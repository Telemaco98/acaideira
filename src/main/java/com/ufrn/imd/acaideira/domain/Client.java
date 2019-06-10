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
	private int    id_client;
	private String cpf;
	private String name;
	private String email;
	private String phone;
	private ArrayList<Address> 	  addresses;
	private ArrayList<CreditCard> credit_cards;

	/**
	 * Default constructor
	 */
	public Client() { 
		this.addresses	  = new ArrayList<>();
		this.credit_cards = new ArrayList<>();
	}
	
	/**
	 * Convenience constructor
	 * @param id
	 * @param cpf
	 * @param name
	 * @param email
	 * @param phone
	 */
	public Client(int id, String cpf, String name, String email, String phone) {
		this.id_client   = id;
		this.cpf 		  = cpf;
		this.name 		  = name;		
		this.email  	  = email;
		this.phone  	  = phone;
		this.addresses	  = new ArrayList<>();
		this.credit_cards = new ArrayList<>();
	}
	
	public Client(int id, String cpf, String name, String email, String phone, ArrayList<Address> addresses, ArrayList<String> phones, ArrayList<CreditCard> credit_cards) {
		this(id, cpf, name, email, phone);
		this.addresses	  = addresses;
		this.credit_cards = credit_cards;
	}

	public int getId_client() {
		return id_client;
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
	
	public void setId_client(int id_client) {
		this.id_client = id_client;
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
	
	public ArrayList<Address> getAddresses() {
		return addresses;
	}
	
	public ArrayList<CreditCard> getCredit_cards() {
		return credit_cards;
	}
	
	@Override
	public String toString() {
		return "[ id = " + this.id_client + ", name = "+ this.name + ", email = " + this.email + ", phone = " + this.phone + " ]";
	}
}