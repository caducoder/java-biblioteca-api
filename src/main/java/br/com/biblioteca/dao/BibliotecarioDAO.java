package br.com.biblioteca.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.biblioteca.model.Bibliotecario;

@Stateless
public class BibliotecarioDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void cadastrar(Bibliotecario bibliotecario) {
		em.persist(bibliotecario);
	}

	public List<Bibliotecario> listar() {
		String jpql = "SELECT b FROM Bibliotecario b";
		return em.createQuery(jpql, Bibliotecario.class).getResultList();
	}
	
	public Bibliotecario buscarPorCpf(String cpfbi) {
		String jpql = "SELECT b FROM Bibliotecario b WHERE cpf=:cpf";
		Bibliotecario bi = null;
		try {
			bi = em.createQuery(jpql, Bibliotecario.class).setParameter("cpf", cpfbi).getSingleResult();
		} catch (RuntimeException e) {
			return null;
		}
		return bi;
	}

	public void remover(Long id) {
		Bibliotecario bi = em.find(Bibliotecario.class, id);
		em.remove(bi);
	}
}