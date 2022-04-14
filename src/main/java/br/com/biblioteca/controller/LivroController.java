package br.com.biblioteca.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
	
	@GET
	@Path("{isbn}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response buscarPorIsbn(@PathParam("isbn") String isbn) {
		Livro lvr = livroService.buscarLivroPorIsbn(isbn);
		
		if(lvr == null) {
			return Response.status(404).build();
		}
		
		return Response.ok(lvr).build();
	}
	
	@PUT
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response alterarLivro(Livro nLivro) {
		livroService.alterar(nLivro);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("{idLivro}")
	public Response removerLivro(@PathParam("idLivro") Long idLivro) {
		livroService.remover(idLivro);
		return Response.ok().build();
	}
}
