package main;

import java.util.List;

import dao.*;
import entidade.Restaurante;

public class Main {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		RestauranteDao r = new RestauranteDao("jdbc:mysql://localhost:3306/restaurante","root","root");
		List<Restaurante> re = r.retrieveRestaurantes();
		for(Restaurante rest:re) {
			System.out.println(rest);
		}
	}

}
