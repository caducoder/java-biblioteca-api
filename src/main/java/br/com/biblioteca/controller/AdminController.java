package br.com.biblioteca.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.biblioteca.model.Administrador;
import br.com.biblioteca.service.AdminService;

@Path("/admin")
public class AdminController {

	@Inject
	private AdminService adminService;
	
	@POST
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response cadastrarAdmin(Administrador admin) {
		adminService.cadastrar(admin);
		return Response.ok().build();
	}
}
