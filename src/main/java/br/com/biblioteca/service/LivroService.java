package br.com.biblioteca.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.biblioteca.model.Cliente;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.Movimentacao;
import br.com.biblioteca.model.Reserva;
import br.com.biblioteca.utils.EstadoLivro;
import br.com.biblioteca.utils.TiposMovimentacao;
import facade.DAOFacade;

@Stateless
public class LivroService {

	@Inject
	private DAOFacade fachada;
	
	@Inject
	private ReservaService reservaService;
	
	@Inject
	private ClienteService clienteService;
	
	
	public void cadastrar(Livro livro) throws Exception {
		fachada.cadastrarLivro(livro);
		Movimentacao mvt = new Movimentacao(null, null, livro.getId(), TiposMovimentacao.CADASTRO_LIVRO, LocalDateTime.now());
		
		fachada.registrarMovimentacao(mvt);
	}

	public List<Livro> listar() {
		return fachada.listarLivro();
	}
	
	public Livro buscarLivroPorCodigo(String codigo) {
		Livro lvr = fachada.buscarLivroPorIsbn(codigo);
		
		if(lvr == null) lvr = fachada.buscarLivroPorIssn(codigo);
		
		return lvr;
	}
	
	public Livro buscarLivroPorId(Long idLivro) {
		return fachada.buscarLivroPorId(idLivro);
	}

	public void alterar(Livro nLivro) {
		fachada.alterarLivro(nLivro);
		
		Movimentacao mvt = new Movimentacao(null, null, nLivro.getId(), TiposMovimentacao.ALTERACAO_LIVRO, LocalDateTime.now());
		
		fachada.registrarMovimentacao(mvt);
	}

	public void remover(Long idLivro) {
		fachada.removerLivro(idLivro);
		
		Movimentacao mvt = new Movimentacao(null, null, idLivro, TiposMovimentacao.EXCLUSAO_LIVRO, LocalDateTime.now());
		
		fachada.registrarMovimentacao(mvt);
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
		return fachada.quantidadeDeLivros();
	}

}
