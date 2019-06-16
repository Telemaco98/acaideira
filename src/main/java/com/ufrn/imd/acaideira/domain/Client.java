package com.ufrn.imd.acaideira.domain;

import java.util.ArrayList;

/**
 * The Client class represents a person that is a user of 
 * Acaideira as consumer
 * 
 * @author 	Shirley Ohara  (ohara.s.98@hotmail.com)
 * @author 	Vitor Henrique (vitorhenrique908@gmail.com)
 * @version 10.06.2019
 */
public class Client {
	private int    idClient;
	private String cpf;
	private String name;
	private String email;
	private String phone;
	private String password;
	private ArrayList<Address> 	  addresses;
	private ArrayList<CreditCard> creditcards;

	/**
	 * Default constructor
	 */
	public Client() { 
		this.addresses	  = new ArrayList<>();
		this.creditcards = new ArrayList<>();
	}
	
	/**
	 * Convenience constructor
	 * @param id
	 * @param cpf
	 * @param name
	 * @param email
	 * @param phone
	 */
	public Client(int id, String cpf, String name, String email, String phone, String password) {
		this.idClient     = id;
		this.cpf 		  = cpf;
		this.name 		  = name;		
		this.email  	  = email;
		this.phone  	  = phone;
		this.addresses	  = new ArrayList<>();
		this.creditcards = new ArrayList<>();
	}
	
	public Client(int id, String cpf, String name, String email, String phone, String password, ArrayList<Address> addresses, ArrayList<CreditCard> creditcards) {
		this(id, cpf, name, email, phone, password);
		this.addresses	  = addresses;
		this.creditcards  = creditcards;
	}

	public int getIdClient() {
		return idClient;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public String getPassword() {
		return password;
	}
	
	public ArrayList<Address> getAddresses() {
		return addresses;
	}
	
	public ArrayList<CreditCard> getCreditCards() {
		return creditcards;
	}
	
	public void setIdClient(int id_client) {
		this.idClient = id_client;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setAddresses(ArrayList<Address> addresses) {
		this.addresses = addresses;
	}
	
	public void setCreditcards(ArrayList<CreditCard> creditcards) {
		this.creditcards = creditcards;
	}
	
	@Override
	public String toString() {
		return "[ id = " + this.idClient + ", name = "+ this.name + ", email = " + this.email + ", phone = " + this.phone + " ]";
	}
}