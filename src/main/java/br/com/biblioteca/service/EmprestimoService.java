package br.com.biblioteca.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
import facade.DAOFacade;

@Stateless
public class EmprestimoService {

	@Inject
	private DAOFacade fachada;

	@Inject
	private ClienteService clienteService;
	@Inject
	private LivroService livroService;
	@Inject
	private ReservaService reservaService;

	public void realizarEmprestimo(Long idCliente, String codigoLivro) throws Exception {
		Cliente cl = clienteService.buscarPorId(idCliente);
		Livro livro = livroService.buscarLivroPorCodigo(codigoLivro);
		
		if(livro.getEstadoLivro() == EstadoLivro.EMPRESTADO) {
			throw new Exception("Livro j· est· emprestado.");
		}
		
		boolean estaReservado = livro.getEstadoLivro() == EstadoLivro.RESERVADO;
		//verifica se livro est√° reservado, se sim, verifica se foi esse cliente que reservou
		if (estaReservado && !reservaService.verificarReserva(cl, livro)) {
			throw new Exception("Livro est· reservado para outra pessoa.");
		} else {
			if(estaReservado) {
				//remove reserva j√° que o cliente fez o emprestimo
				reservaService.removerReserva(livro);
			}
			Emprestimo emprestimo = new Emprestimo();
			
			livro.setEstadoLivro(EstadoLivro.EMPRESTADO);
			emprestimo.setLivro(livro);
			emprestimo.setEmprestadoEm(LocalDate.now());
			emprestimo.setDataDevolucao(LocalDate.now().plusDays(15));
			cl.adicionarEmprestimo(emprestimo);
			Movimentacao mvt = new Movimentacao(null, cl.getId(), livro.getId(), TiposMovimentacao.EMPRESTIMO, LocalDateTime.now());
			
			fachada.registrarMovimentacao(mvt);
			fachada.salvarEmprestimo(emprestimo);
		}
	}

	public void devolverLivro(String codigoLivro) throws Exception {
		Livro lvr = livroService.buscarLivroPorCodigo(codigoLivro);
		Emprestimo empr = lvr.getEmprestimo();
		
		if(empr == null) throw new Exception("N„o foi encontrado emprÈstimo deste livro.");
		
		empr.getCliente().getEmprestimos().remove(empr);
		lvr.setEstadoLivro(EstadoLivro.DISPONIVEL);
		Movimentacao mvt = new Movimentacao(null, empr.getCliente().getId(), lvr.getId(), TiposMovimentacao.DEVOLUCAO, LocalDateTime.now());
		
		fachada.registrarMovimentacao(mvt);
		fachada.removerEmprestimo(empr);
	}

	public Emprestimo renovar(String codigoLivro) {
		Livro lvr = livroService.buscarLivroPorCodigo(codigoLivro);
		Emprestimo empr = lvr.getEmprestimo();
		
		// adiciona mais 15 dias na data de devolu√ß√£o
		empr.setDataDevolucao(empr.getDataDevolucao().plusDays(15));
		return fachada.renovarEmprestimo(empr);
	}

	public Long contarEmprestimos() {
		return fachada.quantidadeDeEmprestimos();
	}

	public Emprestimo buscarPorCodigoLivro(String codLivro) {
		Livro livro = livroService.buscarLivroPorCodigo(codLivro);
		Emprestimo empr = fachada.buscarEmprestimoPorCodigoLivro(livro);
		
		return empr;
	}
	
	public List<Emprestimo> buscarPeloCliente(Long idCliente) {
		Cliente cl = clienteService.buscarPorId(idCliente);
		
		return fachada.buscarEmprestimoPorCliente(cl);
	}

}
