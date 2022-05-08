package br.com.biblioteca.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.biblioteca.model.Administrador;

@Stateless
public class AdminDAO {
	
	@PersistenceContext
	private EntityManager em;

	public void cadastrar(Administrador admin) {
		em.persist(admin);		
	}

	public Administrador buscarAdminLogin(String email) {
		String jpql = "SELECT a FROM Administrador a WHERE email=:email";
		Administrador ad = null;
		try {
			ad = em.createQuery(jpql, Administrador.class)
					.setParameter("email", email)
					.getSingleResult();
		} catch (RuntimeException e) {
			return null;
		}
		
		return ad;
	}

}
