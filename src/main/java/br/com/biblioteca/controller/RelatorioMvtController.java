package br.com.biblioteca.controller;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import facade.ServiceFacade;

@Path("/relatorios")
public class RelatorioMvtController {

	@Inject
	private ServiceFacade fachadaService;
	
	@GET
	@Path("/geral")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response relatorioGeral() {
		return Response.ok(fachadaService.listarMovimentacoes()).build();
	}
	
	@GET
	@Path("/livro")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response relatorioLivro() {
		return Response.ok(fachadaService.listarMovimentacoesLivro()).build();
	}
	
	@GET
	@Path("/cliente")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response relatorioCliente() {
		return Response.ok(fachadaService.listarMovimentacoesCliente()).build();
	}
	
	@GET
	@Path("/emprestimo")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response relatorioEmprestimo() {
		return Response.ok(fachadaService.listarMovimentacoesEmprestimo()).build();
	}
	
	@GET
	@Path("/reserva")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response relatorioReserva() {
		return Response.ok(fachadaService.listarMovimentacoesReserva()).build();
	}
	
}
