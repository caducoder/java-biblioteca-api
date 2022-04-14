package br.com.biblioteca.controller;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
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

import br.com.biblioteca.model.Bibliotecario;
import br.com.biblioteca.service.BibliotecarioService;
import br.com.biblioteca.utils.AuthResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Path("/login")
public class LoginController {

	//não funcionou dessa forma
//	private final SecretKey CHAVE = Keys.hmacShaKeyFor(
//			"kwfo2fl!vbtg$3d"
//			.getBytes(StandardCharsets.UTF_8));
	
	PrivateKey CHAVE_PRIVADA;
	public static PublicKey CHAVE_PUBLICA;
	
	{
		try {
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(2048);
			KeyPair kp = kpg.generateKeyPair();
			CHAVE_PRIVADA = kp.getPrivate();
			CHAVE_PUBLICA = kp.getPublic();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	@Inject
	private BibliotecarioService bbtService;
	
	@POST
	@Path("/biblio")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response autenticarBiblio(Bibliotecario biblio) {
		AuthResponse authParams = new AuthResponse();
		try {
			if (bbtService.loginBiblio(biblio.getEmail(), biblio.getSenha())) {
				String jwtToken = Jwts.builder().setSubject(biblio.getEmail()).setIssuer("localhost:8080")
						.setIssuedAt(new Date())
						.setExpiration(Date
								.from(LocalDateTime.now().plusMinutes(10L).atZone(ZoneId.systemDefault()).toInstant()))
						.signWith(CHAVE_PRIVADA, SignatureAlgorithm.RS256).compact();
				
				authParams.setAccessToken(jwtToken);
				authParams.addRole("2251");
				authParams.addRole("1925");

				return Response.status(200).entity(objectToJSON(authParams)).build();
			} else {
				return Response.status(Response.Status.UNAUTHORIZED).entity("Usuário e/ou senha inválidos").build();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
		}
	}

//	@POST
//	@Path("/admin")
//	@Produces(MediaType.TEXT_PLAIN)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response autenticarAdmin(Administrador admin) {
//		
//	}
	
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
