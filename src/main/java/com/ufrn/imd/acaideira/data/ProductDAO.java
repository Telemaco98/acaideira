package com.ufrn.imd.acaideira.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ufrn.imd.acaideira.data.exception.DatabaseException;
import com.ufrn.imd.acaideira.domain.Product;
import com.ufrn.imd.acaideira.enums.ProductType;

public class ProductDAO extends UtilsDAO<Product> implements DAO<Product> {
	private static ProductDAO ProductDAO;

	private ProductDAO() throws DatabaseException { }
	
	public static synchronized ProductDAO getInstance() throws DatabaseException {
		if (ProductDAO == null) ProductDAO = new ProductDAO();
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
			throw new DatabaseException(e.getMessage());
		} finally {
			closeConnection();
		}
	}

	@Override
	public Product retrieve(int id) throws DatabaseException {
		try {
			this.startConnection();
			
			String sql = "SELECT * FROM product WHERE id_product = "
					+ this.returnValueStringBD(String.valueOf(id));
			ResultSet rs = command.executeQuery(sql);
			Product p = null;
			if (rs.next()) {				
				double price 	   = Double.parseDouble(rs.getString("price"));
				String name_bd 	   = rs.getString("name");
				int    amount	   = Integer.parseInt(rs.getString("amount"));
				String type_str    = rs.getString("type");
				String description = rs.getString("description");
				int id_restaurant  = Integer.parseInt(rs.getString("id_restaurant"));
				
				ProductType type = ProductType.StrToProductType(type_str); 

				p = new Product(id, price, name_bd, amount, type, description, id_restaurant);
			}
			
			return p;
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		} finally {
			this.closeConnection();
		}
	}
	
	public List<Product> retrieveProductsOfRestaurant (int id) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT * FROM product WHERE id_restaurant = "
					+ this.returnValueStringBD(String.valueOf(id));
			ResultSet rs = command.executeQuery(sql);
			
			List<Product> products = new ArrayList<Product>();
			while (rs.next()) {
				int    id_product  = Integer.parseInt(rs.getString("id_product"));
				double price 	   = Double.parseDouble(rs.getString("price"));
				String name_bd 	   = rs.getString("name");
				int    amount	   = Integer.parseInt(rs.getString("amount"));
				String type_str    = rs.getString("type");
				String description = rs.getString("description");
				
				ProductType type = ProductType.StrToProductType(type_str); 

				Product p = new Product(id_product, price, name_bd, amount, type, description, id);
				products.add(p);
			}

			return products;
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		} finally {
			this.closeConnection();
		}
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
			throw new DatabaseException(e.getMessage());
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
			throw new DatabaseException(e.getMessage());
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
				int    id_product  = Integer.parseInt(rs.getString("id_product"));
				double price 	   = Double.parseDouble(rs.getString("price"));
				String name_bd 	   = rs.getString("name");
				int    amount	   = Integer.parseInt(rs.getString("amount"));
				String type_str    = rs.getString("type");
				String description = rs.getString("description");
				int id_restaurant  = Integer.parseInt(rs.getString("id_restaurant"));
				
				ProductType type = ProductType.StrToProductType(type_str); 

				Product p = new Product(id_product, price, name_bd, amount, type, description, id_restaurant);
				products.add(p);
			}

			return products;
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		} finally {
			this.closeConnection();
		}
	}
	
	public List<Product> retrieveProductsByPrice(double valor) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT * FROM product WHERE price="
			+ this.returnValueStringBD(String.valueOf(valor));
			ResultSet rs = command.executeQuery(sql);
			
			List<Product> products = new ArrayList<Product>();
			while (rs.next()) {
				int    id_product  = Integer.parseInt(rs.getString("id_product"));
				double price 	   = Double.parseDouble(rs.getString("price"));
				String name_bd 	   = rs.getString("name");
				int    amount	   = Integer.parseInt(rs.getString("amount"));
				String type_str    = rs.getString("type");
				String description = rs.getString("description");
				int id_restaurant  = Integer.parseInt(rs.getString("id_restaurant"));
				
				ProductType type = ProductType.StrToProductType(type_str); 

				Product p = new Product(id_product, price, name_bd, amount, type, description, id_restaurant);
				products.add(p);
			}

			return products;
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		} finally {
			this.closeConnection();
		}
	}
	
	public List<Product> retrieveProductsByamount(int quant) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT * FROM product WHERE amount="
			+ this.returnValueStringBD(String.valueOf(quant));
			ResultSet rs = command.executeQuery(sql);
			
			List<Product> products = new ArrayList<Product>();
			while (rs.next()) {
				int    id_product  = Integer.parseInt(rs.getString("id_product"));
				double price 	   = Double.parseDouble(rs.getString("price"));
				String name_bd 	   = rs.getString("name");
				int    amount	   = Integer.parseInt(rs.getString("amount"));
				String type_str    = rs.getString("type");
				String description = rs.getString("description");
				int id_restaurant  = Integer.parseInt(rs.getString("id_restaurant"));
				
				ProductType type = ProductType.StrToProductType(type_str); 

				Product p = new Product(id_product, price, name_bd, amount, type, description, id_restaurant);
				products.add(p);
			}
			return products;
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		} finally {
			this.closeConnection();
		}
	}
	
	public List<Product> retrieveProductsByamountAsc(int quant) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT * FROM product WHERE amount="
			+ this.returnValueStringBD(String.valueOf(quant)) + " ORDER BY amount ASC";
			ResultSet rs = command.executeQuery(sql);
			
			List<Product> products = new ArrayList<Product>();
			while (rs.next()) {
				int    id_product  = Integer.parseInt(rs.getString("id_product"));
				double price 	   = Double.parseDouble(rs.getString("price"));
				String name_bd 	   = rs.getString("name");
				int    amount	   = Integer.parseInt(rs.getString("amount"));
				String type_str    = rs.getString("type");
				String description = rs.getString("description");
				int id_restaurant  = Integer.parseInt(rs.getString("id_restaurant"));
				
				ProductType type = ProductType.StrToProductType(type_str); 

				Product p = new Product(id_product, price, name_bd, amount, type, description, id_restaurant);
				products.add(p);
			}

			return products;
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		} finally {
			this.closeConnection();
		}
	}
	
	public List<Product> retrieveProductsByamountDesc(int quant) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT * FROM product WHERE price="
			+ this.returnValueStringBD(String.valueOf(quant)) + "ORDER BY price DESC";
			ResultSet rs = command.executeQuery(sql);
			List<Product> products = new ArrayList<Product>();
			while (rs.next()) {
				int    id_product  = Integer.parseInt(rs.getString("id_product"));
				double price 	   = Double.parseDouble(rs.getString("price"));
				String name_bd 	   = rs.getString("name");
				int    amount	   = Integer.parseInt(rs.getString("amount"));
				String type_str    = rs.getString("type");
				String description = rs.getString("description");
				int id_restaurant  = Integer.parseInt(rs.getString("id_restaurant"));
				
				ProductType type = ProductType.StrToProductType(type_str); 

				Product p = new Product(id_product, price, name_bd, amount, type, description, id_restaurant);
				products.add(p);
			}

			return products;
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		} finally {
			this.closeConnection();
		}
	}
	
	public List<Product> retrieveProductsByName(String name) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT * FROM product WHERE name LIKE "
			+ this.returnValueStringBD("%" + name + "%");
			ResultSet rs = command.executeQuery(sql);
			List<Product> products = new ArrayList<Product>();
			while (rs.next()) {
				int    id_product  = Integer.parseInt(rs.getString("id_product"));
				double price 	   = Double.parseDouble(rs.getString("price"));
				String name_bd 	   = rs.getString("name");
				int    amount	   = Integer.parseInt(rs.getString("amount"));
				String type_str    = rs.getString("type");
				String description = rs.getString("description");
				int id_restaurant  = Integer.parseInt(rs.getString("id_restaurant"));
				
				ProductType type = ProductType.StrToProductType(type_str); 

				Product p = new Product(id_product, price, name_bd, amount, type, description, id_restaurant);
				products.add(p);
			}

			return products;
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		} finally {
			this.closeConnection();
		}
	}
	
	public List<Product> retrieveProductsByNameAsc(String name) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT * FROM product WHERE name LIKE "
			+ this.returnValueStringBD("%" + name + "%") + " ORDER BY name ASC";
			ResultSet rs = command.executeQuery(sql);
			
			List<Product> products = new ArrayList<Product>();
			while (rs.next()) {				
				int    id_product  = Integer.parseInt(rs.getString("id_product"));
				double price 	   = Double.parseDouble(rs.getString("price"));
				String name_bd 	   = rs.getString("name");
				int    amount	   = Integer.parseInt(rs.getString("amount"));
				String type_str    = rs.getString("type");
				String description = rs.getString("description");
				int id_restaurant  = Integer.parseInt(rs.getString("id_restaurant"));
				
				ProductType type = ProductType.StrToProductType(type_str); 

				Product p = new Product(id_product, price, name_bd, amount, type, description, id_restaurant);
				products.add(p);
			}

			return products;
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		} finally {
			this.closeConnection();
		}
	}
	
	@Override
	protected String returnFieldsBD() {
		return "price, name, amount, type, description, id_restaurant";
	}

	@Override
	protected String returnFieldValuesBD(Product p) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("price=");
		buffer.append(returnValueStringBD(String.valueOf(p.getPrice())));
		buffer.append(", name=");
		buffer.append(returnValueStringBD(String.valueOf(p.getIdRestaurant())));
		buffer.append(", amount=");
		buffer.append(returnValueStringBD(String.valueOf(p.getAmount())));
		buffer.append(", type=");
		buffer.append(returnValueStringBD(String.valueOf(p.getType())));
		buffer.append(", description=");
		buffer.append(returnValueStringBD(String.valueOf(p.getDescription())));
		buffer.append(", id_restaurant=");
		buffer.append(returnValueStringBD(p.getName()));
		return buffer.toString();
	}
	
	@Override
	protected String returnValuesBD(Product p) {
		return returnValueStringBD(String.valueOf(p.getPrice())) + ", " +
				returnValueStringBD(p.getName()) + ", " + 
				returnValueStringBD(String.valueOf(p.getAmount())) + ", " +
				returnValueStringBD(String.valueOf(p.getType())) + ", " + 
				returnValueStringBD(p.getDescription()) + ", " + 
				returnValueStringBD(String.valueOf(p.getIdRestaurant()));
	}
}
