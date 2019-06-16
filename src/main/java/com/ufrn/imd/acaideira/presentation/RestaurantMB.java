package com.ufrn.imd.acaideira.presentation;

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
	
	private List<Restaurant> restaurantList = new ArrayList<Restaurant>();
	private Restaurant restaurant 			= new Restaurant();
	
	public List<Restaurant> getRestaurantList() {
		try {
			dao = RestaurantDAO.getInstance();
			restaurantList = dao.retrieveRestaurants();
			
			return restaurantList;
		} catch (DatabaseException e) {
			System.out.println("I didn't get the restaurants list");
		}
		return null;
	}
	
	public Restaurant getRestaurant() {
		return restaurant;
	}
	
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	
	public void setRestaurantList(List<Restaurant> restaurantList) {
		this.restaurantList = restaurantList;
	}
	
	public String addNewRestaurant() {
		try {
			dao = RestaurantDAO.getInstance();
			dao.insert(restaurant);
			
			restaurantList = dao.retrieveRestaurants();			
			return "restaurantlist";
		} catch (DatabaseException e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	}
}