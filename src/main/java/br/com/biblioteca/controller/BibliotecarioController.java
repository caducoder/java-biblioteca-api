package br.com.biblioteca.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.biblioteca.Secured;
import br.com.biblioteca.model.Bibliotecario;
import br.com.biblioteca.service.BibliotecarioService;
import facade.ServiceFacade;

@Path("/bibliotecarios")
public class BibliotecarioController {
	
	@Inject
	private ServiceFacade fachadaService;
	
	@POST
	//@Secured
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Produces(value = MediaType.TEXT_PLAIN)
	public Response cadastrarBibliotecario(Bibliotecario bibliotecario) {
		try {
			fachadaService.cadastrarBibliotecario(bibliotecario);
			return Response.status(201).entity("Funcionário cadastrado com sucesso!").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(400).entity(e.getMessage()).build();
		}
	}
	
	@GET
	//@Secured
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response listarBibliotecarios() {
		return Response.ok(fachadaService.listarBibliotecarios()).build();
	}
	
	@GET
	//@Secured
	@Path("{cpf}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response buscarPorCpf(@PathParam("cpf") String cpf) {
		Bibliotecario cl = fachadaService.buscarBibliotecarioPeloCPF(cpf);
		
		if(cl == null) {
			return Response.status(404).build();
		}
		
		return Response.ok(cl).build();
	}
	
	@DELETE
	//@Secured
	@Path("{id}")
	public Response removerBibliotecario(@PathParam("id") Long id) {
		fachadaService.removerBibliotecario(id);
		
		return Response.ok().build();
	}
}
