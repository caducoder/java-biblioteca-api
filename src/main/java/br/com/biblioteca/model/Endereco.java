package br.com.biblioteca.model;

import javax.persistence.Embeddable;

@Embeddable
public class Endereco {

	private String rua;
	private Long numero;
	private String bairro;
	private String cidade;
	private String cep;
	
	public Endereco() {
	}
	
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	@Override
	public String toString() {
		return "Endereco [rua=" + rua + ", numero=" + numero + ", bairro=" + bairro + ", cidade=" + cidade + ", cep="
				+ cep + "]";
	}
}
