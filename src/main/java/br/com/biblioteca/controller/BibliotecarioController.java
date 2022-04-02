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

import br.com.biblioteca.model.Bibliotecario;
import br.com.biblioteca.service.BibliotecarioService;

@Path("/bibliotecarios")
public class BibliotecarioController {
	
	@Inject
	private BibliotecarioService bibliotecarioService;
	
	@POST
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response cadastrarBibliotecario(Bibliotecario bibliotecario) {
		try {
			bibliotecarioService.cadastrarBibliotecario(bibliotecario);
			return Response.status(201).build();
		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println(e.getMessage());
		}
		
		return Response.status(400).build();
	}
	
	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response listarBibliotecarios() {
		return Response.ok(bibliotecarioService.listarBibliotecarios()).build();
	}
	
	@GET
	@Path("{cpf}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response buscarPorCpf(@PathParam("cpf") String cpf) {
		Bibliotecario cl = bibliotecarioService.buscarPorCpf(cpf);
		if(cl == null) {
			return Response.status(404).build();
		}
		
		return Response.ok(cl).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response removerBibliotecario(@PathParam("id") Long id) {
		bibliotecarioService.remover(id);
		return Response.ok().build();
	}
}
