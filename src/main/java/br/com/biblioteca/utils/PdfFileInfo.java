package br.com.biblioteca.utils;

import java.math.BigDecimal;

import javax.ws.rs.FormParam;

import org.jboss.resteasy.annotations.providers.multipart.PartType;


public class PdfFileInfo {

	@FormParam("filename")
	private String filename;
	
	@FormParam("valor")
	private BigDecimal valor;
	
	@FormParam("tipo")
	private String tipo;
	
	private byte[] dados;
	
	public PdfFileInfo() {
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public byte[] getDados() {
		return dados;
	}

	@FormParam("file")
	@PartType("application/octet-stream")
	public void setDados(byte[] dados) {
		this.dados = dados;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getTipoOperacao() {
		return tipo;
	}

	public void setTipoOperacao(String tipo) {
		this.tipo = tipo;
	}
	
	
}
