package com.ufrn.imd.acaideira.domain;

import java.util.ArrayList;

/**
 * The Client class represents a person that is a user of 
 * Acaideira as consumer
 * 
 * @author 	Shirley Ohara  (ohara.s.98@hotmail.com)
 * @author 	Vitor Henrique (vitorhenrique908@gmail.com)
 * @author  João Victor (jooeu40jv@gmail.com)
 * @version 10.06.2019
 */
public class Client {
	private int    id_client;
	private String cpf;
	private String name;
	private String email;
	private String password;
	private String phone;
	private String addresses;
	private ArrayList<CreditCard> credit_cards;

	/**
	 * Default constructor
	 */
	public Client() { 
		this.credit_cards = new ArrayList<>();
	}
	
	/**
	 * Convenience constructor
	 * @param id
	 * @param name
	 * @param cpf
	 * @param email
	 * @param password
	 * @param phone
	 * @param address
	 */
	public Client(int id, String name, String cpf, String email, String password, String phone, String address) {
		this.id_client   = id;
		this.cpf 		  = cpf;
		this.name 		  = name;		
		this.email  	  = email;
		this.password 	  = password;
		this.phone  	  = phone;
		this.addresses	  = address;
		this.credit_cards = new ArrayList<>();
	}
	
	public Client( String name, String cpf, String email, String password, String phone, String addresses) {
		this.cpf 		  = cpf;
		this.name 		  = name;		
		this.email  	  = email;
		this.password 	  = password;
		this.phone  	  = phone;
		this.addresses	  = addresses;
		this.credit_cards = new ArrayList<>();
	}
	
	public Client(int id, String name, String cpf, String email, String password, String phone, String addresses, ArrayList<String> phones, ArrayList<CreditCard> credit_cards) {
		this(id, cpf, name, email, password, phone, addresses);
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
	
	public String getPassword(){
		return password;
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
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
		
	public String getAddresses() {
		return addresses;
	}

	public void setAddresses(String addresses) {
		this.addresses = addresses;
	}

	public ArrayList<CreditCard> getCredit_cards() {
		return credit_cards;
	}
	
	@Override
	public String toString() {
		return "[ id = " + this.id_client + ", name = "+ this.name + ", email = " + this.email + ", password ="+ this.password + ", phone = " + this.phone + ", addresses = "+ this.addresses+ " ]";
	}
}