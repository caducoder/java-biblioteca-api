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

import br.com.biblioteca.model.Cliente;
import facade.ServiceFacade;

@Path("/clientes")
public class ClienteController {
	
	@Inject
	private ServiceFacade fachadaService;
	
	@POST
	//@Secured
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Produces(value = MediaType.TEXT_PLAIN)
	public Response cadastrarCliente(Cliente cliente) {
		try {
			fachadaService.cadastrarCliente(cliente);
			return Response.status(201).entity("Cliente cadastrado com sucesso").build();
		} catch (Exception e) {
			return Response.status(403).entity(e.getMessage()).build();
		}
	}
	
	@GET
	//@Secured
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response listarClientes() {
		return Response.ok(fachadaService.listarCliente()).build();
	}
	
	@GET
	@Path("/quantidade")
	@Produces(value = MediaType.TEXT_PLAIN)
	public Response quantidadeDeClientes() {
		return Response.ok(fachadaService.quantidadeDeClientes()).build();
	}
	
	@GET
	//@Secured
	@Path("{cpf}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response buscarPorCpf(@PathParam("cpf") String cpf) {
		Cliente cl;
		try {
			cl = fachadaService.buscarClientePeloCPF(cpf);
			
			return Response.ok(cl).build();
		} catch (Exception e) {
			return Response.status(404).entity(e.getMessage()).build();
		}
	}
	
	@GET
	//@Secured
	@Path("/cl/{id}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response buscarPorId(@PathParam("id") Long id) {
		Cliente cl = fachadaService.buscarClientePeloID(id);
		
		if(cl != null) {
			return Response.ok(cl).build();
		} else {
			return Response.status(404).entity("{\"error\": \"Cliente não encontrado.\"}").build();
		}
	}
	
	@PUT
	//@Secured
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Produces(value = MediaType.TEXT_PLAIN)
	public Response alterarCliente(Cliente nCliente) {
		try {
			fachadaService.alterarCliente(nCliente);
			return Response.ok("Cliente atualizado com sucesso.").build();
		} catch (Exception e) {
			return Response.status(400).entity(e.getMessage()).build();
		}
	}
	
	@DELETE
	//@Secured
	@Path("{id}")
	public Response removerCliente(@PathParam("id") Long id) {
		fachadaService.removerCliente(id);
		return Response.ok().build();
	}
	
}
