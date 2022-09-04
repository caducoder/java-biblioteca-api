package br.com.biblioteca.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.biblioteca.model.Administrador;
import br.com.biblioteca.model.Bibliotecario;
import br.com.biblioteca.model.Cliente;
import br.com.biblioteca.model.Emprestimo;
import br.com.biblioteca.model.FinanceiroForm;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.Movimentacao;
import br.com.biblioteca.model.Reserva;
import br.com.biblioteca.model.Usuario;

@Stateless
public class DAOFacade {
	
	@Inject
	private AdminDAO adminDAO;
	
	@Inject
	private BibliotecarioDAO biblioDAO;
	
	@Inject
	private ClienteDAO clienteDAO;
	
	@Inject
	private EmprestimoDAO emprestimoDAO;
	
	@Inject
	private LivroDAO livroDAO;
	
	@Inject
	private ReservaDAO reservaDAO;
	
	@Inject
	private FinanceiroDAO financeiroDAO;
	
	@Inject
	private MovimentacaoDAO movimentacaoDAO;
	
	/*==== ADMINISTRADOR ====*/

	public void cadastrarAdmin(Administrador admin) {
		adminDAO.cadastrar(admin);
	}
	
	public List<Administrador> listarAdmin() {
		return adminDAO.listar();
	}
	
	public Administrador buscarAdminPorCpf(String cpf) {
		return adminDAO.buscarPorCpf(cpf);
	}
	
	public Administrador buscarAdminPorId(Long id) {
		return adminDAO.buscarPorId(id);
	}
	
	public Boolean removerAdmin(Long id) {
		return adminDAO.remover(id);
	}
	
	public Administrador buscarLoginAdmin(String email) {
		return adminDAO.buscarLogin(email);
	}
	
	public String buscarNomeAdmin(String email) {
		return adminDAO.buscarNomePorEmail(email);
	}
	
	public void alterarAdmin(Administrador adminAtual, Usuario user) {
		adminDAO.alterar(adminAtual, user);
	}
	
	public void salvarSenhaAdmin(Administrador admin) {
		adminDAO.salvarNovaSenha(admin);
	}
	
	/*==== BIBLIOTECARIO ====*/
	
	public void cadastrarBibliotecario(Bibliotecario bibliotecario) {
		biblioDAO.cadastrar(bibliotecario);
	}
	
	public List<Bibliotecario> listarBibliotecario() {
		return biblioDAO.listar();
	}
	
	public Bibliotecario buscarBibliotecarioPorCpf(String cpf) {
		return biblioDAO.buscarPorCpf(cpf);
	}
	
	public Bibliotecario buscarBibliotecarioPorId(Long id) {
		return biblioDAO.buscarPorId(id);
	}
	
	public Boolean removerBibliotecario(Long id) {
		return biblioDAO.remover(id);
	}
	
	public Bibliotecario buscarLoginBibliotecario(String email) {
		return biblioDAO.buscarLogin(email);
	}
	
	public String buscarNomeBibliotecario(String email) {
		return biblioDAO.buscarNomePorEmail(email);
	}
	
	public void alterarBibliotecario(Bibliotecario biblioAtual, Usuario user) {
		biblioDAO.alterar(biblioAtual, user);
	}
	
	public void salvarSenhaBibliotecario(Bibliotecario biblio) {
		biblioDAO.salvarNovaSenha(biblio);
	}
	
	/*==== CLIENTE ====*/
	
	public void cadastrarCliente(Cliente cliente) throws Exception {
		clienteDAO.cadastrar(cliente);
	}
	
	public List<Cliente> listarCliente() {
		return clienteDAO.listar();
	}
	
	public Cliente buscarClientePorCpf(String cpf) throws Exception {
		return clienteDAO.buscarPorCpf(cpf);
	}
	
	public Cliente buscarClientePorId(Long id) {
		return clienteDAO.buscarPorId(id);
	}
	
	public void alterarCliente(Cliente cliente) {
		clienteDAO.alterar(cliente);
	}
	
