package br.com.biblioteca.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.biblioteca.dao.BibliotecarioDAO;
import br.com.biblioteca.dao.MovimentacaoDAO;
import br.com.biblioteca.model.Bibliotecario;
import br.com.biblioteca.model.Movimentacao;
import br.com.biblioteca.utils.CryptUtil;
import br.com.biblioteca.utils.TiposMovimentacao;

@Stateless
public class BibliotecarioService {

	@Inject
	private BibliotecarioDAO dao;
	@Inject
	private MovimentacaoDAO mvtDao;
	
	public void cadastrarBibliotecario(Bibliotecario bibliotecario) {
		bibliotecario.setSenha(CryptUtil.criptografarSenha(bibliotecario.getSenha()));
		dao.cadastrar(bibliotecario);
		Movimentacao mvt = new Movimentacao(bibliotecario.getId(), null, null, TiposMovimentacao.CADASTRO_BIBLIOTECARIO, LocalDateTime.now());
		
		mvtDao.registrar(mvt);
	}
	
	public List<Bibliotecario> listarBibliotecarios() {
		return dao.listar();
	}
	
	public Bibliotecario buscarPorCpf(String cpf) {
		return dao.buscarPorCpf(cpf);
	}
	
	public String buscarNomePorEmail(String email) {
		return dao.buscarNomePorEmail(email);
	}

	public void remover(Long id) {
		dao.remover(id);
		Movimentacao mvt = new Movimentacao(id, null, null, TiposMovimentacao.EXCLUSAO_BIBLIOTECARIO, LocalDateTime.now());
		
		mvtDao.registrar(mvt);
	}
	
	public Boolean loginBiblio(String email, String senha) {
		Bibliotecario bbt = dao.buscarBiblioLogin(email);
		if(bbt != null) {
			return CryptUtil.checkPass(senha, bbt.getSenha());
		}
		
		return false;
	}
	
}