package com.ufrn.imd.acaideira.presentation;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.ufrn.imd.acaideira.data.ProductDAO;
import com.ufrn.imd.acaideira.data.exception.DatabaseException;
import com.ufrn.imd.acaideira.domain.Product;
import com.ufrn.imd.acaideira.enums.ProductType;

@Named(value = "menuManagedBean")
@RequestScoped
public class MenuManagedBean {
	ProductDAO productDAO;
	
	boolean nofilter = true;
	List<Product> products = new ArrayList<Product>();
	List<Product> productsAsc = new ArrayList<Product>();
	
	public List<Product> getProducts() {
		if(!nofilter)
			return products;
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
	
	public String productsAsc () {
		try {
			nofilter = false;
			productDAO = ProductDAO.getInstance();
			products = productDAO.retrieveProductsByNameAsc();
			return null;
		} catch (DatabaseException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public String productsDesc () {
		try {
			nofilter = false;
			productDAO = ProductDAO.getInstance();
			products = productDAO.retrieveProductsByNameDesc();
			return null;
		} catch (DatabaseException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public String productsPrice (double value) {
		try {
			nofilter = false;
			productDAO = ProductDAO.getInstance();
			products = productDAO.retrieveProductsByPrice(value);
			return null;
		} catch (DatabaseException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public String productsType () {
		try {
			String type = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get( "typeStr" );
			nofilter = false;
			productDAO = ProductDAO.getInstance();
			products = productDAO.retrieveProductsByType(ProductType.StrToProductType(type));
			return null;
		} catch (DatabaseException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}