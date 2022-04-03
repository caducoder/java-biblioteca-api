package br.com.biblioteca.service;

import java.time.LocalDate;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.biblioteca.dao.EmprestimoDAO;
import br.com.biblioteca.model.Cliente;
import br.com.biblioteca.model.Emprestimo;
import br.com.biblioteca.model.Livro;

@Stateless
public class EmprestimoService {

	@Inject
	private EmprestimoDAO dao;
	@Inject
	private ClienteService clienteService;
	@Inject
	private LivroService livroService;
	
	public void realizarEmprestimo(Long idCliente, String codigoLivro, Emprestimo emprestimo) {
		
		Cliente cl = clienteService.buscarPorId(idCliente);
		Livro livro = livroService.buscarLivroPorIsbn(codigoLivro);
		
		emprestimo.setLivro(livro);
		emprestimo.setEmprestadoEm(LocalDate.now());
		cl.adicionarEmprestimo(emprestimo);
		
		dao.salvarEmprestimo(emprestimo);
	}
}
