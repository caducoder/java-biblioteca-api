package br.com.biblioteca.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.biblioteca.dao.LivroDAO;
import br.com.biblioteca.model.Livro;

@Stateless
public class LivroService {

	@Inject
	private LivroDAO dao;
	
	public void cadastrar(Livro livro) {
		dao.cadastrar(livro);
	}

	public List<Livro> listar() {
		return dao.listar();
	}
}
