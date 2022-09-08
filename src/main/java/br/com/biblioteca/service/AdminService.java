package br.com.biblioteca.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.biblioteca.model.Administrador;
import br.com.biblioteca.model.Usuario;
import br.com.biblioteca.utils.CryptUtil;
import facade.DAOFacade;

@Stateless
public class AdminService {

	@Inject
	private DAOFacade fachada;

	public void cadastrar(Administrador admin) {
		admin.setSenha(CryptUtil.criptografarSenha(admin.getSenha()));
		fachada.cadastrarAdmin(admin);
	}

	public boolean verificaLogin(String email, String senha) {
		Administrador admin = fachada.buscarLoginAdmin(email);

		if (admin != null) {
			return CryptUtil.checkPass(senha, admin.getSenha());
		}

		return false;
	}
	
	public List<Administrador> listar() {
		return fachada.listarAdmin();
	}

	public String buscarNomePorEmail(String email) {
		return fachada.buscarNomeAdmin(email);
	}

	public Administrador buscarPorCpf(String cpf) {
		return fachada.buscarAdminPorCpf(cpf);
	}

	public Administrador buscarPorId(Long idAdmin) {
		return fachada.buscarAdminPorId(idAdmin);
	}

	public Boolean remover(Long id) {
		boolean foiRemovido = fachada.removerAdmin(id);

		if (foiRemovido) {
			return true;
		}

		return false;
	}

	public void alterar(Administrador adm, Usuario user) {
		fachada.alterarAdmin(adm, user);
	}

	public void alterarSenha(Administrador adm, String novaSenha) {
		String novaSenhaCriptografada = CryptUtil.criptografarSenha(novaSenha);

		adm.setSenha(novaSenhaCriptografada);

		fachada.salvarSenhaAdmin(adm);
	}
}
