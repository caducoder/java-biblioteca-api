package br.com.biblioteca.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.biblioteca.model.Emprestimo;
import br.com.biblioteca.service.EmprestimoService;

@Path("/emprestimo")
public class EmprestimoController {

	@Inject
	private EmprestimoService emprestimoService;
	
	@GET
	@Path("{idCliente: [0-9]*}/{codigoLivro}")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response realizarEmprestimo(@PathParam("idCliente") Long idCliente, @PathParam("codigoLivro") String codigoLivro) {
		emprestimoService.realizarEmprestimo(idCliente, codigoLivro);
		return Response.ok().build();
	}
	
	@GET
	@Path("devolucao/{codigoLivro}")
	public Response realizarDevolucao(@PathParam("codigoLivro") String codigoLivro) {
		emprestimoService.devolverLivro(codigoLivro);
		return Response.ok().build();
	}
	
	
	
}
