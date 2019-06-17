package com.ufrn.imd.acaideira.presentation;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.ufrn.imd.acaideira.data.ProductDAO;
import com.ufrn.imd.acaideira.data.RestaurantDAO;
import com.ufrn.imd.acaideira.data.exception.DatabaseException;
import com.ufrn.imd.acaideira.domain.Product;
import com.ufrn.imd.acaideira.domain.Restaurant;
import com.ufrn.imd.acaideira.enums.ProductType;

@Named(value = "loginRestaurantMB")
@RequestScoped
public class LoginRestaurantMB {
	RestaurantDAO restaurantDAO;
	ProductDAO productDAO;
	
	private String email;
	private String psw;
	private String pt_str;
	private Restaurant restaurant 	  = new Restaurant();
	private Product    product 		  = new Product();
	private List<Product> productList = new ArrayList<Product>();
	private List<ProductType> productsType = ProductType.ValuesList();

	public String getEmail() {
		return email;
	}
	
	public String getPsw() {
		return psw;
	}
	
	public String getPt_str() {
		return pt_str;
	}
	
	public void setPt_str(String pt_str) {
		this.pt_str = pt_str;
		product.setType(pt_str);
	}
	
	public Restaurant getRestaurant() {
		return restaurant;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public List<ProductType> getProductsType() {
		return productsType;
	}
	
	public List<Product> getProductList() {
		try {
			productDAO = ProductDAO.getInstance();
			productList = productDAO.retrieveProductsOfRestaurant(restaurant.getIdRestaurant());
			
			return productList;
		} catch (DatabaseException e) {
			System.out.println(e.getMessage());
		}		
		return null;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPsw(String psw) {
		this.psw = psw;
	}
	
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public String addNewProduct () {
		try {
			restaurantDAO = RestaurantDAO.getInstance();
			restaurant = restaurantDAO.retrieve(restaurant.getIdRestaurant());
			
			System.out.println(restaurant.toString());
			return "productregister";
		} catch (DatabaseException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public String confirmProductRegister () {
		try {
			restaurantDAO = RestaurantDAO.getInstance();
			restaurant = restaurantDAO.retrieve(restaurant.getIdRestaurant());
			product.setIdRestaurant(restaurant.getIdRestaurant());

			productDAO = ProductDAO.getInstance();
			System.out.println(product.toString());
			productDAO.insert(product);
			productList = productDAO.retrieveProductsOfRestaurant(restaurant.getIdRestaurant());
				
			return "restaurantlogged";
		} catch (DatabaseException e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	}
	
	public String login() {
		try {
			restaurantDAO = RestaurantDAO.getInstance();
			restaurant = restaurantDAO.retrieve(email, psw);
			
			if (restaurant != null) {
				System.out.println("A PORRA DO ID" + restaurant.getIdRestaurant());
				return "restaurantlogged";
			}
			return null;
		} catch (DatabaseException e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	}
	
	public String logout() {
		restaurant = null;
		return "restaurantlogin";
	}
}