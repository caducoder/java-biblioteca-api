package br.com.biblioteca.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.biblioteca.model.Movimentacao;

@Stateless
public class MovimentacaoDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public void registrar(Movimentacao mvt) {
		em.persist(mvt);
	}
	
	public List<Movimentacao> listar() {
		String jpql = "SELECT m FROM Movimentacao m";
		return em.createQuery(jpql, Movimentacao.class).getResultList();
	}
}
