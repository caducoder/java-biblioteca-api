package br.com.biblioteca.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.biblioteca.dao.LivroDAO;
import br.com.biblioteca.dao.MovimentacaoDAO;
import br.com.biblioteca.model.Cliente;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.Movimentacao;
import br.com.biblioteca.model.Reserva;
import br.com.biblioteca.utils.EstadoLivro;
import br.com.biblioteca.utils.TiposMovimentacao;

@Stateless
public class LivroService {

	@Inject
	private LivroDAO dao;
	
	@Inject
	private ReservaService reservaService;
	
	@Inject
	private ClienteService clienteService;
	
	@Inject
	private MovimentacaoDAO mvtDao;
	
	public void cadastrar(Livro livro) throws Exception {
		dao.cadastrar(livro);
		Movimentacao mvt = new Movimentacao(null, null, livro.getId(), TiposMovimentacao.CADASTRO_LIVRO, LocalDateTime.now());
		
		mvtDao.registrar(mvt);
	}

	public List<Livro> listar() {
		return dao.listar();
	}
	
	public Livro buscarLivroPorIsbn(String isbn) {
		return dao.buscarLivroPorIsbn(isbn);
	}
	
	public Livro buscarLivroPorId(Long idLivro) {
		return dao.buscarLivroPorId(idLivro);
	}

	public void alterar(Livro nLivro) {
		dao.alterar(nLivro);
		
		Movimentacao mvt = new Movimentacao(null, null, nLivro.getId(), TiposMovimentacao.ALTERACAO_LIVRO, LocalDateTime.now());
		
		mvtDao.registrar(mvt);
	}

	public void remover(Long idLivro) {
		dao.remover(idLivro);
		
		Movimentacao mvt = new Movimentacao(null, null, null, TiposMovimentacao.EXCLUSAO_LIVRO, LocalDateTime.now());
		
		mvtDao.registrar(mvt);
	}

	public void reservar(Long idLivro, String cpf) throws Exception {
		Cliente cliente = clienteService.buscarPorCpf(cpf);
		Livro lvr = buscarLivroPorId(idLivro);
		
		if(lvr.getEstadoLivro() == EstadoLivro.RESERVADO) {
			throw new Exception("Livro já está reservado.");
		}
		if(lvr.getEstadoLivro() == EstadoLivro.EMPRESTADO) {
			throw new Exception("Livro está emprestado.");
		}
		
		Reserva reserva = new Reserva(lvr, cpf, LocalDate.now().plusDays(5));
		
		reservaService.salvarReserva(reserva);
		
		lvr.setEstadoLivro(EstadoLivro.RESERVADO);
	}

	public Long quantidade() {
		return dao.quantidadeLivros();
	}

	public Livro buscarPorIssn(String codigoLivro) {
		return dao.buscarLivroPorIssn(codigoLivro);
	}

}
