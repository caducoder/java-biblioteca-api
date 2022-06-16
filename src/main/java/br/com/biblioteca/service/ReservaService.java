package br.com.biblioteca.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.biblioteca.dao.MovimentacaoDAO;
import br.com.biblioteca.dao.ReservaDAO;
import br.com.biblioteca.model.Cliente;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.Movimentacao;
import br.com.biblioteca.model.Reserva;
import br.com.biblioteca.utils.TiposMovimentacao;

@Stateless
public class ReservaService {
	
	@Inject
	private ReservaDAO dao;
	
	@Inject
	private MovimentacaoDAO mvtDao;
	
	@Inject
	private ClienteService clienteService;
	
	public void salvarReserva(Reserva reserva) throws Exception {
		Long qtdReservas = dao.contarReservasPorCpf(reserva.getReservadoPorCpf());
		
		if(qtdReservas > 4) {
			throw new Exception("Limite de reservas online atingido.");
		};
		
		Movimentacao mvt = new Movimentacao(null, clienteService.buscarPorCpf(reserva.getReservadoPorCpf()).getId(), reserva.getLivro().getId(), TiposMovimentacao.RESERVA, LocalDateTime.now());

		mvtDao.registrar(mvt);
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

	public Long contarReservas() {
		return dao.contarReservasTotal();
	}
	
	
}
