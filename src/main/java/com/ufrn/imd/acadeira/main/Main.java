package com.ufrn.imd.acadeira.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.ufrn.imd.acadeira.vision.*;

public class Main {
	
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
	
	public static void clientVision(ClientVision c) throws Exception{
		int option = -1;
		while (option !=0) {
			System.out.println("1 - add client \n 2 - visualize client \n 3 - remove client \n 4 - alter client \n"
					+ "5 - creat order \n 6 - add product in order \n 7 - share order \n 8 - history of order \n"
					+ "9 - pay account \n 10 - repeat order \n 11 - see menu \n 12 - find product by name \n 13 - details of a product");
			option = Integer.parseInt(reader.readLine());
			switch (option) {
			case 0:
				System.out.println("saindo do cliente");
				break;
			case 1:
				c.add();
				break;
			case 2:
				c.visualize();
				break;
			case 3:
				c.remove();
				break;
			case 4:
				c.alter();
				break;
			case 5:
				c.createOrder();
				break;
			case 6:
				c.addProductOrder();
				break;
			case 7:
				c.shareOrder();
				break;
			case 8:
				c.history();
				break;
			case 9:
				c.payAccount();
				break;
			case 10:
				c.repeatOrder();
				break;
			case 11:
				c.seeMenu();
				break;
			case 12:
				c.findProduct();
			case 13:
				c.detailsProduct();
				break;
			default:
				System.out.println("Invalid option\n");
			}
		}
	}
	
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
					v = new ClientVision();
					clientVision((ClientVision)v);
				default:
					System.out.println("Invalid option\n");
			}
			
		}

	}
	
	

}
