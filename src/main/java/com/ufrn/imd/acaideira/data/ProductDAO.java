package com.ufrn.imd.acaideira.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ufrn.imd.acaideira.data.exception.DatabaseException;
import com.ufrn.imd.acaideira.domain.Product;
import com.ufrn.imd.acaideira.domain.Restaurant;

public class ProductDAO extends UtilsDAO<ProductDAO, Product> implements DAO<Product> {
	private static ProductDAO ProductDAO;

	private ProductDAO() throws DatabaseException { }
	
	public static synchronized ProductDAO getInstance() throws DatabaseException {
		if (ProductDAO == null)
			ProductDAO = new ProductDAO();
		
		return ProductDAO;
	}
	
	
	@Override
	public void insert(Product p) throws DatabaseException {
		try {
			this.startConnection();

			StringBuffer buffer = new StringBuffer();
			buffer.append("INSERT INTO product (");
			buffer.append(this.returnFieldsBD());
			buffer.append(") VALUES (");
			buffer.append(returnValuesBD(p));
			buffer.append(")");
			
			String sql = buffer.toString();
			command.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	@Override
	public Product select(int id) throws DatabaseException {
		try {
			this.startConnection();
			
			String sql = "SELECT * FROM product WHERE id_product = "
					+ this.returnValueStringBD(String.valueOf(id));
			ResultSet rs = command.executeQuery(sql);
			Product p = new Product();
			if (rs.next()) {
				p.setId(Integer.parseInt(rs.getString("id_product")));
				p.setPrice(Double.parseDouble(rs.getString("price")));
				p.setNome(rs.getString("name"));
				p.setIdRestaurante(Integer.parseInt(rs.getString("id_restaurant")));
				p.setQuantidade(Integer.parseInt(rs.getString("quantity")));
			}

			return p;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		return null;
	}
	
	public Product selectInRestaurant(Restaurant r, int id) throws DatabaseException {
		try {
			this.startConnection();
			
			String sql = "SELECT * FROM product WHERE id_product = "
					+ this.returnValueStringBD(String.valueOf(id)) 
					+ "AND id_restaurant = " + this.returnValueStringBD(String.valueOf(r.getId())) ;
			ResultSet rs = command.executeQuery(sql);
			Product p = new Product();
			if (rs.next()) {
				p.setId(Integer.parseInt(rs.getString("id_product")));
				p.setPrice(Double.parseDouble(rs.getString("price")));
				p.setNome(rs.getString("name"));
				p.setIdRestaurante(Integer.parseInt(rs.getString("id_restaurant")));
				p.setQuantidade(Integer.parseInt(rs.getString("quantity")));
			}

			return p;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		return null;
	}
	
	@Override
	public void update(Product p) throws DatabaseException {
		try {
			this.startConnection();
			StringBuffer buffer = new StringBuffer();
			buffer.append("UPDATE product SET ");
			buffer.append(returnFieldValuesBD(p));
			buffer.append(" WHERE id_produt=");
			buffer.append(p.getId());
			String sql = buffer.toString();

			command.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}
	
	public void delete(Product p) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "DELETE FROM product WHERE id_product="
					+ this.returnValueStringBD(String.valueOf(p.getId()));
			command.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
	}
	
	public void deleteProductInRestaurant(Product p, Restaurant r) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "DELETE FROM product WHERE id_product="
					+ this.returnValueStringBD(String.valueOf(p.getId()))
					+ "AND d_restaurant ="+this.returnValueStringBD(String.valueOf(r.getId()));
			command.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
	}

	public List<Product> retrieveProducts() throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT * FROM product";
			ResultSet rs = command.executeQuery(sql);
			List<Product> products = new ArrayList<Product>();
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
	
	public List<Product> retrieveProductsByPrice(double valor) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT * FROM product WHERE price="
			+ this.returnValueStringBD(String.valueOf(valor));
			ResultSet rs = command.executeQuery(sql);
			List<Product> products = new ArrayList<Product>();
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
	
	public List<Product> retrieveProductsByQuantity(int quant) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT * FROM product WHERE quantity="
			+ this.returnValueStringBD(String.valueOf(quant));
			ResultSet rs = command.executeQuery(sql);
			List<Product> products = new ArrayList<Product>();
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
	
	public List<Product> retrieveProductsByQuantityAsc(int quant) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT * FROM product WHERE quantity="
			+ this.returnValueStringBD(String.valueOf(quant)) + " ORDER BY quantity ASC";
			ResultSet rs = command.executeQuery(sql);
			List<Product> products = new ArrayList<Product>();
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
	
	public List<Product> retrieveProductsByQuantityDesc(int quant) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT * FROM product WHERE price="
			+ this.returnValueStringBD(String.valueOf(quant)) + "ORDER BY price DESC";
			ResultSet rs = command.executeQuery(sql);
			List<Product> products = new ArrayList<Product>();
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
	
	public List<Product> retrieveProductsByName(String name) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT * FROM product WHERE name LIKE "
			+ this.returnValueStringBD("%" + name + "%");
			ResultSet rs = command.executeQuery(sql);
			List<Product> products = new ArrayList<Product>();
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
	
	public List<Product> retrieveProductsByNameAsc(String name) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT * FROM product WHERE name LIKE "
			+ this.returnValueStringBD("%" + name + "%") + " ORDER BY name ASC";
			ResultSet rs = command.executeQuery(sql);
			List<Product> products = new ArrayList<Product>();
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
	
	@Override
	protected String returnFieldsBD() {
		return "price, name, id_restaurante, quantity";
	}

	@Override
	protected String returnFieldValuesBD(Product p) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("price=");
		buffer.append(returnValueStringBD(String.valueOf(p.getPrice())));
		buffer.append(", name=");
		buffer.append(returnValueStringBD(p.getNome()));
		buffer.append(", id_restaurant=");
		buffer.append(returnValueStringBD(String.valueOf(p.getIdRestaurante())));
		buffer.append(", quantidade=");
		buffer.append(returnValueStringBD(String.valueOf(p.getQuantidade())));
		return buffer.toString();
	}
	
	@Override
	protected String returnValuesBD(Product p) {
		return returnValueStringBD(String.valueOf(p.getPrice())) + ", " + returnValueStringBD(p.getNome()) + ", "
				+ returnValueStringBD(String.valueOf(p.getIdRestaurante())) + ", " + returnValueStringBD(String.valueOf(p.getQuantidade()));
	}
	
}
