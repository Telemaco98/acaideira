package com.ufrn.imd.acaideira.domain;

public class Restaurant{

	private int id;
	
	private String endereco;
	
	private String nome;
	
	private String tipo;
	
	public Restaurant() {
		
	}
	
	public Restaurant(String endereco, String nome, String tipo) {
		this.endereco = endereco;
		this.nome = nome;
		this.tipo = tipo;
	}
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
