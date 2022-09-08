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
import javax.ws.rs.core.Response.Status;

import br.com.biblioteca.model.Livro;
import facade.ServiceFacade;

@Path("/livros")
public class LivroController {

	@Inject
	private ServiceFacade fachadaService;

	@POST
	// @Secured
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response cadastrarLivro(Livro livro) {
		try {
			fachadaService.cadastrarLivro(livro);
			return Response.status(201).entity("Livro cadastrado com sucesso!").build();
		} catch (Exception e) {
			return Response.status(403).entity(e.getMessage()).build();
		}
	}

	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response listarLivros() {
		return Response.ok(fachadaService.listarLivros()).build();
	}

	@GET
	@Path("/quantidade")
	@Produces(value = MediaType.TEXT_PLAIN)
	public Response quantidadeDeLivros() {
		return Response.ok(fachadaService.quantidadeDeLivros()).build();
	}

	@GET
	@Path("{codigo}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response buscarPorCodigo(@PathParam("codigo") String codigo) {
		Livro lvr = fachadaService.buscarLivroPeloCodigo(codigo);

		if (lvr == null) {
			return Response.status(404).build();
		}

		return Response.ok(lvr).build();
	}

	@GET
	@Path("{idLivro: [0-9]*}/{cpf}")
	@Produces(value = MediaType.TEXT_PLAIN)
	public Response reservarLivro(@PathParam("idLivro") Long idLivro, @PathParam("cpf") String cpf) {
		try {
			fachadaService.reservarLivro(idLivro, cpf);

			return Response.ok("Livro reservado com sucesso!").build();
		} catch (Exception e) {
			return Response.status(Status.FORBIDDEN).entity(e.getMessage()).build();
		}
	}

	@PUT
	// @Secured
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response alterarLivro(Livro nLivro) {
		fachadaService.alterarLivro(nLivro);
		return Response.ok().build();
	}

	@DELETE
	// @Secured
	@Path("{idLivro}")
	public Response removerLivro(@PathParam("idLivro") Long idLivro) {
		try {
			fachadaService.removerLivro(idLivro);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(400).entity(e.getMessage()).build();
		}
	}
}
