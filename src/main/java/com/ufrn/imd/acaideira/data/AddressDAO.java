package com.ufrn.imd.acaideira.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ufrn.imd.acadeira.domain.Address;
import com.ufrn.imd.acaideira.data.exception.DatabaseException;

public class AddressDAO extends UtilsDAO<Address> implements DAO<Address> {
	private Connection connection;
	private Statement  command;
	private static AddressDAO addressDAO;

	private AddressDAO () {
		connection = ConnectionFactory.getConnection();
	}

	public static synchronized AddressDAO getInstance() throws DatabaseException {
		if (addressDAO == null)
			addressDAO = new AddressDAO;
		return addressDAO;
	}

	private void startConnection() throws ClassNotFoundException, SQLException, DatabaseException {
		connection = ConnectionFactory.getConnection();
		command = connection.createStatement();
		System.out.println("Connection Success!");
	}

	private void closeConnection() {
		try {
			command.close();
			connection.close();
			System.out.println("Connection Failed");
		} catch (SQLException e) {
		}
	}

	@Override
	public void insert(Address address) throws DatabaseException {
		try {
			this.startConnection();
			
			StringBuffer buffer = new StringBuffer();
			buffer.append("INSERT INTO address (");
			buffer.append(this.returnFieldsBD());
			buffer.append(") VALUES (");
			buffer.append(returnValuesBD(address));
			buffer.append(")");
			
			String sql = buffer.toString();
			command.execute(sql);			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
	}

	@Override
	public Address select(int id) throws DatabaseException {
		try {
			this.startConnection();
			
			String sql = "SELECT * FROM CLIENT WHERE id_address = " +
					UtilsDAO.retornValueStringBD(String.valueOf(id));  // TODO update to get the addresses of each client
			
			ResultSet rs = command.executeQuery(sql);

			Address a = null;
			if (rs.next()) {
				String cep  		= rs.getString("cep");
				String street 		= rs.getString("street");
				String neighborhood = rs.getString("neighborhood");
				String city 		= rs.getString("city");
				String number 		= Integer.parseInt(rs.getString("number"));
				String complement   = rs.getString("complement");
				
				a = new Address(id, cep, street, neighborhood, city, number, complement);
				return c;
			} 
		} catch (SQLException | ClassNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		} finally {
			this.closeConnection();
		}
		
		throw new DatabaseException("This address doesn't exist");  
	}

	@Override
	public void update(Address address) throws DatabaseException {
		try {
			this.startConnection();
			
			StringBuffer buffer = new StringBuffer();
			buffer.append("UPDATE address SET ");
			buffer.append(returnFieldValuesBD(address));
			buffer.append(" WHERE id_address=");
			buffer.append(client.getId_cliente());
			
			String sql = buffer.toString();
			command.executeUpdate(sql);
		} catch (SQLException | ClassNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		} finally {
			this.closeConnection();
		}		
	}

	@Override
	public void delete(Address address) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "DELETE FROM address WHERE id_address="
					+ retornValueStringBD(String.valueOf(address.getAddressId()));
			
			command.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}		
	}

	@Override
	protected String returnFieldsBD() {
		return "cep, street, neighborhood, city, number, complements";
	}

	@Override
	protected String returnFieldValuesBD(Address a) {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("cep=");
		buffer.append(retornValueStringBD(a.getCep()));
		buffer.append(", street=");
		buffer.append(retornValueStringBD(a.getStreet()));
		buffer.append(", neighborhood=");
		buffer.append(retornValueStringBD(a.getNeighborhood()));
		buffer.append(", city=");
		buffer.append(retornValueStringBD(a.getCity()));
		buffer.append(", number=");
		buffer.append(retornValueStringBD(String.valueOf(a.getNumber())));
		buffer.append(", complement=");
		buffer.append(retornValueStringBD(a.getComplement()));
		
		return buffer.toString();
	}

	@Override
	protected String returnValuesBD(Address a) {
		return retornValueStringBD(a.getCep()) + ", " + 
				retornValueStringBD(a.getStreet()) + ", " +
				retornValueStringBD(a.getNeighborhood()) + ", " +
				retornValueStringBD(a.getCity()) + ", " +
				retornValueStringBD(String.valueOf(a.getNumber())) + ", " +
				retornValueStringBD(a.getComplement());
	}
}