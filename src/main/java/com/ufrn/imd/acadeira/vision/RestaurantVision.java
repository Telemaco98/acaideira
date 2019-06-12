package com.ufrn.imd.acadeira.vision;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ufrn.imd.acaideira.data.ProductDAO;
import com.ufrn.imd.acaideira.data.RestaurantDAO;
import com.ufrn.imd.acaideira.data.exception.DatabaseException;
import com.ufrn.imd.acaideira.domain.Product;
import com.ufrn.imd.acaideira.domain.Purchase;
import com.ufrn.imd.acaideira.domain.Restaurant;

public class RestaurantVision implements Vision{
	private RestaurantDAO restaurantDAO;
	private ProductDAO productDAO;
	private static Scanner sc = new Scanner(System.in);
	
	public RestaurantVision() throws DatabaseException {
		this.restaurantDAO = RestaurantDAO.getInstance();
		this.productDAO = ProductDAO.getInstance();
	}
	
	@Override
	public void add() throws Exception {
		ArrayList<String> infoRestaurant = new ArrayList<String>();
		infoRestaurant.add("Name: ");
		infoRestaurant.add("Type: ");
		infoRestaurant.add("Address: ");
		
		ArrayList<String> par = askInfo(infoRestaurant,infoRestaurant.size());
		
		Restaurant r = new Restaurant(par.get(0),par.get(1), par.get(2));
		try {
			restaurantDAO.insert(r);
			//restaurantDAO.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void remove() throws Exception{
		System.out.println("Type the restaurant id: ");
		int id = sc.nextInt();
	    Restaurant restaurant = null;
	    try {
	    	restaurant = restaurantDAO.select(id);
	    }
	    catch(DatabaseException e) {
	    	throw new DatabaseException("Problems");
	    }
	    
	    if(restaurant == null) {
	    	System.out.println("Restaurante não encontrado");
	    }
	    else {
	    	try {
	    		restaurantDAO.delete(restaurant);
	    		//restaurantDAO.commit();
	    	}catch(DatabaseException e) {
	    		throw new DatabaseException("Problems");
	    	}
	    }
	}

	@Override
	public void alter() throws Exception {
		System.out.println("Type the restaurant id: ");
		int id = sc.nextInt();
	    Restaurant restaurant = null;
	    try {
	    	restaurant = restaurantDAO.select(id);
	    }
	    catch(DatabaseException e) {
	    	throw new DatabaseException("Problems");
	    }
	    
	    if(restaurant == null) {
	    	System.out.println("Restaurante não encontrado");
	    }
	    else {
	    	ArrayList<String> infoRestaurant = new ArrayList<String>();
			infoRestaurant.add("Name: ");
			infoRestaurant.add("Type: ");
			infoRestaurant.add("Address: ");
			
			ArrayList<String> par = this.askInfo(infoRestaurant,infoRestaurant.size());
	    	
			restaurant = new Restaurant(par.get(0),par.get(1), par.get(2));
			
	    	try {
	    		restaurantDAO.update(restaurant);
	    		//restaurantDAO.commit();
	    	}catch(DatabaseException e) {
	    		throw new DatabaseException("Problems");
	    	}
	    }
	    
	}

	@Override
	public void visualize() throws Exception{
		System.out.println("Type the restaurant id: ");
		int id = sc.nextInt();
	    Restaurant restaurant = null;
	    try {
	    	restaurant = restaurantDAO.select(id);
	    }
	    catch(DatabaseException e) {
	    	throw new DatabaseException("Problems");
	    }
	    
	    if(restaurant == null) {
	    	System.out.println("Restaurante não encontrado");
	    }
	    else {
	    	System.out.println(restaurant.toString());
	    }
	}

	@Override
	public void visualizeAll() throws Exception {
		System.out.println("Added restaurants");
		List<Restaurant> restaurants = restaurantDAO.retrieveRestaurantes();
		int sizeListRestaurants = restaurants.size();
		if(sizeListRestaurants != 0) {
			System.out.println("Restaurants added");
			for(Restaurant restaurant:restaurants) {
				System.out.println(restaurant.toString());
			}
		}
		else {
			System.out.println("There is no restaurant added");
		}
		
	}
	
	public void retriveProductsInRestaurant() throws Exception{
		System.out.println("Type the restaurant id: ");
		int id = sc.nextInt();
	    Restaurant restaurant = null;
	    try {
	    	restaurant = restaurantDAO.select(id);
	    }
	    catch(DatabaseException e) {
	    	throw new DatabaseException("Problems");
	    }
	    List<Product> productsInRestaurant = new ArrayList<Product>();
	    if(restaurant != null) {
	    	try {
	    		 productsInRestaurant = restaurantDAO.retrieveProductInRestaurant(restaurant);
	    	}
	    	catch(DatabaseException e) {
	    		throw new DatabaseException("Problems when trying to search for products");
	    	}
	    	
	    	int sizeOfListRestaurants = productsInRestaurant.size();
			if(sizeOfListRestaurants != 0) {
	    		System.out.println("----- Products added in the restaurant");
	    		for(Product product:productsInRestaurant) {
	    			System.out.println(product);
	    		}
	    	}
	    	else {
	    		System.out.println(" There is no products added in the restaurant");
	    	}
	    }
	}
	
	public void addProductInRestaurant() throws Exception{	
		System.out.println("Type the restaurant id: ");
		int id = sc.nextInt();
	    Restaurant restaurant = null;
	    try {
	    	restaurant = restaurantDAO.select(id);
	    }
	    catch(DatabaseException e) {
	    	throw new DatabaseException("Problems");
	    }
	    
	    ArrayList<String> infoProduct = new ArrayList<String>();
	    infoProduct.add("quantity");
	    infoProduct.add("name");
	    infoProduct.add("price");
	    
	    if(restaurant != null) {
	    	ArrayList<String> parameters = this.askInfo(infoProduct,infoProduct.size());
	    	Product product = new Product(Integer.parseInt(parameters.get(0)), parameters.get(1), Double.parseDouble(parameters.get(2)));
	    	try {
		    	productDAO.insert(product);
		    	//productDAO.commit();
		    }
		    catch(DatabaseException e) {
		    	throw new DatabaseException("Problems");
		    }
		}
		else {
			System.out.println("There is no restaurant with this info");
		}
	    
	}
	
	public void removeProductInRestaurant() throws Exception {
		System.out.println("Type the restaurant id: ");
		int id = sc.nextInt();
	    Restaurant restaurant = null;
	    try {
	    	restaurant = restaurantDAO.select(id);
	    }
	    catch(DatabaseException e) {
	    	throw new DatabaseException("Problems");
	    }
		if(restaurant != null) {
			System.out.println("Type the product id: ");
		    id = sc.nextInt();
		    Product product = null;
		    try {
		    	product = productDAO.select(id);
		    }
		    catch(DatabaseException e) {
		    	throw new DatabaseException("Problems");
		    }
		    
		    if(product != null) {
		    	try {
			    	productDAO.deleteProductInRestaurant(product, restaurant);
			    	//productDAO.commit();
			    }
			    catch(DatabaseException e) {
			    	throw new DatabaseException("Problems");
			    }
		    }
		    else {
		    	System.out.println("There is no product with this info");
		    }
		}
		else {
			System.out.println("There is no restaurant with this info");
		}
		
	}
	
	public void alterProductInRestaurant() throws Exception{	
		System.out.println("Type the restaurant id: ");
		int id = sc.nextInt();
	    Restaurant restaurant = null;
	    try {
	    	restaurant = restaurantDAO.select(id);
	    }
	    catch(DatabaseException e) {
	    	throw new DatabaseException("Problems");
	    }
	    
	    ArrayList<String> infoProduct = new ArrayList<String>();
	    infoProduct.add("quantity");
	    infoProduct.add("name");
	    infoProduct.add("price");
	    
	    if(restaurant != null) {
			System.out.println("Type the product id: ");
		    id = sc.nextInt();
		    Product product = null;
		    try {
		    	product = productDAO.selectInRestaurant(restaurant,id);
		    }
		    catch(DatabaseException e) {
		    	throw new DatabaseException("Problems");
		    }
		   
		    if(product != null) {
		    	ArrayList<String> parameters = this.askInfo(infoProduct,infoProduct.size());
		    	product = new Product(Integer.parseInt(parameters.get(0)), parameters.get(1), Double.parseDouble(parameters.get(2)));
		    	try {
			    	productDAO.update(product);
			    	productDAO.commit();
			    }
			    catch(DatabaseException e) {
			    	throw new DatabaseException("Problems");
			    }
		    }
		    else {
		    	System.out.println("There is no product with this info");
		    }
		}
		else {
			System.out.println("There is no restaurant with this info");
		}
	    
	}
	
	public void retrivePurchasesFromRestaurant() throws Exception{
		System.out.println("Type the restaurant id: ");
		int id = sc.nextInt();
	    Restaurant restaurant = null;
	    try {
	    	restaurant = restaurantDAO.select(id);
	    }
	    catch(DatabaseException e) {
	    	throw new DatabaseException("Problems");
	    }
	    
	    if(restaurant!= null) {
	    	List<Purchase> purchasesFromRestaurant  = new ArrayList<Purchase>();
	    	try {
	    		purchasesFromRestaurant = restaurantDAO.retrievePurchaseFromRestaurant(restaurant);
	    	}
	    	catch(DatabaseException e) {
	    		throw new DatabaseException("Problems when trying to search for products");
	    	}
	    	
	    	int sizeOfListRestaurants = purchasesFromRestaurant .size();
			if(sizeOfListRestaurants != 0) {
	    		System.out.println("----- Products added in the restaurant");
	    		for(Purchase purchase:purchasesFromRestaurant ) {
	    			System.out.println(purchase.toString());
	    		}
	    	}
	    	else {
	    		System.out.println(" There is no products added in the restaurant");
	    	}
	    }
	    else {
	    	System.out.println("There is no restaurant with this info");
	    }
	    
	}
	
	public void searchByRestaurantParameter() throws Exception {
		ArrayList<String> parameter = new ArrayList<String>();
		ArrayList<String> parameterValue = new ArrayList<String>();
		
		System.out.println("1 - Name \t 2 - Address \t 3 - Type \n");
		int op = sc.nextInt();
		
		switch(op) {
			case 1:
				parameter.add("name");
				parameterValue =  askInfo(parameter, parameter.size());
				retriveRestaurantsByName(parameterValue.get(0));
			break;
			case 2:
				parameter.add("address");
				parameterValue =  askInfo(parameter, parameter.size());
				retriveRestaurantsByAddress(parameterValue.get(0));
			break;
			case 3:
				parameter.add("type");
				parameterValue =  askInfo(parameter, parameter.size());
				retriveRestaurantsByType(parameterValue.get(0));
			break;
			default:
				System.out.println("Invalid option");
		}
	}
	
	public void retriveRestaurantsByName(String name) throws DatabaseException {
		List<Restaurant> restaurants = new ArrayList<Restaurant>();
		try {
			restaurants = restaurantDAO.retrieveRestaurantsByName(name);
    	}
    	catch(DatabaseException e) {
    		throw new DatabaseException("Problems when trying to search for products");
    	}
		
		int sizeListRestaurants = restaurants.size();
		if(sizeListRestaurants != 0) {
			System.out.println("Restaurants added");
			for(Restaurant restaurant:restaurants) {
				System.out.println(restaurant.toString());
			}
		}
		else {
			System.out.println("There is no restaurant added");
		}

	}
	
	public void retriveRestaurantsByType(String type) throws DatabaseException {
		List<Restaurant> restaurants = new ArrayList<Restaurant>();
		try {
			restaurants = restaurantDAO.retrieveRestaurantsByName(type);
    	}
    	catch(DatabaseException e) {
    		throw new DatabaseException("Problems when trying to search for products");
    	}
		
		int sizeListRestaurants = restaurants.size();
		if(sizeListRestaurants != 0) {
			System.out.println("----- Restaurants added");
			for(Restaurant restaurant:restaurants) {
				System.out.println(restaurant.toString());
			}
		}
		else {
			System.out.println("There is no restaurant added");
		}

	}
	
	public void retriveRestaurantsByAddress(String address) throws DatabaseException {
		List<Restaurant> restaurants = new ArrayList<Restaurant>();
		try {
			restaurants = restaurantDAO.retrieveRestaurantsByAddress(address);
    	}
    	catch(DatabaseException e) {
    		throw new DatabaseException("Problems when trying to search for products");
    	}
		
		int sizeListRestaurants = restaurants.size();
		if(sizeListRestaurants != 0) {
			System.out.println("----- Restaurants added");
			for(Restaurant restaurant:restaurants) {
				System.out.println(restaurant.toString());
			}
		}
		else {
			System.out.println("There is no restaurant added");
		}

	}
	
	public ArrayList<String> askInfo(ArrayList<String> infoRest, int quant) {
		ArrayList<String> par = new ArrayList<String>();
		int i = 0;
		do {
			System.out.print(infoRest.get(i));
			i++;
			par.add(sc.nextLine());
		}while(i < quant);
		return par;
	}

}
