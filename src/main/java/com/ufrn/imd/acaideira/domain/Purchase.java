package com.ufrn.imd.acaideira.domain;

public class Purchase {
	private int id;
	private double price;
	
	public Purchase() {
		
	}
	
	public Purchase(double price) {
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
	
	public String toString() {
		return "Id: "+ this.id + " Price: " + this.price;
	}
	
}
