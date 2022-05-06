package br.com.biblioteca.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import br.com.biblioteca.utils.CustomLocalDateSerializer;

@Entity
@Table(name = "reservas")
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name="livro_id")
	private Livro livro;
	
	@Column(name="reservado_por_cpf")
	private String reservadoPorCpf;
	
	@Column(name="expira_em")
	@JsonSerialize(using = CustomLocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate expiraEm;
	
	public Reserva() {
	}
	
	public Reserva(Livro livro, String reservadoPorCpf, LocalDate expiraEm) {
		this.livro = livro;
		this.reservadoPorCpf = reservadoPorCpf;
		this.expiraEm = expiraEm;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	public String getReservadoPorCpf() {
		return reservadoPorCpf;
	}
	public void setReservadoPorCpf(String reservadoPorCpf) {
		this.reservadoPorCpf = reservadoPorCpf;
	}
	public LocalDate getExpiraEm() {
		return expiraEm;
	}
	public void setExpiraEm(LocalDate expiraEm) {
		this.expiraEm = expiraEm;
	}
	
}
