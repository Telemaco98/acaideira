package com.ufrn.imd.acaideira.domain;

public class Restaurant{

	private int id;
	
	private String endereco;
	
	private String nome;
	
	private String tipo;

	private String email;

	private String password;
	
	public Restaurant() {
		
	}
	
	public Restaurant(String endereco, String nome, String tipo, String email, String password) {
		this.endereco = endereco;
		this.nome = nome;
		this.email = email;
		this.password = password;
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
		
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String toString() {
		return  "Id: " + this.getId() + " Nome: " +  this.getNome() + " Tipo: "+ this.getTipo() + " Endereco: "+ this.getEndereco();
	}
	
}
