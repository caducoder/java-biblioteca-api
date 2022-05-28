package br.com.biblioteca.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.biblioteca.dao.EmprestimoDAO;
import br.com.biblioteca.dao.MovimentacaoDAO;
import br.com.biblioteca.model.Cliente;
import br.com.biblioteca.model.Emprestimo;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.Movimentacao;
import br.com.biblioteca.utils.EstadoLivro;
import br.com.biblioteca.utils.TiposMovimentacao;

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
	@Inject
	private MovimentacaoDAO mvtDao;

	public void realizarEmprestimo(Long idCliente, String codigoLivro) throws Exception {
		Cliente cl = clienteService.buscarPorId(idCliente);
		Livro livro = livroService.buscarLivroPorIsbn(codigoLivro);
		//TODO: verificar po issn tbm
		if(livro.getEstadoLivro() == EstadoLivro.EMPRESTADO) {
			throw new Exception("Livro j� est� emprestado.");
		}
		
		boolean estaReservado = livro.getEstadoLivro() == EstadoLivro.RESERVADO;
		//verifica se livro est� reservado, se sim, verifica se foi esse cliente que reservou
		if (estaReservado && !reservaService.verificarReserva(cl, livro)) {
			throw new Exception("Livro est� reservado para outra pessoa.");
		} else {
			if(estaReservado) {
				//remove reserva j� que o cliente fez o emprestimo
				reservaService.removerReserva(livro);
			}
			Emprestimo emprestimo = new Emprestimo();
			
			livro.setEstadoLivro(EstadoLivro.EMPRESTADO);
			emprestimo.setLivro(livro);
			emprestimo.setEmprestadoEm(LocalDate.now());
			emprestimo.setDataDevolucao(LocalDate.now().plusDays(15));
			cl.adicionarEmprestimo(emprestimo);
			Movimentacao mvt = new Movimentacao(null, cl.getId(), livro.getId(), TiposMovimentacao.EMPRESTIMO, LocalDateTime.now());
			
			mvtDao.registrar(mvt);
			dao.salvarEmprestimo(emprestimo);
		}
	}

	public void devolverLivro(String codigoLivro) throws Exception {
		Livro lvr = livroService.buscarLivroPorIsbn(codigoLivro);
		if(lvr == null) {
			lvr = livroService.buscarPorIssn(codigoLivro);
		}
		
		Emprestimo empr = lvr.getEmprestimo();
		if(empr == null) throw new Exception("N�o foi encontrado empr�stimo deste livro.");
		empr.getCliente().getEmprestimos().remove(empr);
		lvr.setEstadoLivro(EstadoLivro.DISPONIVEL);
		Movimentacao mvt = new Movimentacao(null, empr.getCliente().getId(), lvr.getId(), TiposMovimentacao.DEVOLUCAO, LocalDateTime.now());
		
		mvtDao.registrar(mvt);
		dao.removerEmprestimo(empr);
	}

	public Emprestimo renovarEmprestimo(String codigoLivro) {
		Livro lvr = livroService.buscarLivroPorIsbn(codigoLivro);
		Emprestimo empr = lvr.getEmprestimo();
		// adiciona mais 15 dias na data de devolu��o
		empr.setDataDevolucao(empr.getDataDevolucao().plusDays(15));
		return dao.renovarEmprestimo(empr);
	}

	public Long contarEmprestimos() {
		return dao.contarEmprestimos();
	}

}
