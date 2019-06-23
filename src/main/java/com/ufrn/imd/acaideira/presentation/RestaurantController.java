package com.ufrn.imd.acaideira.presentation;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ufrn.imd.acaideira.data.ProductDAO;
import com.ufrn.imd.acaideira.data.RestaurantDAO;
import com.ufrn.imd.acaideira.data.exception.DatabaseException;
import com.ufrn.imd.acaideira.domain.Product;
import com.ufrn.imd.acaideira.domain.Restaurant;
import com.ufrn.imd.acaideira.enums.ProductType;

@Named(value = "restaurantController")
@RequestScoped
public class RestaurantController {
	RestaurantDAO restaurantDAO;
	ProductDAO productDAO;
	
	@Inject
    private LoginControllator loginControllator;
	
	private String pt_str;
	private Product    product 		  = new Product();
	private List<Product> productList = new ArrayList<Product>();
	private List<ProductType> productsType = ProductType.ValuesList();
	
	public String getPt_str() {
		return pt_str;
	}
	
	public void setPt_str(String pt_str) {
		this.pt_str = pt_str;
		product.setType(pt_str);
	}
	
	public Restaurant restaurant() {
		return loginControllator.getRestaurant();
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
			productList = productDAO.retrieveProductsOfRestaurant(restaurant().getIdRestaurant());
			
			return productList;
		} catch (DatabaseException e) {
			System.out.println(e.getMessage());
		}		
		return null;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public String addNewProduct () {
		return "productregister";
	}
	
	public String confirmProductRegister () {
		try {
			product.setIdRestaurant(restaurant().getIdRestaurant());

			productDAO = ProductDAO.getInstance();
			System.out.println(product.toString());
			productDAO.insert(product);
			productList = productDAO.retrieveProductsOfRestaurant(restaurant().getIdRestaurant());
				
			return "restaurantlogged";
		} catch (DatabaseException e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	}
}