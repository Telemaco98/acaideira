package com.ufrn.imd.acaideira.domain;

public class Fornecedor{

	private int id_fornecedor;
	
	private String nome;
		
	public Fornecedor(String nome) {
		this.nome = nome;
	}
	
	public int getId_fornecedor() {
		return id_fornecedor;
	}

	public void setId_fornecedor(int id_fornecedor) {
		this.id_fornecedor = id_fornecedor;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
}
