package com.ufrn.imd.acaideira.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ufrn.imd.acaideira.data.exception.DatabaseException;
import com.ufrn.imd.acaideira.domain.Product;
import com.ufrn.imd.acaideira.domain.Purchase;
import com.ufrn.imd.acaideira.domain.Restaurant;
import com.ufrn.imd.acadeira.vision.Usuario;

public class RestaurantDAO extends UtilsDAO<RestaurantDAO, Restaurant> implements DAO<Restaurant> {
	private static RestaurantDAO restaurantDAO;
	private com.ufrn.imd.acadeira.vision.Usuario user;
	
	private RestaurantDAO() throws DatabaseException { }
	
	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

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
			buffer.append(returnValueStringBD(String.valueOf(r.getId())));
			buffer.append(" AND email=");
			buffer.append(returnValueStringBD(user.getEmail()));
			String sql = buffer.toString();

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

	public Restaurant select(int id) throws DatabaseException {
		try {
			this.startConnection();

			String sql = "SELECT * FROM restaurant WHERE id_restaurant = " + returnValueStringBD(String.valueOf(id))
			+ " AND email=" + returnValueStringBD(user.getEmail());
			ResultSet rs = command.executeQuery(sql);
			Restaurant r = null;
			if (rs.next()) {
				r = new Restaurant();
				r.setId(Integer.parseInt(rs.getString("id_restaurant")));
				r.setNome(rs.getString("name"));
				r.setTipo(rs.getString("type"));
				r.setEndereco(rs.getString("address"));
				r.setEmail(rs.getString("email"));
				r.setPassword(rs.getString("password"));
			}

			return r;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		return null;
	}

	public void delete(Restaurant r) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "DELETE FROM restaurant WHERE id_restaurant=" + returnValueStringBD(String.valueOf(r.getId()))
			+ "AND email=" + returnValueStringBD(user.getEmail());
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
			String sql = "SELECT * FROM restaurant WHERE email=" +  returnValueStringBD(user.getEmail());
			ResultSet rs = command.executeQuery(sql);
			List<Restaurant> rest = new ArrayList<Restaurant>();
			while (rs.next()) {
				Restaurant r = new Restaurant();
				r.setId(Integer.parseInt(rs.getString("id_restaurant")));
				r.setNome(rs.getString("name"));
				r.setTipo(rs.getString("type"));
				r.setEndereco(rs.getString("address"));
				r.setEmail(rs.getString("email"));
				r.setPassword(rs.getString("password"));
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
					+ returnValueStringBD(String.valueOf(r.getId()));
			ResultSet rs = command.executeQuery(sql);
			List<Product> products = new ArrayList<>();
			while (rs.next()) {
				Product p = new Product();
				p.setId(Integer.parseInt(rs.getString("id_product")));
				p.setPrice(Double.parseDouble(rs.getString("price")));
				p.setNome(rs.getString("name"));
				p.setIdRestaurante(Integer.parseInt(rs.getString("id_restaurant")));
				p.setQuantidade(Integer.parseInt(rs.getString("quantity")));
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
					+ "AND email=" + returnValueStringBD(user.getEmail())
					+ " AND id_restaurant=" + returnValueStringBD("%" + String.valueOf(r.getId()) + "%")
					+ "ORDER BY Name DESC";
			ResultSet rs = command.executeQuery(sql);
			List<Product> products = new ArrayList<>();
			while (rs.next()) {
				Product p = new Product();
				p.setId(Integer.parseInt(rs.getString("id_product")));
				p.setPrice(Double.parseDouble(rs.getString("price")));
				p.setNome(rs.getString("name"));
				p.setIdRestaurante(Integer.parseInt(rs.getString("id_restaurant")));
				p.setQuantidade(Integer.parseInt(rs.getString("quantity")));
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
					+ "AND email=" + returnValueStringBD(user.getEmail())
					+ " AND id_restaurant=" + returnValueStringBD("%" + String.valueOf(r.getId()) + "%")
					+ " ORDER BY Name Asc";
			ResultSet rs = command.executeQuery(sql);
			List<Product> products = new ArrayList<>();
			while (rs.next()) {
				Product p = new Product();
				p.setId(Integer.parseInt(rs.getString("id_product")));
				p.setPrice(Double.parseDouble(rs.getString("price")));
				p.setNome(rs.getString("name"));
				p.setIdRestaurante(Integer.parseInt(rs.getString("id_restaurant")));
				p.setQuantidade(Integer.parseInt(rs.getString("quantity")));
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
	
	public List<Restaurant> retrieveRestaurantsByName(String name) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT * FROM restaurant WHERE name LIKE " + returnValueStringBD("%" + name + "%");
			ResultSet rs = command.executeQuery(sql);
			List<Restaurant> rest = new ArrayList<Restaurant>();
			while (rs.next()) {
				Restaurant r = new Restaurant();
				r.setId(Integer.parseInt(rs.getString("id_restaurant")));
				r.setNome(rs.getString("name"));
				r.setTipo(rs.getString("type"));
				r.setEndereco(rs.getString("address"));
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

	public List<Restaurant> retrieveRestaurantsByType(String type) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT * FROM restaurant WHERE type LIKE " + returnValueStringBD("%" + type + "%");
			ResultSet rs = command.executeQuery(sql);
			List<Restaurant> rest = new ArrayList<Restaurant>();
			while (rs.next()) {
				Restaurant r = new Restaurant();
				r.setId(Integer.parseInt(rs.getString("id_restaurant")));
				r.setNome(rs.getString("name"));
				r.setTipo(rs.getString("type"));
				r.setEndereco(rs.getString("address"));
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

	public List<Restaurant> retrieveRestaurantsByAddress(String address) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT * FROM restaurant WHERE address LIKE " + returnValueStringBD("%" + address + "%");
			ResultSet rs = command.executeQuery(sql);
			List<Restaurant> rest = new ArrayList<Restaurant>();
			while (rs.next()) {
				Restaurant r = new Restaurant();
				r.setId(Integer.parseInt(rs.getString("id_restaurant")));
				r.setNome(rs.getString("name"));
				r.setTipo(rs.getString("type"));
				r.setEndereco(rs.getString("address"));
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
	
	public List<Purchase> retrievePurchaseFromRestaurant(Restaurant r) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT * FROM purchase WHERE id_purchase IN "
					+ "( SELECT id_purchase FROM purchase_restaurant WHERE id_restaurant = "
					+ returnValueStringBD("%" + r.getId() + "%") + ")";
			ResultSet rs = command.executeQuery(sql);
			List<Purchase> purchases = new ArrayList<Purchase>();
			while (rs.next()) {
				Purchase p = new Purchase();
				p.setId(Integer.parseInt(rs.getString("id_purchase")));
				p.setPrice(Double.parseDouble(rs.getString("price")));
				purchases.add(p);
			}

			return purchases;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		return null;
	}
	
	public Restaurant searchCrediatials(String email, String password) throws DatabaseException{
		try {
			this.startConnection();

			String sql = "SELECT * FROM restaurant WHERE email = " + returnValueStringBD(email)
			+ " AND password=" + returnValueStringBD(password);
			ResultSet rs = command.executeQuery(sql);
			Restaurant r = null;
			if (rs.next()) {
				r = new Restaurant();
				r.setId(Integer.parseInt(rs.getString("id_restaurant")));
				r.setNome(rs.getString("name"));
				r.setTipo(rs.getString("type"));
				r.setEndereco(rs.getString("address"));
				r.setEmail(rs.getString("email"));
				r.setPassword(rs.getString("password"));
			}

			return r;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		return null;
	}

	@Override
	protected String returnFieldsBD() {
		return "name, type, address, email, password";
	}

	@Override
	protected String returnFieldValuesBD(Restaurant r) {

		StringBuffer buffer = new StringBuffer();
		buffer.append("name=");
		buffer.append(returnValueStringBD(r.getNome()));
		buffer.append(", type=");
		buffer.append(returnValueStringBD(r.getTipo()));
		buffer.append(", address=");
		buffer.append(returnValueStringBD(r.getEndereco()));
		buffer.append(", email=");
		buffer.append(returnValueStringBD(r.getEmail()));
		buffer.append(", password=");
		buffer.append(returnValueStringBD(r.getPassword()));
		return buffer.toString();
	}

	@Override
	protected String returnValuesBD(Restaurant r) {
		return returnValueStringBD(r.getNome()) + ", " + returnValueStringBD(r.getTipo()) + ", "
				+ returnValueStringBD(r.getEndereco()) + ", " + returnValueStringBD(r.getEmail())
				+ ", " + returnValueStringBD(r.getPassword());
	}
		
}
