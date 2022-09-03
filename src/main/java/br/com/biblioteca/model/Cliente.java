package br.com.biblioteca.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class Cliente extends Usuario {
	
	public Cliente() {
	}
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.EAGER)
	private List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
	
	public void adicionarEmprestimo(Emprestimo emp) {
		this.emprestimos.add(emp);
		emp.setCliente(this);
	}

	public List<Emprestimo> getEmprestimos() {
		return emprestimos;
	}
	
	
}
