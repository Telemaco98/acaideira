package com.ufrn.imd.acaideira.domain;

public class Product{

	private int id;
	
	private int quantidade;
	
	private double price;
	
	private String nome;
	
	private int idRestaurante;
		
	public Product() {
		
	}
	
	public Product(int quantidade, String nome, double price) {
		this.quantidade = quantidade;
		this.nome = nome;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdRestaurante() {
		return idRestaurante;
	}

	public void setIdRestaurante(int idRestaurante) {
		this.idRestaurante = idRestaurante;
	}
	
	public String toString() {
		return "Id: " + this.getId() + " Nome: " +  this.getNome() + " Quantidade: "+ this.getQuantidade() + " Preco: "+ this.getPrice();
	}
	
}
