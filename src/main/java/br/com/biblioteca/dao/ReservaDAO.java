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
		em.persist(reserva);
	}

	public List<Reserva> buscarPorCpf(String cpf) {
		String jpql = "SELECT r FROM Reserva r WHERE reservadoPorCpf=:cpf";
		List<Reserva> rsv = null;
		try {
			rsv = em.createQuery(jpql, Reserva.class).setParameter("cpf", cpf).getResultList();
		} catch (Exception e) {
			return null;
		}
		return rsv;
	}
	
	public Long contarReservasPorCpf(String cpf) {
		String jpql = "SELECT COUNT(r) FROM Reserva r WHERE reservadoPorCpf=:cpf";
		return (Long) em.createQuery(jpql).setParameter("cpf", cpf).getSingleResult();
	}
	
	public Reserva buscarPeloLivro(Livro lvr) {
		String jpql = "SELECT r FROM Reserva r WHERE livro=:livro";
		return em.createQuery(jpql, Reserva.class).setParameter("livro", lvr).getSingleResult();
	}
	
	public void remover(Reserva reserva) {
		em.remove(reserva);
	}

	public Long contarReservasTotal() {
		String jpql = "SELECT COUNT(r) FROM Reserva r";
		
		return (Long) em.createQuery(jpql).getSingleResult();
	}
}
