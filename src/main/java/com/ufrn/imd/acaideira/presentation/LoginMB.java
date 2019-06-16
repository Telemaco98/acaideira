package com.ufrn.imd.acaideira.presentation;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.ufrn.imd.acaideira.data.ClientDAO;
import com.ufrn.imd.acaideira.data.exception.DatabaseException;
import com.ufrn.imd.acaideira.domain.Client;

@Named(value = "loginManagedBean")
@RequestScoped
public class LoginMB {
	ClientDAO clientDAO;
	
	private String email;
	private String psw;
	private Client client = new Client();
	
	public String getEmail() {
		return email;
	}
	
	public String getPsw() {
		return psw;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPsw(String psw) {
		this.psw = psw;
	}
	
	public Client getClient() {
		return client;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
	public String login() {
		try {
			clientDAO = ClientDAO.getInstance();
			client = clientDAO.retrieve(email, psw);
			
			if (client != null)
				return "clientlogged";
			else 
				return null;
		} catch (DatabaseException e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	}

	public String logout() {
		client = null;
		return "clientlogin";
	}
}