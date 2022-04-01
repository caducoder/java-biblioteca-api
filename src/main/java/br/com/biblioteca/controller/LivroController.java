package br.com.biblioteca.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.biblioteca.model.Livro;
import br.com.biblioteca.service.LivroService;

@Path("/livros")
public class LivroController {
	
	@Inject
	private LivroService livroService;

	@POST
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response cadastrarLivro(Livro livro) {
		livroService.cadastrar(livro);
		return Response.status(201).build();
	}
	
	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response listarLivros() {
		return Response.ok(livroService.listar()).build();
	}
}
