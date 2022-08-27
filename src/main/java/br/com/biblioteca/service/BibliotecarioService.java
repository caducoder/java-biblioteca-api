package br.com.biblioteca.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.biblioteca.dao.DAOFacade;
import br.com.biblioteca.model.Bibliotecario;
import br.com.biblioteca.model.Movimentacao;
import br.com.biblioteca.model.Usuario;
import br.com.biblioteca.utils.CryptUtil;
import br.com.biblioteca.utils.TiposMovimentacao;

@Stateless
public class BibliotecarioService {

	@Inject
	private DAOFacade fachada;

	public void cadastrar(Bibliotecario bibliotecario) {
		bibliotecario.setSenha(CryptUtil.criptografarSenha(bibliotecario.getSenha()));

		fachada.cadastrarBibliotecario(bibliotecario);
		Movimentacao mvt = new Movimentacao(bibliotecario.getId(), null, null, TiposMovimentacao.CADASTRO_BIBLIOTECARIO,
				LocalDateTime.now());

		fachada.registrarMovimentacao(mvt);
	}

	public List<Bibliotecario> listar() {
		return fachada.listarBibliotecario();
	}

	public Bibliotecario buscarPeloCpf(String cpf) {
		return fachada.buscarBibliotecarioPorCpf(cpf);
	}

	public Bibliotecario buscarPeloId(Long idBiblio) {
		return fachada.buscarBibliotecarioPorId(idBiblio);
	}

	public String buscarNomePeloEmail(String email) {
		return fachada.buscarNomeBibliotecario(email);
	}

	public Boolean remover(Long id) {
		boolean foiRemovido = fachada.removerBibliotecario(id);

		if (foiRemovido) {
			Movimentacao mvt = new Movimentacao(id, null, null, TiposMovimentacao.EXCLUSAO_BIBLIOTECARIO,
					LocalDateTime.now());
			fachada.registrarMovimentacao(mvt);
			return true;
		}

		return false;
	}

	public Boolean verificaLogin(String email, String senha) {
		Bibliotecario bbt = fachada.buscarLoginBibliotecario(email);

		if (bbt != null) {
			return CryptUtil.checkPass(senha, bbt.getSenha());
		}

		return false;
	}

	public void alterar(Bibliotecario bbt, Usuario user) {
		fachada.alterarBibliotecario(bbt, user);
	}

	public void alterarSenha(Bibliotecario bbt, String novaSenha) {
		String novaSenhaCriptografada = CryptUtil.criptografarSenha(novaSenha);

		bbt.setSenha(novaSenhaCriptografada);

		fachada.salvarSenhaBibliotecario(bbt);
	}

}