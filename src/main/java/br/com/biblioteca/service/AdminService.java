package br.com.biblioteca.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.biblioteca.dao.AdminDAO;
import br.com.biblioteca.model.Administrador;
import br.com.biblioteca.utils.CryptUtil;

@Stateless
public class AdminService {

	@Inject
	private AdminDAO dao;

	public void cadastrar(Administrador admin) {
		admin.setSenha(CryptUtil.criptografarSenha(admin.getSenha()));
		dao.cadastrar(admin);

	}
}
