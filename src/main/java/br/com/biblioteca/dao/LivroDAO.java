package br.com.biblioteca.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import br.com.biblioteca.model.Livro;

@Stateless
public class LivroDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void cadastrar(Livro livro) throws Exception {
		try {
			em.persist(livro);
		} catch (Exception e) {
			throw new Exception("Livro já está registrado no sistema.");
		}
	}
	
	public List<Livro> listar() {
		String jpql = "SELECT l FROM Livro l";
		return em.createQuery(jpql, Livro.class).getResultList();
	}

	public Livro buscarLivroPorIsbn(String isbnL) {
		String jpql = "SELECT l FROM Livro l WHERE isbn=:isbn";
		Livro lvr = null;
		
		try {
			lvr = em.createQuery(jpql, Livro.class).setParameter("isbn", isbnL).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		
		return lvr;
	}
	
	public Livro buscarLivroPorId(Long id) {
		return em.find(Livro.class, id);
	}

	public void alterar(Livro nLivro) {
		Livro oldLivro = em.find(Livro.class, nLivro.getId());
		System.out.println(oldLivro);
		
		oldLivro.setIsbn(nLivro.getIsbn());
		oldLivro.setIssn(nLivro.getIssn());
		oldLivro.setDoi(nLivro.getDoi());
		oldLivro.setAutor(nLivro.getAutor());
		oldLivro.setEditora(nLivro.getEditora());
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

	public Long quantidadeLivros() {
		String jpql = "SELECT COUNT(l) FROM Livro l";
		
		return (Long) em.createQuery(jpql).getSingleResult();
	}

	public Livro buscarLivroPorIssn(String issn) {
		String jpql = "SELECT l FROM Livro l WHERE issn=:issn";
		Livro lvr = null;
		
		try {
			lvr = em.createQuery(jpql, Livro.class).setParameter("issn", issn).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		
		return lvr;
	}
	
}
