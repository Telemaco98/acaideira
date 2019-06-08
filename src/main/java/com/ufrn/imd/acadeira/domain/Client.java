package com.ufrn.imd.acadeira.domain;

import java.util.ArrayList;

public class Client {
	private int    id_cliente;
	private String cpf;
	private String name;
	private String email;
	private String phone;
	private ArrayList<Address> 	  addresses;
	private ArrayList<CreditCard> credit_cards;

	public Client(int id, String cpf, String name, String email, String phone) {
		this.id_cliente   = id;
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

	public int getId_cliente() {
		return id_cliente;
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
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
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
}