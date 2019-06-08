package com.ufrn.imd.acadeira.domain;

import java.util.ArrayList;
import java.util.List;

public class Client {
	private int id_cliente;
	private String name;
	private String cpf;
	private List<String> addresses;
	private List<String> phones;
	private List<String> credit_cards;

	public Client(int id, String name, String cpf) {
		this.id_cliente   = id;
		this.name 		  = name;
		this.cpf 		  = cpf;
		this.addresses	  = new ArrayList<String>();
		this.phones 	  = new ArrayList<String>();
		this.credit_cards = new ArrayList<String>();
	}
	
	public Client(int id, String name, String cpf, ArrayList<String> addresses, ArrayList<String> phones, ArrayList<String> credit_cards) {
		this(id, name, cpf);
		this.addresses	  = addresses;
		this.phones 	  = phones;
		this.credit_cards = credit_cards;
	}

	public int getId_cliente() {
		return id_cliente;
	}
	
	public String getName() {
		return name;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public List<String> getAddresses() {
		return addresses;
	}
	
	public List<String> getPhones() {
		return phones;
	}
	
	public void addAddress (String address) {
		addresses.add(address);
	}
	
	public void addPhone (String phone) {
		phones.add(phone);
	}
	
	public List<String> getCredit_cards() {
		return credit_cards;
	}
}
