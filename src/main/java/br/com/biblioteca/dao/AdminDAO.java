package br.com.biblioteca.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.biblioteca.model.Administrador;
import br.com.biblioteca.model.Bibliotecario;
import br.com.biblioteca.model.Usuario;

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

	public String buscarNomePorEmail(String email) {
		String jpql = "SELECT a.nome FROM Administrador a WHERE email=:email";
		
		return em.createQuery(jpql, String.class).setParameter("email", email).getSingleResult();
	}

	public List<Administrador> listar() {
		String jpql = "SELECT a from Administrador a";
		
		return em.createQuery(jpql, Administrador.class).getResultList();
	}

	public Administrador buscarPorCpf(String cpf) {
		String jpql = "SELECT a FROM Administrador a WHERE cpf=:cpf";
		Administrador adm = null;
		
		try {
			adm = em.createQuery(jpql, Administrador.class).setParameter("cpf", cpf).getSingleResult();
		} catch (RuntimeException e) {
			return null;
		}
		
		return adm;
	}
	
	public Boolean remover(Long id) {
		Administrador adm = em.find(Administrador.class, id);
		
		if(adm != null) {
			em.remove(adm);
			return true;
		}
		
		return false;
	}

	public void alterar(Administrador admAtual, Usuario user) {
		admAtual.setNome(user.getNome());
		admAtual.setRg(user.getRg());
		admAtual.setEndereco(user.getEndereco());
		admAtual.setEmail(user.getEmail());
		admAtual.setTelefone(user.getTelefone());
		
		em.merge(admAtual);
	}

	public void salvarNovaSenha(Administrador adm) {
		em.merge(adm);
	}

	public Administrador buscarPorId(Long idAdmin) {
		
		return em.find(Administrador.class, idAdmin);
	}

}
