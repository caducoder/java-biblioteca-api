package br.com.biblioteca.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.biblioteca.model.Movimentacao;

@Stateless
public class MovimentacaoDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public void registrar(Movimentacao mvt) {
		em.persist(mvt);
	}
	
	public List<Movimentacao> listar() {
		String jpql = "SELECT m FROM Movimentacao m";
		return em.createQuery(jpql, Movimentacao.class).getResultList();
	}
	
	public List<Movimentacao> listaRelatorioLivro() {
		String jpql = "SELECT m FROM Movimentacao m WHERE m.tipoMovimentacao = 'CADASTRO_LIVRO' "
				+ "OR m.tipoMovimentacao = 'EXCLUSAO_LIVRO' "
				+ "OR m.tipoMovimentacao = 'ALTERACAO_LIVRO'";
		return em.createQuery(jpql, Movimentacao.class).getResultList();
	}
	
	public List<Movimentacao> listaRelatorioCliente() {
		String jpql = "SELECT m FROM Movimentacao m WHERE m.tipoMovimentacao = 'CADASTRO_CLIENTE' "
				+ "OR m.tipoMovimentacao = 'ALTERACAO_CLIENTE' "
				+ "OR m.tipoMovimentacao = 'EXCLUSAO_CLIENTE'";
		return em.createQuery(jpql, Movimentacao.class).getResultList();
	}
	
	public List<Movimentacao> listaRelatorioEmprestimo() {
		String jpql = "SELECT m FROM Movimentacao m WHERE m.tipoMovimentacao = 'EMPRESTIMO' "
				+ "OR m.tipoMovimentacao = 'DEVOLUCAO' ";
		return em.createQuery(jpql, Movimentacao.class).getResultList();
	}
	
	public List<Movimentacao> listaRelatorioReserva() {
		String jpql = "SELECT m FROM Movimentacao m WHERE m.tipoMovimentacao = 'RESERVA'";
		return em.createQuery(jpql, Movimentacao.class).getResultList();
	}
	
}
