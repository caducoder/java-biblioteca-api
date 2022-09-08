package br.com.biblioteca.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.biblioteca.model.Emprestimo;
import facade.ServiceFacade;

@Path("/emprestimo")
public class EmprestimoController {

	@Inject
	private ServiceFacade fachadaService;

	@GET
	// @Secured
	@Path("{idCliente: [0-9]*}/{codigoLivro}")
	@Produces(value = MediaType.TEXT_PLAIN)
	public Response realizarEmprestimo(@PathParam("idCliente") Long idCliente,
			@PathParam("codigoLivro") String codigoLivro) {
		try {
			fachadaService.realizarEmprestimo(idCliente, codigoLivro);
			return Response.ok("Empréstimo realizado com sucesso.").build();
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
			Emprestimo empr = fachadaService.buscarEmprestimoPeloCodigoLivro(codLivro);

			return Response.ok(empr).build();
		} catch (Exception e) {
			return Response.status(404).entity("Empréstimo não encontrado.").build();
		}
	}

	@GET
	@Path("cliente/{id}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response buscarEmprestimosPorCliente(@PathParam("id") Long idCliente) {

		List<Emprestimo> emprestimos = fachadaService.buscarEmprestimosPeloCliente(idCliente);

		return Response.ok(emprestimos).build();

	}

	@GET
	// @Secured
	@Path("devolucao/{codigoLivro}")
	@Produces(value = MediaType.TEXT_PLAIN)
	public Response realizarDevolucao(@PathParam("codigoLivro") String codigoLivro) {
		try {
			fachadaService.realizarDevolucao(codigoLivro);
			return Response.ok().entity("Devolução registrada com sucesso.").build();
		} catch (Exception e) {
			return Response.status(404).entity(e.getMessage()).build();
		}
	}

	@GET
	// @Secured
	@Path("renovar/{codigoLivro}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response renovarEmprestimo(@PathParam("codigoLivro") String codigoLivro) {
		Emprestimo emprRenovado = fachadaService.renovarEmprestimo(codigoLivro);

		return Response.ok(emprRenovado).build();
	}

	@GET
	@Path("/quantidade")
	@Produces(value = MediaType.TEXT_PLAIN)
	public Response quantidadeDeEmprestimos() {
		return Response.ok(fachadaService.quantidadeDeEmprestimos()).build();
	}

}
