package com.ufrn.imd.acadeira.domain;

public class Address {
	private int    id;
	private int    user_id;
	private String cep;
	private String street;
	private String neighborhood;
	private String city;
	private int    number;
	private String complement;
	
	/**
	 * Convenience constructor
	 * @param id
	 * @param user_id
	 * @param cep
	 * @param street
	 * @param neighbohood
	 * @param city
	 * @param number
	 * @param complement
	 */
	public Address(int id, int user_id, String cep, String street, String neighborhood, String city, int number, String complement) {
		this.id 		  = id;
		this.user_id 	  = user_id;
		this.cep 		  = cep;
		this.street 	  = street;
		this.neighborhood = neighborhood;
		this.city 		  = city;
		this.number 	  = number;
		this.complement   = complement;
	}

	public int getId() {
		return id;
	}
	
	public int  getUser_id() {
		return user_id;
	}
	
	public String getCep() {
		return cep;
	}
	
	public String getStreet() {
		return street;
	}
	
	public String getNeighborhood() {
		return neighborhood;
	}

	public String getCity() {
		return city;
	}
	
	public int getNumber() {
		return number;
	}
	
	public String getComplement() {
		return complement;
	}
}