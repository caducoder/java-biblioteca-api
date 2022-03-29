package br.com.biblioteca.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.biblioteca.dao.ClienteDAO;
import br.com.biblioteca.model.Cliente;

@Stateless
public class ClienteService {

	@Inject
	private ClienteDAO dao;
	
	public void cadastrarCliente(Cliente cliente) {
		System.out.println("Cheguei no ClienteService");
		dao.cadastrar(cliente);
	}
	
	public List<Cliente> listarClientes() {
		return dao.listar();
	}
	
	public Cliente buscarPorCpf(String cpf) {
		return dao.buscarPorCpf(cpf);
	}
	
//	public void remover(Long id) {
//		dao.remover(id);
//	}
//	
//	public Cliente buscarPorId(Long id) {
//		return dao.buscarPorId(id);
//	}
}
