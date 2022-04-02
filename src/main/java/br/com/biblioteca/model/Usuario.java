package br.com.biblioteca.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@NotNull
	private String cpf;
	private String rg;
	@Embedded
	private Endereco endereco;
	@NotNull
	private String email;
	private String telefone;
	
	public Usuario() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco end) {
		this.endereco = end;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", cpf=" + cpf + ", rg=" + rg + ", endereco=" + endereco + ", email=" + email
				+ ", telefone=" + telefone + "]";
	}
	
}
