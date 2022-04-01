package br.com.biblioteca.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.biblioteca.utils.EstadoLivro;
import br.com.biblioteca.utils.Idioma;

@Entity
@Table(name = "livros")
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String isbn;
	private String issn;
	private String doi;
	private String autor;
	private String titulo;
	@Enumerated(EnumType.STRING)
	private Idioma idioma;
	private String descricao;
	@Column(name="numero_paginas")
	private int numeroDePaginas = 50;
	@Column(name="ano_edicao")
	private int anoEdicao;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "estado_livro")
	private EstadoLivro estadoLivro = EstadoLivro.DISPONIVEL;
	
	@OneToOne(mappedBy = "livro")
	private Emprestimo emprestimo;

	public Emprestimo getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Idioma getIdioma() {
		return idioma;
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getNumeroDePaginas() {
		return numeroDePaginas;
	}

	public void setNumeroDePaginas(int numeroDePaginas) {
		this.numeroDePaginas = numeroDePaginas;
	}

	public int getAnoEdicao() {
		return anoEdicao;
	}

	public void setAnoEdicao(int anoEdicao) {
		this.anoEdicao = anoEdicao;
	}

	public EstadoLivro getEstadoLivro() {
		return estadoLivro;
	}

	public void setEstadoLivro(EstadoLivro estadoLivro) {
		this.estadoLivro = estadoLivro;
	}

	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getDoi() {
		return doi;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}

	@Override
	public String toString() {
		return "Livro [autor=" + autor + ", titulo=" + titulo + ", idioma=" + idioma + ", numeroDePaginas="
				+ numeroDePaginas + ", anoEdicao=" + anoEdicao + ", estadoLivro=" + estadoLivro + "]";
	}
	
	
}
