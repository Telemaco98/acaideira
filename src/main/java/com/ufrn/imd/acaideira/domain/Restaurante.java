package com.ufrn.imd.acaideira.domain;

public class Restaurante{

	private int id_restaurante;
	
	private String endereco;
	
	private String nome;
	
	private String tipo;
	
	public Restaurante() {
		
	}
	
	public Restaurante(String endereco, String nome, String tipo) {
		this.endereco = endereco;
		this.nome = nome;
		this.tipo = tipo;
	}
		
	public int getId_restaurante() {
		return id_restaurante;
	}

	public void setId_restaurante(int id_restaurante) {
		this.id_restaurante = id_restaurante;
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
