package com.ufrn.imd.acadeira.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.ufrn.imd.acadeira.vision.*;

public class Main {
	
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
	
	public static void clientVision(ClientVision c) throws Exception{}
	
	public static void restaurantVision(RestaurantVision r) throws Exception {
		int option = -1;
		while(option != 0) {
			System.out.println("1 - Add restaurant \n 2 - Check all restaurants added \n 3 - Alter restaurant info \n 4 - Retrive products from restaurant "
					+ "\n 5 - Remove product from restaurant \n 6 - Alter product from restaurant \n 7 - Purchases from Restaurant \n"
					+ " 8 - Search by a parameter \n 9 - Add product in restaurant \n0 - Exit\n");
			option = Integer.parseInt(reader.readLine());
			switch(option) {
				case 0:
					System.out.println("Saindo do programa\n");
				break;
				case 1:
					r.add();					
				break;
				case 2:
					r.visualizeAll();
				break;
				case 3:
					r.alter();
				break;
				case 4:
					r.retriveProductsInRestaurant();
				break;
				case 5:
					r.removeProductInRestaurant();
				break;
				case 6:
					r.alterProductInRestaurant();
				break;
				case 7:
					r.retrivePurchasesFromRestaurant();
				break;
				case 8:
					r.searchByRestaurantParameter();
				break;
				case 9:
					r.addProductInRestaurant();
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
			System.out.println("1 - Restaurant \n 2 - Client \n 0 - Exit\n");
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
