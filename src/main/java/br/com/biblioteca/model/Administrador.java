package br.com.biblioteca.model;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class Administrador extends Usuario {

	private String senha;
	private BigDecimal salario;
	
	public Administrador() {
	}
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public BigDecimal getSalario() {
		return salario;
	}
	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
}
