package com.example.demo.prova;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column()
	private int numeroFacce;

	public Dado() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumero_facce() {
		return numeroFacce;
	}

	public void setNumero_facce(int numero_facce) {
		this.numeroFacce = numero_facce;
	}
	
	
}
