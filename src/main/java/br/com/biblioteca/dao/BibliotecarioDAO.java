package br.com.biblioteca.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.biblioteca.model.Bibliotecario;
import br.com.biblioteca.model.Usuario;

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
			bi = em.createQuery(jpql, Bibliotecario.class)
					.setParameter("cpf", cpfbi)
					.getSingleResult();
		} catch (RuntimeException e) {
			return null;
		}
		return bi;
	}
	
	public Bibliotecario buscarPorId(Long id) {
		Bibliotecario bbt = em.find(Bibliotecario.class, id);
		return bbt;
	}

	public Boolean remover(Long id) {
		Bibliotecario bi = em.find(Bibliotecario.class, id);
		if(bi != null) {
			em.remove(bi);
			return true;
		} 
		return false;
	}

	public Bibliotecario buscarLogin(String email) {
		String jpql = "SELECT b FROM Bibliotecario b WHERE email=:email";
		Bibliotecario bi = null;
		try {
			bi = em.createQuery(jpql, Bibliotecario.class)
					.setParameter("email", email)
					.getSingleResult();
		} catch (RuntimeException e) {
			return null;
		}
		
		return bi;
	}

	public String buscarNomePorEmail(String email) {
		String jpql = "SELECT b.nome FROM Bibliotecario b WHERE email=:email";
		return em.createQuery(jpql, String.class)
				.setParameter("email", email)
				.getSingleResult();
	}
	
	public void alterar(Bibliotecario biblioAtual, Usuario user) {
		
		biblioAtual.setNome(user.getNome());
		biblioAtual.setCpf(user.getCpf());
		biblioAtual.setRg(user.getRg());
		biblioAtual.setEmail(user.getEmail());
		biblioAtual.setTelefone(user.getTelefone());
		biblioAtual.setEndereco(user.getEndereco());
		
		em.merge(biblioAtual);
	}

	public void salvarNovaSenha(Bibliotecario bbt) {
		em.merge(bbt);
	}
}