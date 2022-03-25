package br.com.biblioteca.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api")
public class teste {

	@GET
	@Produces(value = MediaType.TEXT_HTML)
	public Response responder() {
		return Response.ok("<h1>Hello World!</h1>").build();
	}
}
