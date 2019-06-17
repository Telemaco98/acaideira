package com.ufrn.imd.acaideira.domain;

public class CreditCard {
	private int    id_credit_card;
	private String number;
	private String validity;
	private String owner_name;
	
	/**
	 * Convenience constructor
	 * @param id_creditcard
	 * @param number
	 * @param validity
	 * @param owner_name
	 */
	public CreditCard(int id_creditcard, String number, String validity, String owner_name) {
		this.id_credit_card = id_creditcard;
		this.number 		= number;
		this.validity 		= validity;
		this.owner_name 	= owner_name;
	}
	
	public int getIdCreditCard() {
		return id_credit_card;
	}

	public String getNumber() {
		return number;
	}
	
	public String getValidity() {
		return validity;
	}
	
	public String getOwnerName() {
		return owner_name;
	}
}