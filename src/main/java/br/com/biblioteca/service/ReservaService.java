package br.com.biblioteca.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.biblioteca.dao.ReservaDAO;
import br.com.biblioteca.model.Cliente;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.Reserva;

@Stateless
public class ReservaService {
	
	@Inject
	private ReservaDAO dao;
	
	public void salvarReserva(Reserva reserva) throws Exception {
		Long qtdReservas = dao.contarReservasPorCpf(reserva.getReservadoPorCpf());
		
		if(qtdReservas > 4) {
			throw new Exception("Limite de reservas online atingido.");
		};
		
		dao.salvar(reserva);
	}

	public boolean verificarReserva(Cliente cl, Livro livro) {
		//verifica se existe reserva do livro para esse cliente
		Boolean ehReservado = buscarReservasPorCpf(cl.getCpf()).stream().filter(r -> r.getLivro() == livro).findFirst().isPresent();
		return ehReservado;
	}
	
	public List<Reserva> buscarReservasPorCpf(String cpf) {
		return dao.buscarReservasPorCpf(cpf);
	}
	
	public Reserva buscarReservaPeloLivro(Livro livro) {
		return dao.buscarReservaPeloLivro(livro);
	}
	
	public void removerReserva(Livro livro) {
		dao.removerReserva(buscarReservaPeloLivro(livro));
	}
	
	
}
