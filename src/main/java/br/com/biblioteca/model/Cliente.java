package br.com.biblioteca.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


@Entity
public class Cliente extends Usuario {
	
	public Cliente() {
	}
	
	@OneToMany(mappedBy = "cliente_id", cascade = CascadeType.ALL, orphanRemoval = false)
	private List<Livro> emprestimos = new ArrayList<>();
	
}
