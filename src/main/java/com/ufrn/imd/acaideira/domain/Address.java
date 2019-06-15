package com.ufrn.imd.acaideira.domain;

public class Address {
	private int    idAddress;
	private String cep;
	private String street;
	private String neighborhood;
	private String city;
	private int    number;
	private String complement;
	private int    idRestaurant;
	private int	   idClient;
	
	/**
	 * Default constructor
	 */
	public Address() { 
		this.idRestaurant = -1;
		this.idClient 	  = -1;
	}

	/**
	 * Convenience constructor 
	 * @param idAddress
	 * @param cep
	 * @param street
	 * @param neighborhood
	 * @param city
	 * @param number
	 * @param complement
	 */
	public Address(int idAddress, String cep, String street, String neighborhood, String city, int number, String complement) {
		this.idAddress	  = idAddress;
		this.cep 		  = cep;
		this.street 	  = street;
		this.neighborhood = neighborhood;
		this.city 		  = city;
		this.number 	  = number;
		this.complement   = complement;
		this.idRestaurant = -1;
		this.idClient 	  = -1;
	}

	public int getIdAddress() {
		return idAddress;
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
	
	public int getIdRestaurant() {
		return idRestaurant;
	}
	
	public int getIdClient() {
		return idClient;
	}
	
	public void setIdAddress(int idAddress) {
		this.idAddress = idAddress;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public void setComplement(String complement) {
		this.complement = complement;
	}
	
	public void setIdRestaurant(int idRestaurant) {
		if (idClient == -1)
			this.idRestaurant = idRestaurant;
	}
	
	public void setIdClient(int idClient) {
		if (idRestaurant == -1)
			this.idClient = idClient;
	}
	
	@Override
	public String toString() {
		return "[id: " + this.idAddress + 
				", cep: " + cep +
				", street: " + street +
				", neighborhood: " + neighborhood + 
				", city: " + city + 
				", number: " + number + 
				", complement: " + complement + 
				", id restaurant: " + idRestaurant + 
				", id client: " + idClient + "]";
	}
}