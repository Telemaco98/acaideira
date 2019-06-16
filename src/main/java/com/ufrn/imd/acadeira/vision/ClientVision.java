package com.ufrn.imd.acadeira.vision;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.ufrn.imd.acaideira.data.ClientDAO;
import com.ufrn.imd.acaideira.data.OrderDAO;
import com.ufrn.imd.acaideira.data.ProductDAO;
import com.ufrn.imd.acaideira.data.RestaurantDAO;
import com.ufrn.imd.acaideira.data.exception.DatabaseException;
import com.ufrn.imd.acaideira.domain.Address;
import com.ufrn.imd.acaideira.domain.Client;
import com.ufrn.imd.acaideira.domain.Order;
import com.ufrn.imd.acaideira.domain.Product;
import com.ufrn.imd.acaideira.domain.CreditCard;
import com.ufrn.imd.acaideira.domain.Restaurant;

public class ClientVision implements Vision{
	private ClientDAO clientDAO;
	private ProductDAO productDAO;
	private OrderDAO orderDAO;
	private RestaurantDAO restaurantDAO;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public ClientVision() throws DatabaseException{
		this.clientDAO = ClientDAO.getInstance();
	}
	//ok
	@Override
	public void visualize() throws Exception {
		System.out.println("Type the client ID: ");
		int id = Integer.parseInt(reader.readLine());
		Client client = null;
	    try {
	    	client = clientDAO.select(id);
	    }
	    catch(DatabaseException e) {
	    	throw new DatabaseException("Problems");
	    }
	    
	    if(client== null) {
	    	System.out.println("client not found");
	    }
	    else {
	    	System.out.println(client.toString());
	    }		
	}
	//ok
	@Override
	public void visualizeAll() throws Exception {
		System.out.println("All clients");
		List<Client> clients = clientDAO.selectAllClients();
		int sizeListClients = clients.size();
		if(sizeListClients != 0) {
			System.out.println("Clients added");
			for(Client client:clients) {
				System.out.println(client.toString());
			}
		}
		else {
			System.out.println("There is no clients added");
		}
		
	}
	//ko
	@Override
	public void add() throws Exception {
		ArrayList<String> infoClient = new ArrayList<String>();
		infoClient.add("CPF: ");
		infoClient.add("Name: ");
		infoClient.add("Email: ");
		infoClient.add("Phone: ");
		infoClient.add("Address: ");
		
		ArrayList<String> cli = askInfo(infoClient,infoClient.size());

		Client c = new Client(cli.get(0),cli.get(1), cli.get(2),cli.get(3), cli.get(4));
		try {
			clientDAO.insert(c);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	//ok
	@Override
	public void remove() throws Exception {
		System.out.println("Type the client id: ");
		int id = Integer.parseInt(reader.readLine());
	    Client client = null;
	    try {
	    	client = clientDAO.select(id);
	    }
	    catch(DatabaseException e) {
	    	throw new DatabaseException("Problems");
	    }
	    
	    if(client == null) {
	    	System.out.println("Client not found!");
	    }
	    else {
	    	try {
	    		clientDAO.delete(client);
	    		//restaurantDAO.commit();
	    	}catch(DatabaseException e) {
	    		throw new DatabaseException("Problems");
	    	}
	    }
		
	}
	//not ok
	@Override
	public void alter() throws Exception {
		
		System.out.println("Type the client id: ");
		int id = Integer.parseInt(reader.readLine());
	    Client client= null;
	    try {
	    	client = clientDAO.select(id);
	    }
	    catch(DatabaseException e) {
	    	throw new DatabaseException("Problems");
	    }
	    
	    if(client== null) {
	    	System.out.println("client not found");
	    }
	    else {
	    	ArrayList<String> infoClient = new ArrayList<String>();
	    	infoClient.add("Name: ");
	    	infoClient.add("cpf: ");
	    	infoClient.add("email: ");
	    	infoClient.add("phone: ");
	    	infoClient.add("Address: ");
			
			ArrayList<String> par = this.askInfo(infoClient,infoClient.size());
			 
			client.setName(par.get(0));
			client.setCpf(par.get(1));
			client.setEmail(par.get(2));
			client.setPhone(par.get(3));
			//setAddress
			client.setPhone(par.get(4));
	    	try {
	    		clientDAO.update(client);
	    	}catch(DatabaseException e) {
	    		throw new DatabaseException("Problems");
	    	}
	    }
		
	}
	
	//Create a new order
	public void createOrder() throws Exception {
		System.out.println("Type de id of client: ");
		int idClient= Integer.parseInt(reader.readLine());
		Client client = null;
		
		try {
			client = clientDAO.select(idClient);
		}catch (DatabaseException e) {
			throw new DatabaseException("Problems");
		}
		
		if(client != null) {
			Order order = new Order("creating");
			try {
				orderDAO.insert(order);
			} catch(DatabaseException e) {
				throw new DatabaseException("Problems");
			}			
		} else {
			System.out.println("Client not found");
		}
	}
//	
	public void addProductOrder() throws Exception{
		System.out.println("type the id of the client");
		int idClient = Integer.parseInt(reader.readLine());
		Client client = null;
		 try {
			 client = clientDAO.select(idClient);
		 } catch (DatabaseException e) {
			 throw new DatabaseException("erro");
		 } if (client !=null) {
			 Order order = orderDAO.selectOrderbyClient(idClient);
			 System.out.println("Type the id of th product: ");
			 
			 int idProduct = Integer.parseInt(reader.readLine());
			 System.out.println("Type the quantity: ");
			 
			 int qtd= Integer.parseInt(reader.readLine());
			 Product product = null;
			 try {
				 product = productDAO.select(idProduct);
				 orderDAO.addToCart(product, order, qtd);
			 } catch (DatabaseException e) {
				 throw new DatabaseException("erro");
			 }
		 } else {
			 System.out.println("order not found");
		 }
	}
	
	
	//aaaaaaaa
	public void shareOrder() throws Exception{
		System.out.println("type the id of the Order: ");
		int idOrder = Integer.parseInt(reader.readLine());
		Order order = null;
		
		try {
			order = orderDAO.select(idOrder);
		} catch (DatabaseException e) {
			throw new DatabaseException("Erro");
		}
		if(order == null) {
			System.out.println("Order not found!");
		} else {
			System.out.println("Type the id of the client to share: ");
			int idClient = Integer.parseInt(reader.readLine());
			Client clientShared = null;
			
			try {
				clientShared = clientDAO.select(idClient); 
			} catch (DatabaseException e) {
				throw new DatabaseException("Erro");
			} if(clientShared != null) {
				orderDAO.creatOrderClient(idClient, idOrder);
			}
		}
	}


	public void history() throws Exception {
		System.out.println("Type de id of client: ");
		int idClient = Integer.parseInt(reader.readLine());
		Client client = null;
		
		try {
			client = clientDAO.select(idClient);
		} catch (DatabaseException e) {
			throw new DatabaseException("erro");
		} List<Order> orders = new ArrayList<Order>(); 
		if(client != null) {
			orders = orderDAO.retrievePaymentsBYId(client);
			int sizeOrders = orders.size();
			System.out.println("History of Orders: ");
			if(sizeOrders >0) {
				for(Order single:orders) {
					System.out.println(single);
				}
			}
		}
	}
	
	//aaaaaaaa
	public void payAcount() {
		
	}
	
	
	//aaaaaaaa
	public void repeatOrder() {}
	
	//aaaaaaaa
	public void seeMenu() throws Exception{
		System.out.println("type the id o f restaurant: ");
		int idRestaurant = Integer.parseInt(reader.readLine());
		Restaurant restaurant = null;
		
		try {
			restaurant = restaurantDAO.select(idRestaurant);
		} catch(DatabaseException e) {
			throw new DatabaseException("Problem");
		}
		List<Product> menu = new ArrayList<Product>();
		if (restaurant != null) {
			try { 
			menu =restaurantDAO.retrieveProductInRestaurant(restaurant);
			} catch (DatabaseException e) {
				throw new DatabaseException("eroo");
			}
			int sizeOfMenu = menu.size();
			if(sizeOfMenu != 0) {
	    		System.out.println("----- menu of the restaurant");
	    		for(Product product:menu) {
	    			System.out.println(product);
	    		}
	    	}
	    	else {
	    		System.out.println("No have products in this restaurant!");
	    	}
			
		}
	}
	
	//find product 
	public void findProduct() throws Exception{
		System.out.println("type the name of product: ");
		String productName = reader.readLine();
		List<Product> products = new ArrayList<Product>();		
		try {
			products =  productDAO.retrieveProductsByName(productName);
		} catch (DatabaseException e) {
			throw new DatabaseException("erro");
		}
		int sizeProducts = products.size();
		if (sizeProducts > 0) {
			System.out.println("-----List of products:");
			for(Product product:products) {
				System.out.println(product);
			}
		} else {
			System.out.println("No have this product in the restaurants list");
		}

	}
	
	public void detailsProduct() throws Exception{
		System.out.println("type the id of product");
		int idProduct =  Integer.parseInt(reader.readLine());
		Product product = null;
		
		try {
			product = productDAO.select(idProduct);
		} catch (DatabaseException e) {
			throw new DatabaseException("Erro"); 
		}
		if(product != null){
			System.out.println(product);
		}
		else {
			System.out.println("Product not found!");
		}
	}
	private ArrayList<String> askInfo(ArrayList<String> infoClient, int size) throws Exception{
		ArrayList<String> par = new ArrayList<String>();
		int i = 0;
		do {
			System.out.print(infoClient.get(i));
			i++;
			par.add(reader.readLine());
		}while(i < size);
		return par;
	}
	
}
