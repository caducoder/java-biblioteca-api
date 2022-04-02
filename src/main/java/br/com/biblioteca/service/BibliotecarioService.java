package br.com.biblioteca.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.biblioteca.dao.BibliotecarioDAO;
import br.com.biblioteca.model.Bibliotecario;

@Stateless
public class BibliotecarioService {

	@Inject
	private BibliotecarioDAO dao;
	
	public void cadastrarBibliotecario(Bibliotecario bibliotecario) {
		System.out.println(bibliotecario);
		dao.cadastrar(bibliotecario);
	}
	
	public List<Bibliotecario> listarBibliotecarios() {
		return dao.listar();
	}
	
	public Bibliotecario buscarPorCpf(String cpf) {
		return dao.buscarPorCpf(cpf);
	}

	public void remover(Long id) {
		dao.remover(id);
		
	}
	
//	public Cliente buscarPorId(Long id) {
//	return dao.buscarPorId(id);
//}
}