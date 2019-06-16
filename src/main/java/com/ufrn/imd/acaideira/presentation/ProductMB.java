package com.ufrn.imd.acaideira.presentation;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.ufrn.imd.acaideira.data.ProductDAO;
import com.ufrn.imd.acaideira.data.exception.DatabaseException;
import com.ufrn.imd.acaideira.domain.Product;

@Named(value = "productManagedBean")
@RequestScoped
public class ProductMB {
	ProductDAO dao;
	
	private Product product = new Product();
//	private List<Product> productList = new ArrayList<Product>();
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
//	public String addNewProduct (int id_restaurant) {
//		try {
//			dao = ProductDAO.getInstance();
//			dao.insert(product);
//			
//			productList = dao.retrieveProductsOfRestaurant(id_restaurant);
//			
//			return null;
//		} catch (DatabaseException e) {
//			System.out.println(e.getMessage());
//		}
//		return null;
//	}
}
