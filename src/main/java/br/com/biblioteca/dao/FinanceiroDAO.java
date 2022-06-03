package br.com.biblioteca.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.biblioteca.model.FinanceiroForm;

@Stateless
public class FinanceiroDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void salvar(FinanceiroForm fin) {
		em.persist(fin);
	}
	
	public FinanceiroForm getFinanca(Long id) {
		return em.find(FinanceiroForm.class, id);
	}
	
	public String getNotaFiscalName(Long id) {
		String jpql = "SELECT f.fileName FROM FinanceiroForm f WHERE id=:id";
		
		return em.createQuery(jpql, String.class).setParameter("id", id).getSingleResult();
	}
	
	public List<FinanceiroForm> listarFinancas() {
		String jpql = "SELECT f FROM FinanceiroForm f";
		
		return em.createQuery(jpql, FinanceiroForm.class).getResultList();
	}
}
