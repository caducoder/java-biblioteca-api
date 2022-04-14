package br.com.biblioteca.service;

import java.time.LocalDate;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.biblioteca.dao.EmprestimoDAO;
import br.com.biblioteca.model.Cliente;
import br.com.biblioteca.model.Emprestimo;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.utils.EstadoLivro;

@Stateless
public class EmprestimoService {

	@Inject
	private EmprestimoDAO dao;
	@Inject
	private ClienteService clienteService;
	@Inject
	private LivroService livroService;
	
	public void realizarEmprestimo(Long idCliente, String codigoLivro) {
		
		Cliente cl = clienteService.buscarPorId(idCliente);
		Livro livro = livroService.buscarLivroPorIsbn(codigoLivro);
		Emprestimo emprestimo = new Emprestimo();
		
		livro.setEstadoLivro(EstadoLivro.EMPRESTADO);
		emprestimo.setLivro(livro);
		emprestimo.setEmprestadoEm(LocalDate.now());
		emprestimo.setDataDevolucao(LocalDate.now().plusDays(15));
		cl.adicionarEmprestimo(emprestimo);
		
		dao.salvarEmprestimo(emprestimo);
	}

	public void devolverLivro(String codigoLivro) {
		Livro lvr = livroService.buscarLivroPorIsbn(codigoLivro);
		Emprestimo empr = lvr.getEmprestimo();
		empr.getCliente().getEmprestimos().remove(empr);
		lvr.setEstadoLivro(EstadoLivro.DISPONIVEL);
		
		dao.removerEmprestimo(empr);
		
	}
}
