package br.com.biblioteca.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.biblioteca.dao.DAOFacade;
import br.com.biblioteca.dao.FinanceiroDAO;
import br.com.biblioteca.model.FinanceiroForm;

@Stateless
public class FinanceiroService {

	@Inject
	private DAOFacade fachada;

	public void salvarFinanca(FinanceiroForm upload) {
		fachada.salvarFinanca(upload);
	}
	
	public String getNotaFiscal(Long id) {
		return fachada.buscarNomeNotaFiscal(id);
	}
	
	public List<FinanceiroForm> getFinancas() {
		return fachada.listarFinanca();
	}

}
