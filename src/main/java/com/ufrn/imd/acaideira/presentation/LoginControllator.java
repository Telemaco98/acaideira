package com.ufrn.imd.acaideira.presentation;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.ufrn.imd.acaideira.data.ClientDAO;
import com.ufrn.imd.acaideira.data.RestaurantDAO;
import com.ufrn.imd.acaideira.data.exception.DatabaseException;
import com.ufrn.imd.acaideira.domain.Client;
import com.ufrn.imd.acaideira.domain.Restaurant;

@Named(value = "loginControllator")
@SessionScoped
public class LoginControllator implements Serializable {
	private static final long serialVersionUID = 1L;
	ClientDAO clientDAO;
	
	@Inject
    private HttpSession session;
	private String email;
	private String psw;
	
	public String getEmail() {
		return "";
	}
	
	public String getPsw() {
		return "";
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPsw(String psw) {
		this.psw = psw;
	}

    public void login() {
    	try {
    		ClientDAO clientDAO = ClientDAO.getInstance();
			Client client = clientDAO.retrieve(email, psw);
			
			this.setClient(client);
		} catch (DatabaseException e) {
			System.out.println(e.getMessage());
		}
    }
    
    public void loginRest() {
    	try {
    		RestaurantDAO restaurantDAO = RestaurantDAO.getInstance();
			Restaurant restaurant = restaurantDAO.retrieve(email, psw);

			this.setRestaurant(restaurant);
		} catch (DatabaseException e) {
			System.out.println(e.getMessage());
		}
    }
    
    public void logout() {
        session.removeAttribute("logged");
        session.removeAttribute("loggedRest");
        session.invalidate();
    }
    
    public Client getClient() {
        return (Client) this.session.getAttribute("logged");
    }

    public void setClient(Client client) {
        this.session.setAttribute("logged", client);
    }
    
    public Restaurant getRestaurant () {
    	return (Restaurant) this.session.getAttribute("loggedRest"); 
    }
    
    public void setRestaurant(Restaurant restaurant) {
    	this.session.setAttribute("loggedRest", restaurant);
	}
}