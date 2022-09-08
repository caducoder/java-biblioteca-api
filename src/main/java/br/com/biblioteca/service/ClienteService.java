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
import facade.DAOFacade;

@Stateless
public class ClienteService {

	@Inject
	private DAOFacade fachada;
	
	public void cadastrar(Cliente cliente) throws Exception {
		fachada.cadastrarCliente(cliente);
		Movimentacao mvt = new Movimentacao(null, cliente.getId(), null, TiposMovimentacao.CADASTRO_CLIENTE, LocalDateTime.now());
		
		fachada.registrarMovimentacao(mvt);
	}
	
	public List<Cliente> listar() {
		return fachada.listarCliente();
	}
	
	public Cliente buscarPorCpf(String cpf) throws Exception {
		return fachada.buscarClientePorCpf(cpf);
	}

	public void remover(Long id) {
		fachada.removerCliente(id);
		Movimentacao mvt = new Movimentacao(null, id, null, TiposMovimentacao.EXCLUSAO_CLIENTE, LocalDateTime.now());
		
		fachada.registrarMovimentacao(mvt);
	}
	
	public Cliente buscarPorId(Long id) {
		return fachada.buscarClientePorId(id);
	}

	public void alterar(Cliente nCliente) {
		fachada.alterarCliente(nCliente);
		Movimentacao mvt = new Movimentacao(null, nCliente.getId(), null, TiposMovimentacao.ALTERACAO_CLIENTE, LocalDateTime.now());
		
		fachada.registrarMovimentacao(mvt);
	}

	public Long contarClientes() {
		return fachada.quantidadeDeClientes();
	}
}
