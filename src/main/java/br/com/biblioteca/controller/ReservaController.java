package br.com.biblioteca.controller;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.biblioteca.service.ReservaService;

@Path("/reservas")
public class ReservaController {

	@Inject
	private ReservaService reservaService;
	
	@GET
	@Path("/quantidade")
	@Produces(value = MediaType.TEXT_PLAIN)
	public Response quantidadeDeReservas() {
		return Response.ok(reservaService.contarReservas()).build();
	}
}
