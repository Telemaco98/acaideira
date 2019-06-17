package com.ufrn.imd.acaideira.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ufrn.imd.acaideira.data.exception.DatabaseException;
import com.ufrn.imd.acaideira.domain.Address;
import com.ufrn.imd.acaideira.domain.Product;
import com.ufrn.imd.acaideira.domain.Restaurant;

public class RestaurantDAO extends UtilsDAO<Restaurant> implements DAO<Restaurant> {
	private static RestaurantDAO restaurantDAO;

	private RestaurantDAO() throws DatabaseException { }

	public static synchronized RestaurantDAO getInstance() throws DatabaseException {
		if (restaurantDAO == null)
			restaurantDAO = new RestaurantDAO();
		return restaurantDAO;
	}

	public void update(Restaurant r) throws DatabaseException {
		try {
			this.startConnection();
			StringBuffer buffer = new StringBuffer();
			buffer.append("UPDATE restaurant SET ");
			buffer.append(returnFieldValuesBD(r));
			buffer.append(" WHERE id_restaurant=");
			buffer.append(r.getIdRestaurant());
			String sql = buffer.toString();
			
			//TODO fazer update do address tbm

			command.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	public void insert(Restaurant r) throws DatabaseException {
		try {
			this.startConnection();

			StringBuffer buffer = new StringBuffer();
			buffer.append("INSERT INTO restaurant (");
			buffer.append(this.returnFieldsBD());
			buffer.append(") VALUES (");
			buffer.append(returnValuesBD(r));
			buffer.append(")");
			String sql = buffer.toString();
			command.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	public Restaurant retrieve(int id) throws DatabaseException {
		try {
			this.startConnection();

			String sql = "SELECT * FROM restaurant WHERE id_restaurant = " + returnValueStringBD(String.valueOf(id));
			ResultSet rs = command.executeQuery(sql);
			
			Restaurant r = new Restaurant();
			
			if (rs.next()) {
				r.setIdRestaurant(id);
				r.setName(rs.getString("name"));
				r.setType(rs.getString("type"));
				r.setEmail(rs.getString("email"));
				r.setPassword(rs.getString("password"));
				
				AddressDAO addressDAO = AddressDAO.getInstance();
				List<Address> addresses = addressDAO.retrieveAllRestaurantAddresses(id);
				
				r.setAddresses(addresses);
			}
			
			return r;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		return null;
	}
	
	public Restaurant retrieve(String email, String psw) throws DatabaseException {
		try {
			this.startConnection();

			String sql = "SELECT * FROM restaurant WHERE email = " +
					returnValueStringBD(email) + 
					" AND password = " + returnValueStringBD(psw);
			ResultSet rs = command.executeQuery(sql);
			
			Restaurant restaurant = null;
			
			if (rs.next()) {
				int id 		 	= Integer.parseInt(rs.getString("id_restaurant"));
				String name  	= rs.getString("name");
				String type  	= rs.getString("type");
				
				AddressDAO addressDAO = AddressDAO.getInstance();
				List<Address> addresses = addressDAO.retrieveAllRestaurantAddresses(id);
				
				restaurant = new Restaurant(id, email, name, type, psw, addresses);
			}
			
			return restaurant;
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		} finally {
			this.closeConnection();
		}
	}

	public void delete(Restaurant r) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "DELETE FROM restaurant WHERE id_restaurant=" + returnValueStringBD(String.valueOf(r.getIdRestaurant()));
			command.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
	}

	public List<Restaurant> retrieveRestaurants() throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT * FROM restaurant";
			ResultSet rs = command.executeQuery(sql);
			
			List<Restaurant> rest = new ArrayList<Restaurant>();
			while (rs.next()) {
				Restaurant r = new Restaurant();
				
				r.setIdRestaurant(Integer.parseInt(rs.getString("id_restaurant")));
				r.setName(rs.getString("name"));
				r.setType(rs.getString("type"));
				r.setPassword(rs.getString("password"));
				r.setEmail(rs.getString("email"));
				
				rest.add(r);
			}

			return rest;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		return null;
	}

	public List<Product> retrieveProductInRestaurant(Restaurant r) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT * FROM product WHERE id_restaurant = "
					+ returnValueStringBD(String.valueOf(r.getIdRestaurant()));
			;
			ResultSet rs = command.executeQuery(sql);
			List<Product> products = new ArrayList<>();
			while (rs.next()) {
				Product p = new Product();
				p.setIdProduct(Integer.parseInt(rs.getString("id_product")));
				p.setPrice(Double.parseDouble(rs.getString("price")));
				p.setName(rs.getString("name"));
				p.setIdRestaurant(Integer.parseInt(rs.getString("id_restaurant")));
				p.setAmount(Integer.parseInt(rs.getString("quantity")));
				products.add(p);
			}

			return products;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		return null;
	}

	public List<Product> retrieveProductsByNameDesc(Restaurant r, String name) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT * FROM product WHERE name LIKE " + returnValueStringBD("%" + name + "%")
					+ " AND id_restaurant=" + returnValueStringBD("%" + String.valueOf(r.getIdRestaurant()) + "%")
					+ "ORDER BY Name DESC";
			ResultSet rs = command.executeQuery(sql);
			List<Product> products = new ArrayList<>();
			while (rs.next()) {
				Product p = new Product();
				p.setIdProduct(Integer.parseInt(rs.getString("id_product")));
				p.setPrice(Double.parseDouble(rs.getString("price")));
				p.setName(rs.getString("name"));
				p.setIdRestaurant(Integer.parseInt(rs.getString("id_restaurant")));
				p.setAmount(Integer.parseInt(rs.getString("quantity")));
				products.add(p);
			}

			return products;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		return null;
	}

	public List<Product> retrieveProductsByNameAsc(Restaurant r, String name) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT * FROM product WHERE name LIKE " + returnValueStringBD("%" + name + "%")
					+ " AND id_restaurant=" + returnValueStringBD("%" + String.valueOf(r.getIdRestaurant()) + "%")
					+ " ORDER BY Name Asc";
			ResultSet rs = command.executeQuery(sql);
			List<Product> products = new ArrayList<>();
			while (rs.next()) {
				Product p = new Product();
				p.setIdProduct(Integer.parseInt(rs.getString("id_product")));
				p.setPrice(Double.parseDouble(rs.getString("price")));
				p.setName(rs.getString("name"));
				p.setIdRestaurant(Integer.parseInt(rs.getString("id_restaurant")));
				p.setAmount(Integer.parseInt(rs.getString("quantity")));
				products.add(p);
			}

			return products;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		return null;
	}

