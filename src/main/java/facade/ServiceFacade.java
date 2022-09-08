package facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response.ResponseBuilder;

import br.com.biblioteca.model.Administrador;
import br.com.biblioteca.model.Bibliotecario;
import br.com.biblioteca.model.Cliente;
import br.com.biblioteca.model.Emprestimo;
import br.com.biblioteca.model.FinanceiroForm;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.Movimentacao;
import br.com.biblioteca.model.Usuario;
import br.com.biblioteca.service.AdminService;
import br.com.biblioteca.service.BibliotecarioService;
import br.com.biblioteca.service.ClienteService;
import br.com.biblioteca.service.EmprestimoService;
import br.com.biblioteca.service.FinanceiroService;
import br.com.biblioteca.service.LivroService;
import br.com.biblioteca.service.ReservaService;
import br.com.biblioteca.utils.PdfFileInfo;

@Stateless
public class ServiceFacade {
	
	@Inject
	private DAOFacade fachadaDAO;

	@Inject
	private AdminService adminService;
	
	@Inject
	private BibliotecarioService bibliotecarioService;
	
	@Inject
	private ClienteService clienteService;
	
	@Inject
	private LivroService livroService;
	
	@Inject
	private EmprestimoService emprestimoService;
	
	@Inject
	private FinanceiroService financeiroService;
	
	@Inject
	private ReservaService reservaService;
	
	/*==== ADMINISTRADOR ====*/
	
	public void cadastrarAdministrador(Administrador admin) {
		adminService.cadastrar(admin);
	}
	
	public List<Administrador> listarAdministradores() {
		return adminService.listar();
	}
	
	public Administrador buscarAdministradorPeloCPF(String cpf) {
		return adminService.buscarPorCpf(cpf);
	}
	
	public Administrador buscarAdministradorPeloID(Long id) {
		return adminService.buscarPorId(id);
	}
	
	public String buscarNomeAdministradorPeloEmail(String email) {
		return adminService.buscarNomePorEmail(email);
	}
	
	public Boolean verificaLoginAdministrador(String email, String senha) {
		return adminService.verificaLogin(email, senha);
	}
	
	public void alterarAdministrador(Administrador adm, Usuario user) {
		adminService.alterar(adm, user);
	}
	
	public void alterarSenhaAdministrador(Administrador adm, String novaSenha) {
		adminService.alterarSenha(adm, novaSenha);
	}
	
	public void removerAdministrador(Long id) {
		adminService.remover(id);
	}
	
	/*==== BIBLIOTECARIO ====*/
	
	public void cadastrarBibliotecario(Bibliotecario biblio) {
		bibliotecarioService.cadastrar(biblio);
	}
	
	public List<Bibliotecario> listarBibliotecarios() {
		return bibliotecarioService.listar();
	}
	
	public Bibliotecario buscarBibliotecarioPeloCPF(String cpf) {
		return bibliotecarioService.buscarPeloCpf(cpf);
	}
	
	public Bibliotecario buscarBibliotecarioPeloID(Long id) {
		return bibliotecarioService.buscarPeloId(id);
	}
	
	public String buscarNomeBibliotecarioPeloEmail(String email) {
		return bibliotecarioService.buscarNomePeloEmail(email);
	}
	
	public Boolean verificaLoginBibliotecario(String email, String senha) {
		return bibliotecarioService.verificaLogin(email, senha);
	}
	
	public void alterarBibliotecario(Bibliotecario biblio, Usuario user) {
		bibliotecarioService.alterar(biblio, user);
	}
	
	public void alterarSenhaBibliotecario(Bibliotecario biblio, String novaSenha) {
		bibliotecarioService.alterarSenha(biblio, novaSenha);
	}
	
	public Boolean removerBibliotecario(Long id) {
		return bibliotecarioService.remover(id);
	}
	
	/*==== CLIENTE ====*/
	
	public void cadastrarCliente(Cliente cliente) throws Exception {
		clienteService.cadastrar(cliente);
	}
	
	public List<Cliente> listarCliente() {
		return clienteService.listar();
	}
	
	public Long quantidadeDeClientes() {
		return clienteService.contarClientes();
	}
	
	public Cliente buscarClientePeloCPF(String cpf) throws Exception {
		return clienteService.buscarPorCpf(cpf);
	}
	
	public Cliente buscarClientePeloID(Long id) {
		return clienteService.buscarPorId(id);
	}
	
	public void alterarCliente(Cliente cliente) {
		clienteService.alterar(cliente);
	}
	
	public void removerCliente(Long id) {
		clienteService.remover(id);
	}
	
	/*==== EMPRESTIMO ====*/
	
	public void realizarEmprestimo(Long idCliente, String codigoLivro) throws Exception {
		emprestimoService.realizarEmprestimo(idCliente, codigoLivro);
	}
	
	public Emprestimo buscarEmprestimoPeloCodigoLivro(String codigo) {
		return emprestimoService.buscarPorCodigoLivro(codigo);
	}
	
	public List<Emprestimo> buscarEmprestimosPeloCliente(Long idCliente) {
		return emprestimoService.buscarPeloCliente(idCliente);
	}
	
	public void realizarDevolucao(String codigoLivro) throws Exception {
		emprestimoService.devolverLivro(codigoLivro);
	}
	
	public Emprestimo renovarEmprestimo(String codigoLivro) {
		return emprestimoService.renovar(codigoLivro);
	}
	
	public Long quantidadeDeEmprestimos() {
		return emprestimoService.contarEmprestimos();
	}
	
	/*==== FINANCEIRO ====*/
	
	public void salvarFinanca(PdfFileInfo formData) throws Exception {
		financeiroService.salvarFinanca(formData);
	}
	
	public ResponseBuilder buscarNotaFiscalPeloId(Long id) throws Exception {
		return financeiroService.getNotaFiscal(id);
	}
	
	public List<FinanceiroForm> listarFinanceiro() {
		return financeiroService.getFinancas();
	}
	
	/*==== LIVRO ====*/
	
	public void cadastrarLivro(Livro livro) throws Exception {
		livroService.cadastrar(livro);
	}
	
	public List<Livro> listarLivros() {
		return livroService.listar();
	}
	
	public Long quantidadeDeLivros() {
		return livroService.quantidade();
	}
	
	public Livro buscarLivroPeloCodigo(String codigo) {
		return livroService.buscarLivroPorCodigo(codigo);
	}
	
	public void reservarLivro(Long idLivro, String cpf) throws Exception {
		livroService.reservar(idLivro, cpf);
	}
	
	public void alterarLivro(Livro livro) {
		livroService.alterar(livro);
	}
	
	public void removerLivro(Long id) {
		livroService.remover(id);
	}
	
	/*==== MOVIMENTACAO ====*/
	
	public List<Movimentacao> listarMovimentacoes() {
		return fachadaDAO.listarMovGeral();
	}
	
	public List<Movimentacao> listarMovimentacoesLivro() {
		return fachadaDAO.listarMovLivro();
	}
	
	public List<Movimentacao> listarMovimentacoesCliente() {
		return fachadaDAO.listarMovCliente();
	}
	
	public List<Movimentacao> listarMovimentacoesEmprestimo() {
		return fachadaDAO.listarMovEmprestimo();
	}
	
	public List<Movimentacao> listarMovimentacoesReserva() {
		return fachadaDAO.listarMovReserva();
	}
	
	/*==== RESERVA ====*/
	
	public Long quantidadeDeReservas() {
		return reservaService.contarReservas();
	}
	
}
