package br.com.biblioteca.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.UUID;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.com.biblioteca.Secured;
import br.com.biblioteca.model.FinanceiroForm;
import br.com.biblioteca.service.FinanceiroService;
import br.com.biblioteca.utils.PdfFileInfo;


@Path("/financeiro")
public class FinanceiroController {
	
	private static final String BASE_DIR = "C:\\files\\";
	
	@Inject
	private FinanceiroService financeiroService;
	
	// cria pasta para guardar os pdfs, caso nao exista
	{
		File f = new File(BASE_DIR);
		if(!f.exists()) {
			f.mkdir();
		}
	}
	
	@POST
	//@Secured
	@Path("/upload")
	@Consumes(value = MediaType.MULTIPART_FORM_DATA)
	@Produces(value = MediaType.TEXT_PLAIN)
	public Response uploadNotaFiscal(@MultipartForm PdfFileInfo formData) {
		
		UUID uuid = UUID.randomUUID();
		
		String nomeArq = uuid.toString();
		
		String caminho_pasta = BASE_DIR + nomeArq + ".pdf";
		
		try {
			// salvando pdf na pasta
			writeFile(formData.getDados(), caminho_pasta);
	
			FinanceiroForm fin = new FinanceiroForm(nomeArq, formData.getAssunto(), formData.getTipoOperacao(), formData.getValor(), LocalDate.now());

			//salvando dados do financeiro no banco de dados
			financeiroService.salvarFinanca(fin);
			
			return Response.status(200)
				    .entity("Financeiro registrado com sucesso.").build();
		} catch (IOException | NullPointerException e) {
			
			e.printStackTrace();
			return Response.status(400).entity(e.getMessage()).build();
		}

	}
	
	@GET
	//@Secured
	@Path("/download/{id}")
	@Produces("application/pdf")
	public Response downloadNotaFiscal(@PathParam("id") Long id) {
		String nomeDoArquivo = financeiroService.getNotaFiscal(id);
		File file = new File(BASE_DIR + nomeDoArquivo + ".pdf");
		try {
			byte[] bytes = Files.readAllBytes(file.toPath());
			ResponseBuilder responseBuilder = Response.ok(bytes);
			responseBuilder.type("application/pdf");
			responseBuilder.header("Acces-Control-Allow-Origin", "*");
			responseBuilder.header("Access-Control-Allow-Methods", "GET");
		    responseBuilder.header(
		          "Access-Control-Allow-Headers",
		          "X-Requested-With,Host,User-Agent,Accept,Accept-Language,Accept-Encoding,Accept-Charset,Keep-Alive,Connection,Referer,Origin");
			responseBuilder.header("Content-Disposition", "filename="+nomeDoArquivo+".pdf");
			
			return responseBuilder.build();
		} catch (IOException e) {
			e.printStackTrace();
			return Response.status(500).entity(e.getMessage()).build();
		}
	}
	
	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response listarNotas() {
		return Response.ok(financeiroService.getFinancas()).build();
	}
	
	private void writeFile(byte[] content, String filename) throws IOException {

		File file = new File(filename);

		if (!file.exists()) {
			file.createNewFile();
		}

		FileOutputStream fop = new FileOutputStream(file);
		
		fop.write(content);
		fop.flush();
		fop.close();

	}
}
