package br.com.biblioteca.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "financas")
public class FinanceiroForm {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name ="nome_arquivo")
	private String fileName;
	
	@Column(name = "tipo_operacao")
	private String tipoOperacao;

	private BigDecimal valor;
	
	private LocalDate data;

	public FinanceiroForm() {
	}
	
	public FinanceiroForm(String fileName, String tipoOperacao, BigDecimal valor, LocalDate data) {
		this.fileName = fileName;
		this.tipoOperacao = tipoOperacao;
		this.valor = valor;
		this.data = data;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(String tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
	

}