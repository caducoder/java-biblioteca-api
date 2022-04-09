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

}
