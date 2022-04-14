package br.com.biblioteca;

import java.io.IOException;
import java.security.PublicKey;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import br.com.biblioteca.controller.LoginController;
import io.jsonwebtoken.Jwts;

@Provider
@Authorize
@Priority(Priorities.AUTHENTICATION)
public class AuthorizeFilter implements ContainerRequestFilter {
	
	PublicKey CHAVE_PUBLICA = LoginController.CHAVE_PUBLICA;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

		try {
			if(CHAVE_PUBLICA == null) {
				throw new Exception("Chave pública null");
			}
			String token = authHeader.substring("Bearer".length()).trim();
			
			Jwts.parserBuilder()
				.setSigningKey(CHAVE_PUBLICA)
				.build()
				.parseClaimsJws(token);
		} catch (Exception e) {
			e.printStackTrace();
			requestContext
				.abortWith(Response.status(Response.Status.UNAUTHORIZED)
				.build());
		}
		
	}

}
