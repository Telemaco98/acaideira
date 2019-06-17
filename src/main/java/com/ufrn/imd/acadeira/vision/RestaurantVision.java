package com.ufrn.imd.acadeira.vision;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import com.ufrn.imd.acaideira.data.ProductDAO;
import com.ufrn.imd.acaideira.data.RestaurantDAO;
import com.ufrn.imd.acaideira.data.exception.DatabaseException;
import com.ufrn.imd.acaideira.domain.Product;
import com.ufrn.imd.acaideira.domain.Purchase;
import com.ufrn.imd.acaideira.domain.Restaurant;

public class RestaurantVision implements Vision{
	private RestaurantDAO restaurantDAO;
	private ProductDAO productDAO;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private Usuario user;
	
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
		infoRestaurant.add("Email: ");
		infoRestaurant.add("Password: ");
		
		ArrayList<String> par = askInfo(infoRestaurant,infoRestaurant.size());
		
		Restaurant r = new Restaurant(par.get(0),par.get(1), par.get(2),par.get(3),toMD5(par.get(4)));
		try {
			restaurantDAO.insert(r);
			//restaurantDAO.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void remove() throws Exception{
		restaurantDAO.setUser(user);
		System.out.println("Type the restaurant id: ");
		int id = Integer.parseInt(reader.readLine());
	    Restaurant restaurant = null;
	    try {
	    	restaurant = restaurantDAO.select(id);
	    }
	    catch(DatabaseException e) {
	    	throw new DatabaseException("Problems");
	    }
	    
	    if(restaurant == null) {
	    	System.out.println("Restaurante nao encontrado");
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
		restaurantDAO.setUser(user);
		System.out.println("Type the restaurant id: ");
		int id = Integer.parseInt(reader.readLine());
	    Restaurant restaurant = null;
	    try {
	    	restaurant = restaurantDAO.select(id);
	    }
	    catch(DatabaseException e) {
	    	throw new DatabaseException("Problems");
	    }
	    
	    if(restaurant == null) {
	    	System.out.println("Restaurante nao encontrado");
	    }
	    else {
	    	ArrayList<String> infoRestaurant = new ArrayList<String>();
			infoRestaurant.add("Name: ");
			infoRestaurant.add("Type: ");
			infoRestaurant.add("Address: ");
			
			ArrayList<String> par = this.askInfo(infoRestaurant,infoRestaurant.size());
	    	
			restaurant.setNome(par.get(0));
			restaurant.setTipo(par.get(1));
			restaurant.setEndereco(par.get(2));
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
		restaurantDAO.setUser(user);
		System.out.println("Type the restaurant id: ");
		int id = Integer.parseInt(reader.readLine());
	    Restaurant restaurant = null;
	    try {
	    	restaurant = restaurantDAO.select(id);
	    }
	    catch(DatabaseException e) {
	    	throw new DatabaseException("Problems");
	    }
	    
	    if(restaurant == null) {
	    	System.out.println("Restaurante nao encontrado");
	    }
	    else {
	    	System.out.println(restaurant.toString());
	    }
	}

	@Override
	public void visualizeAll() throws Exception {
		System.out.println("Added restaurants");
		restaurantDAO.setUser(user);
		List<Restaurant> restaurants = restaurantDAO.retrieveRestaurants();
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
		restaurantDAO.setUser(user);
		System.out.println("Type the restaurant id: ");
		int id = Integer.parseInt(reader.readLine());
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
		restaurantDAO.setUser(user);
		System.out.println("Type the restaurant id: ");
		int id = Integer.parseInt(reader.readLine());
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
	    	product.setIdRestaurante(restaurant.getId());
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
		restaurantDAO.setUser(user);
		System.out.println("Type the restaurant id: ");
		int id = Integer.parseInt(reader.readLine());
	    Restaurant restaurant = null;
	    try {
	    	restaurant = restaurantDAO.select(id);
	    }
	    catch(DatabaseException e) {
	    	throw new DatabaseException("Problems");
	    }
		if(restaurant != null) {
			System.out.println("Type the product id: ");
		    id = Integer.parseInt(reader.readLine());
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
		restaurantDAO.setUser(user);
		System.out.println("Type the restaurant id: ");
		int id = Integer.parseInt(reader.readLine());
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
		    id = Integer.parseInt(reader.readLine());
		    Product product = null;
		    try {
		    	product = productDAO.selectInRestaurant(restaurant,id);
		    }
		    catch(DatabaseException e) {
		    	throw new DatabaseException("Problems");
		    }
		   
		    if(product != null) {
		    	ArrayList<String> parameters = this.askInfo(infoProduct,infoProduct.size());
		    	product.setQuantidade(Integer.parseInt(parameters.get(0)));
		    	product.setNome(parameters.get(1));
		    	product.setPrice(Double.parseDouble(parameters.get(2)));
		    	product.setIdRestaurante(restaurant.getId());
		    	try {
			    	productDAO.update(product);
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
		restaurantDAO.setUser(user);
		System.out.println("Type the restaurant id: ");
		int id = Integer.parseInt(reader.readLine());;
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
		restaurantDAO.setUser(user);
		ArrayList<String> parameter = new ArrayList<String>();
		ArrayList<String> parameterValue = new ArrayList<String>();
		
		System.out.println("1 - Name \t 2 - Address \t 3 - Type \n");
		int op = Integer.parseInt(reader.readLine());
		
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
			restaurants = restaurantDAO.retrieveRestaurantsByType(type);
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
	
	public void searchCredentials() throws Exception {
		ArrayList<String> infoRestaurant = new ArrayList<String>();
		infoRestaurant.add("Email: ");
		infoRestaurant.add("Password: ");
		
		ArrayList<String> par = askInfo(infoRestaurant,infoRestaurant.size());
		Restaurant r;
		try {
			r = restaurantDAO.searchCrediatials(par.get(0),toMD5(par.get(1)));
		}catch(Exception e) {
			throw new DatabaseException("Problems when trying to log in");
		}
		
		if(r != null) {
			this.user = new Usuario(r.getEmail(),r.getPassword());
		}
		else {
			System.out.println("Usuário não encontrado");
		}
		
	}
	
	public ArrayList<String> askInfo(ArrayList<String> infoRest, int quant) throws Exception {
		ArrayList<String> par = new ArrayList<String>();
		int i = 0;
		do {
			System.out.print(infoRest.get(i));
			i++;
			par.add(reader.readLine());
		}while(i < quant);
		return par;
	}
	

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public String toMD5(String texto) throws Exception{
		MessageDigest m=MessageDigest.getInstance("MD5");
	    m.update(texto.getBytes(),0,texto.length());
		return new BigInteger(1,m.digest()).toString(16);
	}
	
	public void logOut() {
		this.user = null;
		restaurantDAO.setUser(user);
	}

}
