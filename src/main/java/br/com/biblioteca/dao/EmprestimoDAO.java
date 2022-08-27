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

	public void salvar(Emprestimo empr) {
		em.persist(empr);
	}

	public void remover(Emprestimo empr) {
		em.remove(empr);
	}

	public Emprestimo renovar(Emprestimo empr) {
		return em.merge(empr);
	}

	public Long contarEmprestimos() {
		String jpql = "SELECT COUNT(e) FROM Emprestimo e";
		
		return (Long) em.createQuery(jpql).getSingleResult();
	}
	
	public Emprestimo buscarPorCodigoLivro(Livro livro) throws NoResultException {
		String jpql = "SELECT e FROM Emprestimo e WHERE e.livro =:livro";
		
		return em.createQuery(jpql, Emprestimo.class).setParameter("livro", livro).getSingleResult();
	}
}
