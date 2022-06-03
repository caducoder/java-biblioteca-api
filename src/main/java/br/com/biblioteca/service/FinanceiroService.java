package br.com.biblioteca.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.biblioteca.dao.FinanceiroDAO;
import br.com.biblioteca.model.FinanceiroForm;

@Stateless
public class FinanceiroService {

	@Inject
	private FinanceiroDAO dao;

	public void salvarFinanca(FinanceiroForm upload) {
		dao.salvar(upload);
	}
	
	public String getNotaFiscal(Long id) {
		return dao.getNotaFiscalName(id);
	}
	
	public List<FinanceiroForm> getFinancas() {
		return dao.listarFinancas();
	}

//	public FinanceiroForm getFinanca(Long id) {
//		return dao.getFinanca(id);
//	}
//
	


}
