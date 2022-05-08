package br.com.biblioteca.controller;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.biblioteca.service.AdminService;
import br.com.biblioteca.service.BibliotecarioService;
import br.com.biblioteca.utils.AuthResponse;
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
	@Path("/biblio")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response autenticarBiblio(@FormParam("email") String email, @FormParam("senha") String senha) {
		AuthResponse authParams = new AuthResponse();
		try {
			
			authParams.addRole(authenticate(email, senha));
			
			String token = issueToken(email);

			authParams.setAccessToken(token);
			

			return Response.status(200).entity(objectToJSON(authParams)).build();

			//return Response.status(Response.Status.UNAUTHORIZED).entity("Usuário e/ou senha inválidos").build();

		} catch (Exception ex) {
			return Response.status(Response.Status.FORBIDDEN).entity(ex.getMessage()).build();
		}
	}
	
	private String authenticate(String email, String senha) throws Exception {
		
		if(bibliotecarioService.loginBiblio(email, senha)) {
			return "2200";
		}
		if(adminService.loginAdmin(email, senha)) {
			return "2205";
		}
		throw new Exception("Usuário e/ou senha inválidos");
	}
	
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
