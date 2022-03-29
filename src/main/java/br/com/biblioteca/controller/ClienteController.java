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

import br.com.biblioteca.model.Cliente;
import br.com.biblioteca.service.ClienteService;

@Path("/clientes")
public class ClienteController {
	
	@Inject
	private ClienteService clienteService;
	
	@POST
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response cadastrarCliente(Cliente cliente) {
		try {
			clienteService.cadastrarCliente(cliente);
			return Response.status(201).build();
		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println(e.getMessage());
		}
		
		return Response.status(400).build();
	}
	
	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response listarClientes() {
		return Response.ok(clienteService.listarClientes()).build();
	}
	
	@GET
	@Path("{cpf}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response buscarPorCpf(@PathParam("cpf") String cpf) {
		Cliente cl = clienteService.buscarPorCpf(cpf);
		if(cl == null) {
			return Response.status(404).build();
		}
		
		return Response.ok(cl).build();
	}
}