	public List<Restaurant> retrieveRestaurantsByType(String type) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT * FROM restaurant WHERE type LIKE " + returnValueStringBD("%" + type + "%");
			ResultSet rs = command.executeQuery(sql);
			List<Restaurant> rest = new ArrayList<Restaurant>();
			while (rs.next()) {
				Restaurant r = new Restaurant();
				int id_restaurant = Integer.parseInt(rs.getString("id_restaurant"));
				r.setIdRestaurant(id_restaurant);
				r.setName(rs.getString("name"));
				r.setType(rs.getString("type"));
				r.setName(rs.getString("email"));
				
				AddressDAO addressDAO = AddressDAO.getInstance();
				addressDAO.retrieveAllRestaurantAddresses(id_restaurant);
				
				rest.add(r);
			}

			return rest;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		return null;
	}

	@Override
	protected String returnFieldsBD() {
		return "name, type, password, email";
	}

	@Override
	protected String returnFieldValuesBD(Restaurant r) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("name=");
		buffer.append(returnValueStringBD(r.getName()));
		buffer.append(", type=");
		buffer.append(returnValueStringBD(r.getType()));
		buffer.append(", password=");
		buffer.append(returnValueStringBD(r.getType()));
		buffer.append(", email=");
		buffer.append(returnValueStringBD(r.getEmail()));
		return buffer.toString();
	}

	@Override
	protected String returnValuesBD(Restaurant r) {
		return returnValueStringBD(r.getName()) + ", " +
				returnValueStringBD(r.getType()) + ", " + 
				returnValueStringBD(r.getPassword()) + ", " + 
				returnValueStringBD(r.getEmail());
	}
}
