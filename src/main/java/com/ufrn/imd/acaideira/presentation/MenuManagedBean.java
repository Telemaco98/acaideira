package com.ufrn.imd.acaideira.presentation;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.ufrn.imd.acaideira.data.ProductDAO;
import com.ufrn.imd.acaideira.data.exception.DatabaseException;
import com.ufrn.imd.acaideira.domain.Product;

@Named(value = "menuManagedBean")
@RequestScoped
public class MenuManagedBean {
	ProductDAO productDAO;
	
	List<Product> products = new ArrayList<Product>();
	
	public List<Product> getProducts() {
		try {
			productDAO = ProductDAO.getInstance();
			products = productDAO.retrieveProducts();
			
			return products;
		} catch (DatabaseException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
}
