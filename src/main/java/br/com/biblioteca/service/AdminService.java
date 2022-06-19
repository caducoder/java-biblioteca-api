package br.com.biblioteca.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.biblioteca.dao.AdminDAO;
import br.com.biblioteca.model.Administrador;
import br.com.biblioteca.model.Bibliotecario;
import br.com.biblioteca.model.Movimentacao;
import br.com.biblioteca.model.Usuario;
import br.com.biblioteca.utils.CryptUtil;
import br.com.biblioteca.utils.TiposMovimentacao;

@Stateless
public class AdminService {

	@Inject
	private AdminDAO dao;

	public void cadastrar(Administrador admin) {
		admin.setSenha(CryptUtil.criptografarSenha(admin.getSenha()));
		dao.cadastrar(admin);
	}

	public boolean verificaLogin(String email, String senha) {
		Administrador admin = dao.buscarAdminLogin(email);
		
		if(admin != null) {
			return CryptUtil.checkPass(senha, admin.getSenha());
		}
		
		return false;
	}

	public String buscarNomePorEmail(String email) {
		return dao.buscarNomePorEmail(email);
	}

	public List<Administrador> listarAdmins() {
		return dao.listar();
	}
	
	public Administrador buscarPorCpf(String cpf) {
		return dao.buscarPorCpf(cpf);
	}
	
	public Administrador buscarPorId(Long idAdmin) {
		return dao.buscarPorId(idAdmin);
	}
	
	public Boolean remover(Long id) {
		boolean foiRemovido = dao.remover(id);
		
		if(foiRemovido) {
			return true;
		}
		
		return false;
	}

	public void alterar(Administrador adm, Usuario user) {
		dao.alterar(adm, user);
	}
	
	public void alterarSenha(Administrador adm, String novaSenha) {
		String novaSenhaCriptografada = CryptUtil.criptografarSenha(novaSenha);
		
		adm.setSenha(novaSenhaCriptografada);
		
		dao.salvarNovaSenha(adm);
	}
}
