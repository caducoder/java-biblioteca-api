package br.com.biblioteca.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

import br.com.biblioteca.model.Administrador;
import br.com.biblioteca.model.Bibliotecario;
import br.com.biblioteca.model.Usuario;
import facade.ServiceFacade;

@Path("/funcionarios")
public class FuncionarioController {

	@Inject
	private ServiceFacade fachadaService;

	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response listarFuncionarios() {
		List<Object> funcionarios = new ArrayList<>();

		Collections.addAll(funcionarios, fachadaService.listarBibliotecarios());
		Collections.addAll(funcionarios, fachadaService.listarAdministradores());

		return Response.ok(funcionarios.toArray()).build();
	}

	@GET
	// @Secured
	@Path("{cpf}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response buscarPorCpf(@PathParam("cpf") String cpf) {
		Bibliotecario biblio = fachadaService.buscarBibliotecarioPeloCPF(cpf);
		if (biblio != null) {
			return Response.ok(biblio).build();
		}

		Administrador adm = fachadaService.buscarAdministradorPeloCPF(cpf);
		if (adm != null) {
			return Response.ok(adm).build();
		}

		return Response.status(404).build();
	}

	@PUT
	// @Secured
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Produces(value = MediaType.TEXT_PLAIN)
	public Response alterarFuncionario(Usuario user) {
		Bibliotecario bbt = fachadaService.buscarBibliotecarioPeloCPF(user.getCpf());
		try {
			if (bbt != null) {
				fachadaService.alterarBibliotecario(bbt, user);
			} else {
				Administrador adm = fachadaService.buscarAdministradorPeloCPF(user.getCpf());
				fachadaService.alterarAdministrador(adm, user);
			}
			return Response.ok("Usuário alterado com sucesso.").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(400).entity(e.getMessage()).build();
		}
	}

	@POST
	// @Secured
	@Path("{idFuncionario}")
	@Consumes(value = MediaType.TEXT_PLAIN)
	@Produces(value = MediaType.TEXT_PLAIN)
	public Response mudarSenha(@PathParam("idFuncionario") Long id, String novaSenha) {
		Bibliotecario bbt = fachadaService.buscarBibliotecarioPeloID(id);

		try {
			if (bbt != null) {
				fachadaService.alterarSenhaBibliotecario(bbt, novaSenha);
			} else {
				Administrador adm = fachadaService.buscarAdministradorPeloID(id);
				fachadaService.alterarSenhaAdministrador(adm, novaSenha);
			}

			return Response.ok("Senha alterada com sucesso.").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(400).entity(e.getMessage()).build();
		}
	}

	@DELETE
	// @Secured
	@Path("{id}")
	public Response removerFuncionario(@PathParam("id") Long id) {
		if (!fachadaService.removerBibliotecario(id)) {
			fachadaService.removerAdministrador(id);
		}
		
		return Response.ok().build();
	}
}
