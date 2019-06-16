package com.ufrn.imd.acaideira.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ufrn.imd.acaideira.data.exception.DatabaseException;
import com.ufrn.imd.acaideira.domain.Address;
import com.ufrn.imd.acaideira.domain.Client;
import com.ufrn.imd.acaideira.domain.CreditCard;

public class ClientDAO extends UtilsDAO<Client> implements DAO<Client> {
	private static ClientDAO clientDAO;
	
	private ClientDAO () throws DatabaseException { 
		super();
		connection = ConnectionFactory.getConnection();
	}

	public synchronized static ClientDAO getInstance() throws DatabaseException {
		if (clientDAO == null)
			clientDAO = new ClientDAO();
		return clientDAO; 
	}
	
	@Override
	public void insert(Client client) throws DatabaseException {
		try {
			this.startConnection();
			
			StringBuffer buffer = new StringBuffer();
			buffer.append("INSERT INTO client (");
			buffer.append(this.returnFieldsBD());
			buffer.append(") VALUES (");
			buffer.append(returnValuesBD(client));
			buffer.append(")");
			
			String sql = buffer.toString();
			command.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
	}

	@Override
	public Client retrieve(int id) throws DatabaseException {
		try {
			this.startConnection();
			
			String sql = "SELECT * FROM CLIENT WHERE id_client = " +
					returnValueStringBD(String.valueOf(id));  // TODO update to get the addresses of each client
			
			ResultSet rs = command.executeQuery(sql);

			Client c = null;
			if (rs.next()) {
				String cpf  	= rs.getString("cpf");
				String name 	= rs.getString("name");
				String email 	= rs.getString("email");
				String password = rs.getString("password");
				String phone 	= rs.getString("phone");
				
				AddressDAO addressDAO = AddressDAO.getInstance();
				ArrayList<Address> addresses = addressDAO.retrieveAllClientAddresses(id);
				
				CreditCardDAO creditCardDAO = CreditCardDAO.getInstance();
				ArrayList<CreditCard> creditcards = creditCardDAO.retrieveAllClientCreditCards(id); 
				
				c = new Client(id, cpf, name, email, phone, password, addresses, creditcards);
			} 
			
			return c;
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		} finally {
			this.closeConnection();
		}  
	}
	
	public Client retrieve(String email, String password) throws DatabaseException {
		try {
			this.startConnection();
			
			String sql = "SELECT * FROM CLIENT WHERE email = " +
					returnValueStringBD(email) + 
					" AND password = " + returnValueStringBD(password); 
			
			ResultSet rs = command.executeQuery(sql);

			Client c = null;
			if (rs.next()) {
				int id 			= Integer.parseInt(rs.getString("id_client"));
				String cpf  	= rs.getString("cpf");
				String name 	= rs.getString("name");
				String phone 	= rs.getString("phone");

				AddressDAO addressDAO = AddressDAO.getInstance();
				ArrayList<Address> addresses = addressDAO.retrieveAllClientAddresses(id);
				
				CreditCardDAO creditCardDAO = CreditCardDAO.getInstance();
				ArrayList<CreditCard> creditcards = creditCardDAO.retrieveAllClientCreditCards(id); 
				
				c = new Client(id, cpf, name, email, phone, password, addresses, creditcards);
			}
			
			return c;
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		} finally {
			this.closeConnection();
		}
	}
	
	public ArrayList<Client> retrieveAllClients () throws DatabaseException {
		try {
			this.startConnection();
			
			String sql = "SELECT * FROM client";
			ResultSet rs = command.executeQuery(sql);

			ArrayList<Client> clients = new ArrayList<>();
			while (rs.next()) {
				int id			= Integer.parseInt(rs.getString("id_client"));
				String cpf  	= rs.getString("cpf");
				String name 	= rs.getString("name");
				String email 	= rs.getString("email");
				String password = rs.getString("password");
				String phone 	= rs.getString("phone");
				
				Client c = new Client(id, cpf, name, email, phone, password);
				clients.add(c);
			}
			
			return clients;
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		} finally {
			this.closeConnection();
		}  
	}

	@Override
	public void update(Client client) throws DatabaseException {
		try {
			this.startConnection();
			
			StringBuffer buffer = new StringBuffer();
			buffer.append("UPDATE client SET ");
			buffer.append(returnFieldValuesBD(client));
			buffer.append(" WHERE id_client=");
			buffer.append(client.getIdClient());
			
			// TODO atualizar endereços e creditcards
			
			String sql = buffer.toString();
			command.executeUpdate(sql);
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		} finally {
			this.closeConnection();
		}		
	}

	@Override
	public void delete(Client client) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "DELETE FROM client WHERE id_client="
					+ returnValueStringBD(String.valueOf(client.getIdClient()));
			
			command.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
	}
	
	@Override
	protected String returnFieldsBD() {
		return "cpf, name, email, password, phone";
	}

	@Override
	protected String returnFieldValuesBD(Client c) {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("cpf=");
		buffer.append(returnValueStringBD(c.getCpf()));
		buffer.append(", name=");
		buffer.append(returnValueStringBD(c.getName()));
		buffer.append(", email=");
		buffer.append(returnValueStringBD(c.getEmail()));
		buffer.append(", password=");
		buffer.append(returnValueStringBD(c.getPassword()));
		buffer.append(", phone=");
		buffer.append(returnValueStringBD(c.getPhone()));
		
		return buffer.toString();
	}

	@Override
	protected String returnValuesBD(Client c) {
		return returnValueStringBD(c.getCpf()) + ", " +
				returnValueStringBD(c.getName()) + ", " +
				returnValueStringBD(c.getEmail()) + ", " +
				returnValueStringBD(c.getPassword()) + ", " +
				returnValueStringBD(c.getPhone());
	}
	
//	public static void main(String[] args) {
//		Client c = new Client();
//		c.setName("Teste2");
//		c.setEmail("a@a");
//		c.setPhone("99999999999");
//		
//		try {
//			ClientDAO dao = ClientDAO.getInstance();
//			System.out.println(dao.selectAllClients().toString());
//		} catch (DatabaseException e) {
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//		}
//	}
}
