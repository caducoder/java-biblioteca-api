package br.com.biblioteca.controller;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.biblioteca.Secured;
import br.com.biblioteca.model.Emprestimo;
import br.com.biblioteca.service.EmprestimoService;

@Path("/emprestimo")
public class EmprestimoController {

	@Inject
	private EmprestimoService emprestimoService;
	
	@GET
	@Secured
	@Path("{idCliente: [0-9]*}/{codigoLivro}")
	@Produces(value = MediaType.TEXT_PLAIN)
	public Response realizarEmprestimo(@PathParam("idCliente") Long idCliente, @PathParam("codigoLivro") String codigoLivro) {
		try {
			emprestimoService.realizarEmprestimo(idCliente, codigoLivro);
			return Response.ok("Empréstimo realizado com sucesso.").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(403).entity(e.getMessage()).build();
		}
		
	}
	
	@GET
	@Secured
	@Path("devolucao/{codigoLivro}")
	public Response realizarDevolucao(@PathParam("codigoLivro") String codigoLivro) {
		emprestimoService.devolverLivro(codigoLivro);
		return Response.ok().build();
	}
	
	@GET
	@Secured
	@Path("renovar/{codigoLivro}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response renovarEmprestimo(@PathParam("codigoLivro") String codigoLivro) {
		Emprestimo emprRenovado = emprestimoService.renovarEmprestimo(codigoLivro);
		return Response.ok(emprRenovado).build();
	}
	
}
