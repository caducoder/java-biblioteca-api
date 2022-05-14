package br.com.biblioteca.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.biblioteca.Secured;
import br.com.biblioteca.model.Cliente;
import br.com.biblioteca.service.ClienteService;

@Path("/clientes")
public class ClienteController {
	
	@Inject
	private ClienteService clienteService;
	
	@POST
	@Secured
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Produces(value = MediaType.TEXT_PLAIN)
	public Response cadastrarCliente(Cliente cliente) {
		try {
			clienteService.cadastrarCliente(cliente);
			return Response.status(201).entity("Cliente cadastrado com sucesso").build();
		} catch (Exception e) {
			return Response.status(403).entity(e.getMessage()).build();
		}
		
	}
	
	@GET
	@Secured
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response listarClientes() {
		return Response.ok(clienteService.listarClientes()).build();
	}
	
	@GET
	@Secured
	@Path("{cpf}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response buscarPorCpf(@PathParam("cpf") String cpf) {
		Cliente cl = clienteService.buscarPorCpf(cpf);
		if(cl == null) {
			return Response.status(404).build();
		}
		
		return Response.ok(cl).build();
	}
	
	@PUT
	@Secured
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response alterarCliente(Cliente nCliente) {
		clienteService.alterar(nCliente);
		return Response.ok().build();
	}
	
	@DELETE
	@Secured
	@Path("{id}")
	public Response removerCliente(@PathParam("id") Long id) {
		clienteService.remover(id);
		return Response.ok().build();
	}
	
	
}
