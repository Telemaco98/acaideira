package com.ufrn.imd.acaideira.domain;

import com.ufrn.imd.acaideira.enums.ProductType;

/**
 * @author Shirley Ohara (shirleyohara@ufrn.edu.br)
 */
public class Product {
	private int    		 idProduct;
	private double 		 price;
	private String 		 name;
	private int    		 amount;
	private ProductType  type;
	private String 		 description;
	private int    		 idRestaurant;

	/**
	 * Default Constructor
	 */
	public Product() { }

	/**
	 * Convenience constructor
	 * @param idProduct
	 * @param price
	 * @param name
	 * @param amount
	 * @param type
	 * @param description
	 * @param idRestaurant
	 */
	public Product(int idProduct, double price, String name, int amount, ProductType type, String description, int idRestaurant) {
		this.idProduct		= idProduct; 
		this.price 			= price;
		this.name 			= name;
		this.amount 		= amount;
		this.type 			= type;
		this.description	= description;
		this.idRestaurant 	= idRestaurant;
	}

	public int getId() {
		return idProduct;
	}

	public void setId(int id) {
		this.idProduct = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIdRestaurant() {
		return idRestaurant;
	}

	public void setIdRestaurant(int idRestaurant) {
		this.idRestaurant = idRestaurant;
	}
	
	public ProductType getType() {
		return type;
	}
	
	public void setType(ProductType type) {
		this.type = type;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
