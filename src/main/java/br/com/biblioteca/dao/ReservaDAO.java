package br.com.biblioteca.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.Reserva;

@Stateless
public class ReservaDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void salvar(Reserva reserva) {
		System.out.println("cheguei no ReservaDAO");
		em.persist(reserva);
	}

	public List<Reserva> buscarReservasPorCpf(String cpf) {
		String jpql = "SELECT r FROM Reserva r WHERE reservadoPorCpf=:cpf";
		List<Reserva> rsv = null;
		try {
			rsv = em.createQuery(jpql, Reserva.class).setParameter("cpf", cpf).getResultList();
		} catch (Exception e) {
			return null;
		}
		return rsv;
	}
	
	public Reserva buscarReservaPeloLivro(Livro lvr) {
		String jpql = "SELECT r FROM Reserva r WHERE livro=:livro";
		return em.createQuery(jpql, Reserva.class).setParameter("livro", lvr).getSingleResult();
	}
	
	public void removerReserva(Reserva reserva) {
		em.remove(reserva);
	}
}
