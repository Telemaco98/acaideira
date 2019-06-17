package com.ufrn.imd.acaideira.presentation;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named(value = "orderManagedBean")
@RequestScoped
public class OrderMB {
	private int amount;
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
