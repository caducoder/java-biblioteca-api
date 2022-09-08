package br.com.biblioteca.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.biblioteca.model.Administrador;
import facade.ServiceFacade;

@Path("/admin")
public class AdminController {

	@Inject
	private ServiceFacade fachadaService;
	
	@POST
	//@Secure
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Produces(value = MediaType.TEXT_PLAIN)
	public Response cadastrarAdmin(Administrador admin) {
		try {
			fachadaService.cadastrarAdministrador(admin);
			return Response.status(201).entity("Usuário cadastrado com sucesso!").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(400).entity(e.getMessage()).build();
		}
		
	}
}
