package br.com.biblioteca.controller;

import javax.inject.Inject;
import javax.persistence.NoResultException;
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
			return Response.ok("Empr�stimo realizado com sucesso.").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(403).entity(e.getMessage()).build();
		}
	}
	
	@GET
	@Path("{codigoLivro}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response buscarEmprestimoPorCodigoLivro(@PathParam("codigoLivro") String codLivro) {
		
		try {
			Emprestimo empr = emprestimoService.buscarPorCodigoLivro(codLivro);
			
			return Response.ok(empr).build();
		} catch (Exception e) {
			return Response.status(404).entity("Empr�stimo n�o encontrado.").build();
		}
		
	}
	
	@GET
	@Secured
	@Path("devolucao/{codigoLivro}")
	@Produces(value = MediaType.TEXT_PLAIN)
	public Response realizarDevolucao(@PathParam("codigoLivro") String codigoLivro) {
		try {
			emprestimoService.devolverLivro(codigoLivro);
			return Response.ok().entity("Devolu��o registrada com sucesso.").build();
		} catch (Exception e) {
			return Response.status(404).entity(e.getMessage()).build();
		}
	}
	
	@GET
	@Secured
	@Path("renovar/{codigoLivro}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response renovarEmprestimo(@PathParam("codigoLivro") String codigoLivro) {
		Emprestimo emprRenovado = emprestimoService.renovarEmprestimo(codigoLivro);
		return Response.ok(emprRenovado).build();
	}
	
	@GET
	@Path("/quantidade")
	@Produces(value = MediaType.TEXT_PLAIN)
	public Response quantidadeDeEmprestimos() {
		return Response.ok(emprestimoService.contarEmprestimos()).build();
	}
	
	
}
