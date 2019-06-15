package com.ufrn.imd.acaideira.domain;

import java.util.List;

public class Restaurant {
	private int 	idRestaurant;
	private String 	email;
	private String 	name;
	private String 	type;
	private String  password;
	private List<Address> addresses;
	
	public Restaurant() { }
	
	public Restaurant(String email, String name, List<Address> addresses, String type, String password) {
		this.email 	   = email;
		this.name	   = name;
		this.addresses = addresses;
		this.type 	   = type;
		this.password  = password;
	}
	
	public Restaurant(int idRestaurant, String email, String name, List<Address> addresses, String type, String password) {
		this (email, name, addresses, type, password);
		this.idRestaurant = idRestaurant;
	}
		
	public int getIdRestaurant() {
		return idRestaurant;
	}
	
	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}
	
	public List<Address> getAddresses() {
		return addresses;
	}

	public void setIdRestaurant(int idRestaurant) {
		this.idRestaurant = idRestaurant;
	}
	
	public String getType() {
		return type;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "[ email= " + email + 
				" name= " + name + 
				", type= " + type + " ]";
	}
}