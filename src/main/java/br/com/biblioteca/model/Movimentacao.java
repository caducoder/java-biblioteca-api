package br.com.biblioteca.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import br.com.biblioteca.utils.TiposMovimentacao;

@Entity
@Table(name = "movimentacoes")
public class Movimentacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "id_usuario")
	private Long idUsuario;
	
	@Column(name = "id_cliente")
	private Long idCliente;
	
	@Column(name = "id_livro")
	private Long idLivro;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_movimentacao")
	private TiposMovimentacao tipoMovimentacao;
	
	@Column(name = "data_hora")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime dataHora;
	
	
	public Movimentacao() {
	}
	
	public Movimentacao(Long idUsuario, Long idCliente, Long idLivro, TiposMovimentacao tipoMovimentacao,
			LocalDateTime dataHora) {
		this.idUsuario = idUsuario;
		this.idCliente = idCliente;
		this.idLivro = idLivro;
		this.tipoMovimentacao = tipoMovimentacao;
		this.dataHora = dataHora;
	}


	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getIdLivro() {
		return idLivro;
	}
	public void setIdLivro(Long idLivro) {
		this.idLivro = idLivro;
	}
	
	public TiposMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}
	public void setTipoMovimentacao(TiposMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}
	
	public LocalDateTime getDataHora() {
		return dataHora;
	}
	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
	
	
}