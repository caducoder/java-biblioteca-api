package br.com.biblioteca.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import br.com.biblioteca.model.FinanceiroForm;
import br.com.biblioteca.utils.PdfFileInfo;
import facade.DAOFacade;

@Stateless
public class FinanceiroService {

	private static final String BASE_DIR = "C:\\files\\";

	// cria pasta para guardar os pdfs, caso nao exista
	{
		File f = new File(BASE_DIR);
		if (!f.exists()) {
			f.mkdir();
		}
	}

	@Inject
	private DAOFacade fachada;

	public void salvarFinanca(PdfFileInfo formData) throws Exception {
		UUID uuid = UUID.randomUUID();

		String nomeArq = uuid.toString();

		String caminho_pasta = BASE_DIR + nomeArq + ".pdf";

		try {
			// salvando pdf na pasta
			writeFile(formData.getDados(), caminho_pasta);

			FinanceiroForm fin = new FinanceiroForm(nomeArq, formData.getAssunto(), formData.getTipoOperacao(),
					formData.getValor(), LocalDate.now());

			// salvando dados do financeiro no banco de dados
			fachada.salvarFinanca(fin);

		} catch (IOException | NullPointerException e) {
			throw new Exception("Erro ao salvar documento.");
		}

	}

	public ResponseBuilder getNotaFiscal(Long id) throws Exception {
		String nomeDoArquivo = fachada.buscarNomeNotaFiscal(id);
		File file = new File(BASE_DIR + nomeDoArquivo + ".pdf");

		try {
			byte[] bytes = Files.readAllBytes(file.toPath());
			ResponseBuilder responseBuilder = Response.ok(bytes);
			responseBuilder.type("application/pdf");
			responseBuilder.header("Acces-Control-Allow-Origin", "*");
			responseBuilder.header("Access-Control-Allow-Methods", "GET");
			responseBuilder.header("Access-Control-Allow-Headers",
					"X-Requested-With,Host,User-Agent,Accept,Accept-Language,Accept-Encoding,Accept-Charset,Keep-Alive,Connection,Referer,Origin");
			responseBuilder.header("Content-Disposition", "filename=" + nomeDoArquivo + ".pdf");

			return responseBuilder;
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("Erro ao buscar nota fiscal.");
		}
	}

	public List<FinanceiroForm> getFinancas() {
		return fachada.listarFinanca();
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
