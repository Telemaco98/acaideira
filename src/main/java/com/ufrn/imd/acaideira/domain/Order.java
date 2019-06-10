package com.ufrn.imd.acaideira.domain;

/**
 * The Order class represents an order in a restaurant 
 * 
 * @author Shirley Ohara  (ohara.s.98@hotmail.com)
 * @author Vitor Henrique (vitorhenrique908@gmail.com)
 */
public class Order {
	private int 	id;
	private String 	status;
	
	/**
	 * Default constructor
	 */
	public Order() { }

	/**
	 * Convenience constructor
	 * @param id
	 * @param status
	 */
	public Order(int id, String status) {
		this.id = id;
		this.status = status;
	}

	/**
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}