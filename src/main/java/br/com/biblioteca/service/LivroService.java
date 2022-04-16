package br.com.biblioteca.service;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.biblioteca.dao.LivroDAO;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.Reserva;
import br.com.biblioteca.utils.EstadoLivro;

@Stateless
public class LivroService {

	@Inject
	private LivroDAO dao;
	
	@Inject
	private ReservaService reservaService;
	
	public void cadastrar(Livro livro) {
		dao.cadastrar(livro);
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
	}

	public void remover(Long idLivro) {
		dao.remover(idLivro);
	}

	public void reservar(Long idLivro, String cpf) throws Exception {
		Livro lvr = buscarLivroPorId(idLivro);
		if(lvr.getEstadoLivro() == EstadoLivro.RESERVADO) {
			throw new Exception("Livro já está reservado.");
		}
		
		Reserva reserva = new Reserva(lvr, cpf, LocalDate.now().plusDays(5));
		
		lvr.setEstadoLivro(EstadoLivro.RESERVADO);
		
		reservaService.salvarReserva(reserva);
	}
}
