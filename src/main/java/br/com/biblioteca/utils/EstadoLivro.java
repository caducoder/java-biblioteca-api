package br.com.biblioteca.utils;

public enum EstadoLivro {
	DISPONIVEl("Disponível"),
	RESERVADO("Reservado"),
	EMPRESTADO("Emprestado");

	private String descricao;
	
	EstadoLivro(String string) {
		this.descricao = string;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	@Override
	public String toString() {
		return this.descricao;
	}
}
