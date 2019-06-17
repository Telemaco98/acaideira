package com.ufrn.imd.acaideira.domain;

public class Address {
	private int    id_address;
	private String cep;
	private String street;
	private String neighborhood;
	private String city;
	private int    number;
	private String complement;
	private int    id_client;
	
	/**
	 * Convenience constructor
	 * @param id_address
	 * @param cep
	 * @param street
	 * @param neighborhood
	 * @param city
	 * @param number
	 * @param complement
	 * @param user_id
	 */
	public Address(int id_address, String cep, String street, String neighborhood, String city, int number, String complement, int id_client) {
		this.id_address	  = id_address;
		this.id_client 	  = id_client;
		this.cep 		  = cep;
		this.street 	  = street;
		this.neighborhood = neighborhood;
		this.city 		  = city;
		this.number 	  = number;
		this.complement   = complement;
	}

	public int getIdAddress() {
		return id_address;
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
	
	public int getIdClient() {
		return id_client;
	}
}