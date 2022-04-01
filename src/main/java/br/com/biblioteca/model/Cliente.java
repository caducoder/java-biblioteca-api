package br.com.biblioteca.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@Entity
public class Cliente extends Usuario {
	
	public Cliente() {
	}
	
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
