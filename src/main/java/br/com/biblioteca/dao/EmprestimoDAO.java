package br.com.biblioteca.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.biblioteca.model.Emprestimo;

@Stateless
public class EmprestimoDAO {
	
	@PersistenceContext
	private EntityManager em;

	public void salvarEmprestimo(Emprestimo empr) {
		em.persist(empr);
	}

	public void removerEmprestimo(Emprestimo empr) {
		em.remove(empr);
	}

	public Emprestimo renovarEmprestimo(Emprestimo empr) {
		return em.merge(empr);
	}

	public Long contarEmprestimos() {
		String jpql = "SELECT COUNT(e) FROM Emprestimo e";
		
		return (Long) em.createQuery(jpql).getSingleResult();
	}
}
