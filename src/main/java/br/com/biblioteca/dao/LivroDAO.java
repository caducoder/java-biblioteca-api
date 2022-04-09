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

	public void alterar(Livro nLivro) {
		Livro oldLivro = em.find(Livro.class, nLivro.getId());
		System.out.println(oldLivro);
		
		oldLivro.setIsbn(nLivro.getIsbn());
		oldLivro.setIssn(nLivro.getIssn());
		oldLivro.setDoi(nLivro.getDoi());
		oldLivro.setAutor(nLivro.getAutor());
		oldLivro.setTitulo(nLivro.getTitulo());
		oldLivro.setIdioma(nLivro.getIdioma());
		oldLivro.setDescricao(nLivro.getDescricao());
		oldLivro.setNumeroDePaginas(nLivro.getNumeroDePaginas());
		oldLivro.setAnoEdicao(nLivro.getAnoEdicao());
		
		em.merge(oldLivro);
	}

	public void remover(Long idLivro) {
		Livro lvr = em.find(Livro.class, idLivro);
		em.remove(lvr);
	}
	
}
