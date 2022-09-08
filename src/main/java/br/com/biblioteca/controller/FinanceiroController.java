package br.com.biblioteca.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.com.biblioteca.utils.PdfFileInfo;
import facade.ServiceFacade;


@Path("/financeiro")
public class FinanceiroController {
	
	@Inject
	private ServiceFacade fachadaService;
	
	@POST
	//@Secured
	@Path("/upload")
	@Consumes(value = MediaType.MULTIPART_FORM_DATA)
	@Produces(value = MediaType.TEXT_PLAIN)
	public Response uploadNotaFiscal(@MultipartForm PdfFileInfo formData) {
		try {
			fachadaService.salvarFinanca(formData);
			
			return Response.status(200)
				    .entity("Financeiro registrado com sucesso.").build();
		} catch (Exception e) {
			return Response.status(400).entity(e.getMessage()).build();
		}
	}
	
	@GET
	//@Secured
	@Path("/download/{id}")
	@Produces("application/pdf")
	public Response downloadNotaFiscal(@PathParam("id") Long id) {
		try {
			return fachadaService.buscarNotaFiscalPeloId(id).build();
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}
	
	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response listarNotas() {
		return Response.ok(fachadaService.listarFinanceiro()).build();
	}
	
	
}
