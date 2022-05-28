package br.com.biblioteca.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.biblioteca.model.Cliente;

@Stateless
public class ClienteDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void cadastrar(Cliente cliente) throws Exception {
		try {
			em.persist(cliente);
		} catch (Exception e) {
			throw new Exception("Cliente j� est� registrado no sistema.");
		}
		
	}

	public List<Cliente> listar() {
		String jpql = "SELECT c FROM Cliente c";
		return em.createQuery(jpql, Cliente.class).getResultList();
	}
	
	public Cliente buscarPorCpf(String cpfcl) throws Exception {
		String jpql = "SELECT c FROM Cliente c WHERE cpf=:cpf";
		Cliente cl = null;
		try {
			cl = em.createQuery(jpql, Cliente.class).setParameter("cpf", cpfcl).getSingleResult();
		} catch (RuntimeException e) {
			throw new Exception("Cliente n�o encontrado. Cadastre-se na biblioteca.");
		}
		return cl;
	}

	public void remover(Long id) {
		Cliente cl = em.find(Cliente.class, id);
		em.remove(cl);
	}

	public Cliente buscarPorId(Long id) {
		return em.find(Cliente.class, id);
	}

	public void alterar(Cliente nCliente) {
		Cliente clienteAtual = buscarPorId(nCliente.getId());
		
		clienteAtual.setNome(nCliente.getNome());
		clienteAtual.setRg(nCliente.getRg());
		clienteAtual.setEndereco(nCliente.getEndereco());
		clienteAtual.setEmail(nCliente.getEmail());
		clienteAtual.setTelefone(nCliente.getTelefone());
		
		em.merge(clienteAtual);
	}

	public Long contarClientes() {
		String jpql = "SELECT COUNT(c) FROM Cliente c";
		
		return (Long) em.createQuery(jpql).getSingleResult();
	}
}
