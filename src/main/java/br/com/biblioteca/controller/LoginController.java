package br.com.biblioteca.controller;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.biblioteca.service.AdminService;
import br.com.biblioteca.service.BibliotecarioService;
import br.com.biblioteca.utils.AuthResponse;
import br.com.biblioteca.utils.Credentials;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Path("/login")
public class LoginController {

	public static final Key CHAVE = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	@Inject
	private BibliotecarioService bibliotecarioService;
	
	@Inject
	private AdminService adminService;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response autenticarUsuario(Credentials credenciais) {
		AuthResponse authParams = new AuthResponse();
		try {
			// adiciona na resposta o c�digo equivalente a fun��o do usuario: admin ou bibliotecario
			authParams.addRole(authenticate(credenciais.getEmail(), credenciais.getSenha()));
			
			// emitindo token para o email recebido
			String token = issueToken(credenciais.getEmail());

			// setando o token na resposta
			authParams.setAccessToken(token);

			return Response.status(200).entity(objectToJSON(authParams)).build();
		} catch (Exception ex) {
			return Response.status(Response.Status.FORBIDDEN).entity(ex.getMessage()).build();
		}
	}
	
	// m�todo que verifica se o usu�rio � bibliotecario ou administrador, e retorna o c�digo equivalente
	private int authenticate(String email, String senha) throws Exception {
		
		if(bibliotecarioService.loginBiblio(email, senha)) {
			return 2200;// c�digo para bibliotecario
		}
		if(adminService.loginAdmin(email, senha)) {
			return 2205;// c�digo para admin
		}
		//caso n�o encontre nenhum dos dois
		throw new Exception("Usu�rio e/ou senha inv�lidos");
	}
	
	// m�todo que emite o token
	private String issueToken(String email) {
		String jwtToken = Jwts.builder()
                .setSubject(email)
                .setIssuer("localhost:8080")
                .setIssuedAt(new Date())
                .setExpiration(
					Date.from(
						LocalDateTime.now().plusMinutes(15L)
							.atZone(ZoneId.systemDefault())
						.toInstant()))
                .signWith(CHAVE)
                .compact();
		return jwtToken;
	}

	// m�todo para mapear a resposta para o formato JSON
	private String objectToJSON(AuthResponse ap) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(ap);
			return json;
		} catch (Exception e) {
			return null;
		}
	}
}