	public void removerCliente(Long id) {
		clienteDAO.remover(id);
	}
	
	public Long quantidadeDeClientes() {
		return clienteDAO.contarClientes();
	}
	
	/*==== EMPRESTIMO ====*/
	
	public void salvarEmprestimo(Emprestimo empr) {
		emprestimoDAO.salvar(empr);
	}
	
	public void removerEmprestimo(Emprestimo empr) {
		emprestimoDAO.remover(empr);
	}
	
	public Emprestimo renovarEmprestimo(Emprestimo empr) {
		return emprestimoDAO.renovar(empr);
	}
	
	public Long quantidadeDeEmprestimos() {
		return emprestimoDAO.contarEmprestimos();
	}
	
	public Emprestimo buscarEmprestimoPorCodigoLivro(Livro livro) {
		return emprestimoDAO.buscarPorCodigoLivro(livro);
	}
	
	public List<Emprestimo> buscarEmprestimoPorCliente(Cliente cliente) {
		return emprestimoDAO.buscarPorCliente(cliente);
	}
	
	/*==== FINANCEIRO ====*/
	
	public void salvarFinanca(FinanceiroForm fin) {
		financeiroDAO.salvar(fin);
	}
	
	public FinanceiroForm buscaFinanca(Long id) {
		return financeiroDAO.getFinanca(id);
	}
	
	public String buscarNomeNotaFiscal(Long id) {
		return financeiroDAO.getNotaFiscalName(id);
	}
	
	public List<FinanceiroForm> listarFinanca() {
		return financeiroDAO.listar();
	}
	
	/*==== LIVRO ====*/
	
	public void cadastrarLivro(Livro livro) throws Exception {
		livroDAO.cadastrar(livro);
	}
	
	public List<Livro> listarLivro() {
		return livroDAO.listar();
	}
	
	public Livro buscarLivroPorIsbn(String isbn) {
		return livroDAO.buscarPorIsbn(isbn);
	}
	
	public Livro buscarLivroPorId(Long id) {
		return livroDAO.buscarPorId(id);
	}
	
	public Livro buscarLivroPorIssn(String issn) {
		return livroDAO.buscarPorIssn(issn);
	}
	
	public void alterarLivro(Livro livro) {
		livroDAO.alterar(livro);
	}
	
	public void removerLivro(Long id) {
		livroDAO.remover(id);
	}
	
	public Long quantidadeDeLivros() {
		return livroDAO.contarLivros();
	}
	
	/*==== RESERVA ====*/
	public void salvarReserva(Reserva reserva) {
		reservaDAO.salvar(reserva);
	}
	
	public List<Reserva> buscarReservasPorCpf(String cpf) {
		return reservaDAO.buscarPorCpf(cpf);
	}
	
	public Reserva buscarReservaPeloLivro(Livro livro) {
		return reservaDAO.buscarPeloLivro(livro);
	}
	
	public void removerReserva(Reserva reserva) {
		reservaDAO.remover(reserva);
	}
	
	public Long quantidadeDeReservasPorCpf(String cpf) {
		return reservaDAO.contarReservasPorCpf(cpf);
	}
	
	public Long quantidadeDeReservas() {
		return reservaDAO.contarReservasTotal();
	}
	
	/*==== MOVIMENTACAO ====*/
	
	public void registrarMovimentacao(Movimentacao mvt) {
		movimentacaoDAO.registrar(mvt);
	}
	
	public List<Movimentacao> listarMovGeral() {
		return movimentacaoDAO.listar();
	}
	
	public List<Movimentacao> listarMovLivro() {
		return movimentacaoDAO.listaRelatorioLivro();
	}
	
	public List<Movimentacao> listarMovCliente() {
		return movimentacaoDAO.listaRelatorioCliente();
	}
	
	public List<Movimentacao> listarMovEmprestimo() {
		return movimentacaoDAO.listaRelatorioEmprestimo();
	}
	
	public List<Movimentacao> listarMovReserva() {
		return movimentacaoDAO.listaRelatorioReserva();
	}

}
