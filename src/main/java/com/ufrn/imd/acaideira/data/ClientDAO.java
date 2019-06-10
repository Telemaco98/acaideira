package com.ufrn.imd.acaideira.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ufrn.imd.acaideira.data.exception.DatabaseException;
import com.ufrn.imd.acaideira.domain.Client;

public class ClientDAO extends UtilsDAO<ClientDAO, Client> implements DAO<Client> {
	private ClientDAO clientDAO;
	
	private ClientDAO () { }
	
	@Override
	public synchronized ClientDAO getInstance() {
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
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
	}

	@Override
	public Client select(int id) throws DatabaseException {
		try {
			this.startConnection();
			
			String sql = "SELECT * FROM CLIENT WHERE id_client = " +
					retornValueStringBD(String.valueOf(id));  // TODO update to get the addresses of each client
			
			ResultSet rs = command.executeQuery(sql);

			Client c = null;
			if (rs.next()) {
				String cpf  = rs.getString("cpf");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				
				c = new Client(id, cpf, name, email, phone);
			} 
			
			return c;
		} catch (SQLException | ClassNotFoundException e) {
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
	public void delete(Client client) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "DELETE FROM client WHERE id_client="
					+ retornValueStringBD(String.valueOf(client.getId_cliente()));
			
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
		return "cpf, name, email, phone";
	}

	@Override
	protected String returnFieldValuesBD(Client c) {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("cpf=");
		buffer.append(retornValueStringBD(c.getCpf()));
		buffer.append(", name=");
		buffer.append(retornValueStringBD(c.getName()));
		buffer.append(", email=");
		buffer.append(retornValueStringBD(c.getEmail()));
		buffer.append(", phone=");
		buffer.append(retornValueStringBD(c.getPhone()));
		
		return buffer.toString();
	}

	@Override
	protected String returnValuesBD(Client c) {
		return retornValueStringBD(c.getCpf()) + ", " +
				retornValueStringBD(c.getName()) + ", " +
				retornValueStringBD(c.getEmail()) + ", " +
				retornValueStringBD(c.getPhone());
	}

}
