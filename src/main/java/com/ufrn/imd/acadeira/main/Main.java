package com.ufrn.imd.acadeira.main;

import java.util.Scanner;

import com.ufrn.imd.acadeira.vision.*;
import com.ufrn.imd.acaideira.data.RestaurantDAO;
import com.ufrn.imd.acaideira.domain.Restaurant;

public class Main {

	public static void restaurantVision(Vision v) throws Exception {
		Scanner sc = new Scanner(System.in);
		int option = -1;
		while(option != 0) {
			System.out.println("1 - Adicionar restaurante \t 0 - Sair\n");
			option = sc.nextInt();
			switch(option) {
				case 0:
					System.out.println("Saindo do programa\n");
				break;
				case 1:
					v.add();					
				break;
				default:
					System.out.println("Opção inválida\n");
			}
			
		}
	}
	
	public static void main(String[] args) throws Exception{
	    Scanner sc = new Scanner(System.in);
		Vision v;
		int option = -1;
		while(option != 0) {
			System.out.println("1 - Restaurante \t 2 - Cliente \t 0 - Sair\n");
			option = sc.nextInt();
			switch(option) {
				case 0:
					System.out.println("Saindo do programa\n");
				break;
				case 1:
					v = new RestaurantVision();
					restaurantVision(v);
				break;
				case 2:
					v = new ClientVision();
				default:
					System.out.println("Opção inválida\n");
			}
			
		}

	}
	
	

}
