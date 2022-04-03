package br.com.biblioteca.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.biblioteca.model.Livro;

@Stateless
public class LivroDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void cadastrar(Livro livro) {
		em.persist(livro);
	}
	
	public List<Livro> listar() {
		String jpql = "SELECT l FROM Livro l";
		return em.createQuery(jpql, Livro.class).getResultList();
	}

	public Livro buscarLivroPorIsbn(String isbnL) {
		String jpql = "SELECT l FROM Livro l WHERE isbn=:isbn";
		
		return em.createQuery(jpql, Livro.class)
				.setParameter("isbn", isbnL)
				.getSingleResult();
		
	}
	
}
