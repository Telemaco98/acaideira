package com.ufrn.imd.acadeira.vision;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ufrn.imd.acaideira.data.RestaurantDAO;
import com.ufrn.imd.acaideira.data.exception.DatabaseException;
import com.ufrn.imd.acaideira.domain.Restaurant;

public class RestaurantVision implements Vision{
	private RestaurantDAO restaurantDAO;
	private static int numParamRestaurant = 3;
	
	public RestaurantVision() throws DatabaseException {
		this.restaurantDAO = RestaurantDAO.getInstance();
	}
	
	@Override
	public void add() throws Exception {
		Scanner sc = new Scanner(System.in);
		int i = 0;
		ArrayList<String> par = new ArrayList<String>();
		ArrayList<String> parName = new ArrayList<String>();
		parName.add("Name: ");
		parName.add("Type: ");
		parName.add("Address: ");
		do {
			System.out.print("-> " + parName.get(i));
			i++;
			par.add(sc.nextLine());
		}while(i < numParamRestaurant);
		
		
		Restaurant r= new Restaurant(par.get(0),par.get(1), par.get(2));
		try {
			restaurantDAO.insert(r);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void remove() throws Exception{
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
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
	    	}catch(DatabaseException e) {
	    		throw new DatabaseException("Problems");
	    	}
	    }
	}

	@Override
	public void alter() throws Exception {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
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
	    		restaurantDAO.update(restaurant);
	    	}catch(DatabaseException e) {
	    		throw new DatabaseException("Problems");
	    	}
	    }
	    
	}

	@Override
	public void visualize() throws Exception{
		Scanner sc = new Scanner(System.in);
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
	    	System.out.println("Id: " + restaurant.getId() + " Nome: " +  restaurant.getNome() + " Tipo: "+ restaurant.getTipo() + " Endereco: "+ restaurant.getEndereco());
	    }
	}

	@Override
	public void visualizeAll() throws Exception {
		System.out.println("Added restaurants");
		List<Restaurant> restaurants = restaurantDAO.retrieveRestaurantes();
		for(Restaurant restaurant:restaurants) {
			System.out.println("Id: " + restaurant.getId() + " Nome: " +  restaurant.getNome() + " Tipo: "+ restaurant.getTipo() + " Endereco: "+ restaurant.getEndereco());
		}
	}

}
