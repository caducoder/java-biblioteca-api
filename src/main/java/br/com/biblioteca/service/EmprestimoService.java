package br.com.biblioteca.service;

import java.time.LocalDate;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.biblioteca.dao.EmprestimoDAO;
import br.com.biblioteca.model.Cliente;
import br.com.biblioteca.model.Emprestimo;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.utils.EstadoLivro;

@Stateless
public class EmprestimoService {

	@Inject
	private EmprestimoDAO dao;
	@Inject
	private ClienteService clienteService;
	@Inject
	private LivroService livroService;
	@Inject
	private ReservaService reservaService;

	public void realizarEmprestimo(Long idCliente, String codigoLivro) throws Exception {
		// TODO: bloquear emprestimo de livro já emprestado
		Cliente cl = clienteService.buscarPorId(idCliente);
		Livro livro = livroService.buscarLivroPorIsbn(codigoLivro);
		boolean estaReservado = livro.getEstadoLivro() == EstadoLivro.RESERVADO;
		//verifica se livro está reservado, se sim, verifica se foi esse cliente que reservou
		if (estaReservado && !reservaService.verificarReserva(cl, livro)) {
			
			throw new Exception("Livro está reservado para outra pessoa.");
		} else {
			if(estaReservado) {
				//remove reserva já que o cliente fez o emprestimo
				reservaService.removerReserva(livro);
			}
			Emprestimo emprestimo = new Emprestimo();
			
			livro.setEstadoLivro(EstadoLivro.EMPRESTADO);
			emprestimo.setLivro(livro);
			emprestimo.setEmprestadoEm(LocalDate.now());
			emprestimo.setDataDevolucao(LocalDate.now().plusDays(15));
			cl.adicionarEmprestimo(emprestimo);

			dao.salvarEmprestimo(emprestimo);
		}
	}

	public void devolverLivro(String codigoLivro) {
		Livro lvr = livroService.buscarLivroPorIsbn(codigoLivro);
		Emprestimo empr = lvr.getEmprestimo();
		empr.getCliente().getEmprestimos().remove(empr);
		lvr.setEstadoLivro(EstadoLivro.DISPONIVEL);

		dao.removerEmprestimo(empr);
	}

	public Emprestimo renovarEmprestimo(String codigoLivro) {
		Livro lvr = livroService.buscarLivroPorIsbn(codigoLivro);
		Emprestimo empr = lvr.getEmprestimo();
		empr.setDataDevolucao(empr.getDataDevolucao().plusDays(15));
		return dao.renovarEmprestimo(empr);
	}

}
