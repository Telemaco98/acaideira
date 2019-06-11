package employee.presentation;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.ufrn.imd.acaideira.data.RestaurantDAO;
import com.ufrn.imd.acaideira.data.exception.DatabaseException;
import com.ufrn.imd.acaideira.domain.Restaurant;

@Named(value = "restaurantManageBean")
@RequestScoped
public class RestaurantMB {
	RestaurantDAO dao;
	
	private List<Restaurant> restaurantList = new ArrayList<>();
	private Restaurant restaurant 			= new Restaurant();
	
	public Restaurant getRestaurant() {
		return restaurant;
	}
	
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	public ArrayList<Restaurant> getRestaurantList() {
		try {
			dao = RestaurantDAO.getInstance();
			restaurantList = dao.retrieveRestaurantes();
			return null;
		} catch (DatabaseException e) {
			System.out.println("I didn't get the restaurants list");
		}
		return null;
	}
	
	public void setRestaurantList(List<Restaurant> restaurantList) {
		this.restaurantList = restaurantList;
	}
	
	public void addNewRestaurant() {
		try {
			dao = RestaurantDAO.getInstance();
			dao.insert(restaurant);
			restaurantList = dao.retrieveRestaurantes();
		} catch (DatabaseException e) {
			System.out.println(e.getMessage());
		}
	}	
}