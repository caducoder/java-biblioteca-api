package br.com.biblioteca.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.biblioteca.dao.ClienteDAO;
import br.com.biblioteca.dao.MovimentacaoDAO;
import br.com.biblioteca.model.Cliente;
import br.com.biblioteca.model.Movimentacao;
import br.com.biblioteca.utils.TiposMovimentacao;

@Stateless
public class ClienteService {

	@Inject
	private ClienteDAO dao;
	
	@Inject
	private MovimentacaoDAO mvtDao;
	
	public void cadastrarCliente(Cliente cliente) throws Exception {
		dao.cadastrar(cliente);
		Movimentacao mvt = new Movimentacao(null, cliente.getId(), null, TiposMovimentacao.CADASTRO_CLIENTE, LocalDateTime.now());
		
		mvtDao.registrar(mvt);
	}
	
	public List<Cliente> listarClientes() {
		return dao.listar();
	}
	
	public Cliente buscarPorCpf(String cpf) throws Exception {
		return dao.buscarPorCpf(cpf);
	}

	public void remover(Long id) {
		dao.remover(id);
		Movimentacao mvt = new Movimentacao(null, id, null, TiposMovimentacao.EXCLUSAO_CLIENTE, LocalDateTime.now());
		
		mvtDao.registrar(mvt);
	}
	
	public Cliente buscarPorId(Long id) {
		return dao.buscarPorId(id);
	}

	public void alterar(Cliente nCliente) {
		dao.alterar(nCliente);
		Movimentacao mvt = new Movimentacao(null, nCliente.getId(), null, TiposMovimentacao.ALTERACAO_CLIENTE, LocalDateTime.now());
		
		mvtDao.registrar(mvt);
	}

	public Long contarClientes() {
		return dao.contarClientes();
	}
}
