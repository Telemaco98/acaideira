package com.ufrn.imd.acadeira.domain;

public class CreditCard {
	private int    id_credit_card;
	private int    user_id;
	private String number;
	private String validity;
	private String owner_name;
	
	/**
	 * Convenience constructor 
	 * @param id
	 * @param user_id
	 * @param number
	 * @param validity
	 * @param owner_name
	 */
	public CreditCard(int id, int user_id, String number, String validity, String owner_name) {
		this.id_credit_card = id;
		this.user_id 		= user_id;
		this.number 		= number;
		this.validity 		= validity;
		this.owner_name 	= owner_name;
	}
	
	public int getIdCreditCard() {
		return id_credit_card;
	}
	
	public int getUserId() {
		return user_id;
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
