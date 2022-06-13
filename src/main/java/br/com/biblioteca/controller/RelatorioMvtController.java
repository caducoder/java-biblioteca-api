package br.com.biblioteca.controller;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.biblioteca.dao.MovimentacaoDAO;

@Path("/relatorios")
public class RelatorioMvtController {

	@Inject
	private MovimentacaoDAO mvtDao;
	
	@GET
	@Path("/geral")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response relatorioGeral() {
		return Response.ok(mvtDao.listar()).build();
	}
	
	@GET
	@Path("/livro")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response relatorioLivro() {
		return Response.ok(mvtDao.listaRelatorioLivro()).build();
	}
	
	@GET
	@Path("/cliente")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response relatorioCliente() {
		return Response.ok(mvtDao.listaRelatorioCliente()).build();
	}
	
	@GET
	@Path("/emprestimo")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response relatorioEmprestimo() {
		return Response.ok(mvtDao.listaRelatorioEmprestimo()).build();
	}
	
	
}
