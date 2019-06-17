package com.ufrn.imd.acadeira.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.ufrn.imd.acadeira.vision.*;

public class Main {
	
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
	
	public static void restaurantVision(RestaurantVision r) throws Exception {
		int option = -1;
		while(option != 0) {
			System.out.println("\n 1 - Add restaurant \n 2 - Log in \n 3- Search restaurant by parameter \n 0 - Exit\n");
			option = Integer.parseInt(reader.readLine());
			switch(option) {
				case 0:
					System.out.println("Saindo do programa\n");
				break;
				case 1:
					r.add();					
				break;
				case 2:
					r.searchCredentials();
					if(r.getUser() != null){
						logIn(r);
					}
					else {
						System.out.println("Crendentials not found");
					}
				break;
				case 3:
					r.searchByRestaurantParameter();
				break;
				default:
					System.out.println("Invalid option\n");
			}
			
		}
	}
	
	public static void logIn(RestaurantVision r) throws Exception{
		
		int option = -1;
		while(option != 0) {
			System.out.println("\n 1 - Alter restaurant info \n 2 - Retrive products from restaurant "
					+ "\n 3 - Remove product from restaurant \n 4 - Alter product from restaurant \n 5 - Purchases from Restaurant \n"
					+ " 6 - Add product in restaurant \n 7 - Retrive all my restaurants \n 8 - Remove restaurant"
					+ "\n 0 - Log out");
			
			option = Integer.parseInt(reader.readLine());
			switch(option) {
				case 0:
					System.out.println("Loging out\n");
				break;
				case 1:
					r.alter();
				break;
				case 2:
					r.retriveProductsInRestaurant();
				break;
				case 3:
					r.removeProductInRestaurant();
				break;
				case 4:
					r.alterProductInRestaurant();
				break;
				case 5:
					r.retrivePurchasesFromRestaurant();
				break;
				case 6:
					r.addProductInRestaurant();
				break;
				case 7:
					r.visualizeAll();
				break;
				case 8:
					r.remove();
				break;
				default:
					System.out.println("Invalid option\n");
			}
		}
	}
	
	
	public static void main(String[] args) throws Exception{	    
		Vision v;
	    
		int option = -1;
		while(option != 0) {
			System.out.println("\n 1 - Restaurant \n 2 - Client \n 0 - Exit\n");
			option = Integer.parseInt(reader.readLine());
			switch(option) {
				case 0:
					System.out.println("Saindo do programa\n");
				break;
				case 1:
					v = new RestaurantVision();
					restaurantVision((RestaurantVision)v);
				break;
				case 2:
					System.out.println("");
				default:
					System.out.println("Invalid option\n");
			}
			
		}

	}
	
	

}
