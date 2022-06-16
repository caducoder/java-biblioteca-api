package br.com.biblioteca.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import br.com.biblioteca.model.Emprestimo;
import br.com.biblioteca.model.Livro;

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
	
	public Emprestimo buscarEmprestimoPorCodigoLivro(Livro livro) throws NoResultException {
		String jpql = "SELECT e FROM Emprestimo e WHERE e.livro =:livro";
		
		return em.createQuery(jpql, Emprestimo.class).setParameter("livro", livro).getSingleResult();
	}
}
