package br.com.biblioteca.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movimentacoes")
public class Movimentacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_movimentacao;
	private Long id_usuario;
	private Long id_cliente;
	private String isbn;
	private String issn;
	private String doi;
	private String tipo_movimentacao;
	private LocalDateTime data;
	private LocalDateTime hora;
	
	
	public Long getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}
	
	public Long getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}
	
	public Long getId_movimentacao() {
		return id_movimentacao;
	}
	public void setId_movimentacao(Long id_movimentacao) {
		this.id_movimentacao = id_movimentacao;
	}
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public String getIssn() {
		return issn;
	}
	public void setIssn(String issn) {
		this.issn = issn;
	}
	
	public String getDoi() {
		return doi;
	}
	public void setDoi(String doi) {
		this.doi = doi;
	}
	
	public String getTipo_movimentacao() {
		return tipo_movimentacao;
	}
	public void setTipo_movimentacao(String tipo_movimentacao) {
		this.tipo_movimentacao = tipo_movimentacao;
	}
	
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	
	public LocalDateTime getHora() {
		return hora;
	}
	public void setHora(LocalDateTime hora) {
		this.hora = hora;
	}
}