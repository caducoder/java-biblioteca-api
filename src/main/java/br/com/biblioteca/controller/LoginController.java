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

import br.com.biblioteca.utils.AuthResponse;
import br.com.biblioteca.utils.Credentials;
import facade.ServiceFacade;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Path("/login")
public class LoginController {

	public static final Key CHAVE = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	@Inject
	private ServiceFacade fachadaService;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response autenticarUsuario(Credentials credenciais) {
		AuthResponse authParams = new AuthResponse();
		try {
			int codigo = authenticate(credenciais.getEmail(), credenciais.getSenha());
			
			// adiciona o nome do usuário na resposta
			if(codigo == 2200) {
				authParams.setUsername(fachadaService.buscarNomeBibliotecarioPeloEmail(credenciais.getEmail()));
			} else {
				authParams.setUsername(fachadaService.buscarNomeAdministradorPeloEmail(credenciais.getEmail()));
			}
			
			// adiciona na resposta o código equivalente a função do usuario: admin ou bibliotecario
			authParams.addRole(codigo);
			
			// emitindo token para o email recebido
			String token = issueToken(credenciais.getEmail());

			// setando o token na resposta
			authParams.setAccessToken(token);

			return Response.status(200).entity(objectToJSON(authParams)).build();
		} catch (Exception ex) {
			return Response.status(Response.Status.FORBIDDEN).entity(ex.getMessage()).build();
		}
	}
	
	// método que verifica se o usuário é bibliotecario ou administrador, e retorna o código equivalente
	private int authenticate(String email, String senha) throws Exception {
		
		if(fachadaService.verificaLoginBibliotecario(email, senha)) {
			return 2200;// código para bibliotecario
		}
		if(fachadaService.verificaLoginAdministrador(email, senha)) {
			return 2205;// código para admin
		}
		//caso não encontre nenhum dos dois
		throw new Exception("Usuário e/ou senha inválidos");
	}
	
	// método que emite o token
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

	// método para mapear a resposta para o formato JSON
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
