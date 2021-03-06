package com.ufrn.imd.acaideira.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ufrn.imd.acaideira.data.exception.DatabaseException;
import com.ufrn.imd.acaideira.domain.Address;

public class AddressDAO extends UtilsDAO<AddressDAO, Address> implements DAO<Address> {
	private static AddressDAO addressDAO;

	private AddressDAO () { }

	public synchronized AddressDAO getInstance(){
		if (addressDAO == null)
			addressDAO = new AddressDAO();
		return addressDAO;
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
	}

	@Override
	public Address select(int id) throws DatabaseException {
		try {
			this.startConnection();
			
			String sql = "SELECT * FROM address WHERE id_address = " +
					returnValueStringBD(String.valueOf(id));
			
			ResultSet rs = command.executeQuery(sql);

			Address a = null;
			if (rs.next()) {
				String cep  		= rs.getString("cep");
				String street 		= rs.getString("street");
				String neighborhood = rs.getString("neighborhood");
				String city 		= rs.getString("city");
				int    number 		= Integer.parseInt(rs.getString("number"));
				String complement   = rs.getString("complement");
				int    id_client	= Integer.parseInt(rs.getString("id_user"));
				
				a = new Address(id, cep, street, neighborhood, city, number, complement, id_client);
			} 
			
			return a;
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		} finally {
			this.closeConnection();
		}
	}
	
	public ArrayList<Address> selectAllClientAddresses (int id_client) throws DatabaseException {
		try {
			this.startConnection();
			
			String sql = "SELECT * FROM address WHERE id_client = " +
					returnValueStringBD(String.valueOf(id_client));
			
			ResultSet rs = command.executeQuery(sql);

			ArrayList<Address> addresses = new ArrayList<Address>();
			while (rs.next()) {
				int    id_address	= Integer.parseInt(rs.getString("id_address"));
				String cep  		= rs.getString("cep");
				String street 		= rs.getString("street");
				String neighborhood = rs.getString("neighborhood");
				String city 		= rs.getString("city");
				int    number 		= Integer.parseInt(rs.getString("number"));
				String complement   = rs.getString("complement");
				
				
				Address a = new Address(id_address, cep, street, neighborhood, city, number, complement, id_client);
				addresses.add(a);
			} 
			
			return addresses;
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		} finally {
			this.closeConnection();
		}
	}

	@Override
	public void update(Address address) throws DatabaseException {
		try {
			this.startConnection();
			
			StringBuffer buffer = new StringBuffer();
			buffer.append("UPDATE address SET ");
			buffer.append(returnFieldValuesBD(address));
			buffer.append(" WHERE id_address=");
			buffer.append(address.getIdAddress());
			
			String sql = buffer.toString();
			command.executeUpdate(sql);
		} catch (SQLException e) {
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
					+ returnValueStringBD(String.valueOf(address.getIdAddress()));
			
			command.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}		
	}

	@Override
	protected String returnFieldsBD() {
		return "cep, street, neighborhood, city, number, complements, id_client";
	}

	@Override
	protected String returnFieldValuesBD(Address a) {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("cep=");
		buffer.append(returnValueStringBD(a.getCep()));
		buffer.append(", street=");
		buffer.append(returnValueStringBD(a.getStreet()));
		buffer.append(", neighborhood=");
		buffer.append(returnValueStringBD(a.getNeighborhood()));
		buffer.append(", city=");
		buffer.append(returnValueStringBD(a.getCity()));
		buffer.append(", number=");
		buffer.append(returnValueStringBD(String.valueOf(a.getNumber())));
		buffer.append(", complement=");
		buffer.append(returnValueStringBD(a.getComplement()));
		buffer.append(", id_client=");
		buffer.append(returnValueStringBD(String.valueOf(a.getIdClient())));
		
		return buffer.toString();
	}

	@Override
	protected String returnValuesBD(Address a) {
		return returnValueStringBD(a.getCep()) + ", " + 
				returnValueStringBD(a.getStreet()) + ", " +
				returnValueStringBD(a.getNeighborhood()) + ", " +
				returnValueStringBD(a.getCity()) + ", " +
				returnValueStringBD(String.valueOf(a.getNumber())) + ", " +
				returnValueStringBD(a.getComplement()) + ", " +
				returnValueStringBD(String.valueOf(a.getIdClient()));
	}
}