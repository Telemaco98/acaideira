package com.ufrn.imd.acaideira.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ufrn.imd.acadeira.domain.*;
import com.ufrn.imd.acaideira.data.exception.DatabaseException;

public class ProdutoDAO implements DAO<Produto> {
	private Connection connection;
	private static ProdutoDAO produtoDAO;
	private Statement comando;

	private ProdutoDAO() throws DatabaseException {
		this.connection = ConnectionFactory.getConnection();
	}
	
	public static synchronized ProdutoDAO getInstance() throws DatabaseException {
		if (produtoDAO == null)
			produtoDAO = new ProdutoDAO();
		
		return produtoDAO;
	}

	private void startConnection() throws ClassNotFoundException, SQLException, DatabaseException {
		connection = ConnectionFactory.getConnection();
		comando = connection.createStatement();
		System.out.println("Conectado!");
	}

	private void closeConnection() {
		try {
			comando.close();
			connection.close();
			System.out.println("Conexão Fechada");
		} catch (SQLException e) {
		}
	}

	public void update(Produto p) throws DatabaseException {
		try {
			this.startConnection();
			StringBuffer buffer = new StringBuffer();
			buffer.append("UPDATE product SET ");
			buffer.append(returnFieldValuesBD(p));
			buffer.append(" WHERE id_produt=");
			buffer.append(p.getId());
			String sql = buffer.toString();

			comando.executeUpdate(sql);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	public void insert(Produto p) throws DatabaseException {
		try {
			this.startConnection();

			StringBuffer buffer = new StringBuffer();
			buffer.append("INSERT INTO product (");
			buffer.append(this.retornarCamposBD());
			buffer.append(") VALUES (");
			buffer.append(retornarValoresBD(p));
			buffer.append(")");
			String sql = buffer.toString();
			comando.executeUpdate(sql);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	public Produto select(int id) throws DatabaseException {
		try {
			this.startConnection();
			
			String sql = "SELECT * FROM product WHERE id_product = "
					+ this.retornarValorStringBD(String.valueOf(id));
			ResultSet rs = comando.executeQuery(sql);
			Produto p = new Produto();
			if (rs.next()) {
				p.setId(Integer.parseInt(rs.getString("id_product")));
				p.setPrice(Double.parseDouble(rs.getString("price")));
				p.setNome(rs.getString("name"));
				p.setIdRestaurante(Integer.parseInt(rs.getString("id_restaurant")));
				p.setQuantidade(Integer.parseInt(rs.getString("quantity")));
				
				
			}

			return p;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		return null;
	}
	
	public void delete(Produto p) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "DELETE FROM product WHERE id_product="
					+ this.retornarValorStringBD(String.valueOf(p.getId()));
			comando.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
	}

	public List<Produto> retrieveProdutos() throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT * FROM product";
			ResultSet rs = comando.executeQuery(sql);
			List<Produto> products = new ArrayList<Produto>();
			while (rs.next()) {
				Produto p = new Produto();
				p.setId(Integer.parseInt(rs.getString("id_product")));
				p.setPrice(Double.parseDouble(rs.getString("price")));
				p.setNome(rs.getString("name"));
				p.setIdRestaurante(Integer.parseInt(rs.getString("id_restaurant")));
				p.setQuantidade(Integer.parseInt(rs.getString("quantity")));
				products.add(p);
			}

			return products;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		return null;
	}
	
	public List<Produto> retrieveProdutosByPrice(double valor) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT * FROM product WHERE price="
			+ this.retornarValorStringBD(String.valueOf(valor));
			ResultSet rs = comando.executeQuery(sql);
			List<Produto> products = new ArrayList<Produto>();
			while (rs.next()) {
				Produto p = new Produto();
				p.setId(Integer.parseInt(rs.getString("id_product")));
				p.setPrice(Double.parseDouble(rs.getString("price")));
				p.setNome(rs.getString("name"));
				p.setIdRestaurante(Integer.parseInt(rs.getString("id_restaurant")));
				p.setQuantidade(Integer.parseInt(rs.getString("quantity")));
				products.add(p);
			}

			return products;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		return null;
	}
	
	public List<Produto> retrieveProdutosByQuantity(int quant) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT * FROM product WHERE quantity="
			+ this.retornarValorStringBD(String.valueOf(quant));
			ResultSet rs = comando.executeQuery(sql);
			List<Produto> products = new ArrayList<Produto>();
			while (rs.next()) {
				Produto p = new Produto();
				p.setId(Integer.parseInt(rs.getString("id_product")));
				p.setPrice(Double.parseDouble(rs.getString("price")));
				p.setNome(rs.getString("name"));
				p.setIdRestaurante(Integer.parseInt(rs.getString("id_restaurant")));
				p.setQuantidade(Integer.parseInt(rs.getString("quantity")));
				products.add(p);
			}
			return products;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		return null;
	}
	
	public List<Produto> retrieveProdutosByQuantityAsc(int quant) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT * FROM product WHERE quantity="
			+ this.retornarValorStringBD(String.valueOf(quant)) + " ORDER BY quantity ASC";
			ResultSet rs = comando.executeQuery(sql);
			List<Produto> products = new ArrayList<Produto>();
			while (rs.next()) {
				Produto p = new Produto();
				p.setId(Integer.parseInt(rs.getString("id_product")));
				p.setPrice(Double.parseDouble(rs.getString("price")));
				p.setNome(rs.getString("name"));
				p.setIdRestaurante(Integer.parseInt(rs.getString("id_restaurant")));
				p.setQuantidade(Integer.parseInt(rs.getString("quantity")));
				products.add(p);
			}

			return products;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		return null;
	}
	
	public List<Produto> retrieveProdutosByQuantityDesc(int quant) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT * FROM product WHERE price="
			+ this.retornarValorStringBD(String.valueOf(quant)) + "ORDER BY price DESC";
			ResultSet rs = comando.executeQuery(sql);
			List<Produto> products = new ArrayList<Produto>();
			while (rs.next()) {
				Produto p = new Produto();
				p.setId(Integer.parseInt(rs.getString("id_product")));
				p.setPrice(Double.parseDouble(rs.getString("price")));
				p.setNome(rs.getString("name"));
				p.setIdRestaurante(Integer.parseInt(rs.getString("id_restaurant")));
				p.setQuantidade(Integer.parseInt(rs.getString("quantity")));
				products.add(p);
			}

			return products;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		return null;
	}
	
	public List<Produto> retrieveProdutosByName(String name) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT * FROM product WHERE name LIKE "
			+ this.retornarValorStringBD("%" + name + "%");
			ResultSet rs = comando.executeQuery(sql);
			List<Produto> products = new ArrayList<Produto>();
			while (rs.next()) {
				Produto p = new Produto();
				p.setId(Integer.parseInt(rs.getString("id_product")));
				p.setPrice(Double.parseDouble(rs.getString("price")));
				p.setNome(rs.getString("name"));
				p.setIdRestaurante(Integer.parseInt(rs.getString("id_restaurant")));
				p.setQuantidade(Integer.parseInt(rs.getString("quantity")));
				products.add(p);
			}

			return products;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		return null;
	}
	
	public List<Produto> retrieveProdutosByNameAsc(String name) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT * FROM product WHERE name LIKE "
			+ this.retornarValorStringBD("%" + name + "%") + " ORDER BY name ASC";
			ResultSet rs = comando.executeQuery(sql);
			List<Produto> products = new ArrayList<Produto>();
			while (rs.next()) {
				Produto p = new Produto();
				p.setId(Integer.parseInt(rs.getString("id_product")));
				p.setPrice(Double.parseDouble(rs.getString("price")));
				p.setNome(rs.getString("name"));
				p.setIdRestaurante(Integer.parseInt(rs.getString("id_restaurant")));
				p.setQuantidade(Integer.parseInt(rs.getString("quantity")));
				products.add(p);
			}

			return products;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		return null;
	}
	
	protected String retornarCamposBD() {
		return "price, name, id_restaurante, quantity";
	}

	protected String returnFieldValuesBD(Produto p) {

		StringBuffer buffer = new StringBuffer();
		buffer.append("price=");
		buffer.append(retornarValorStringBD(String.valueOf(p.getPrice())));
		buffer.append(", name=");
		buffer.append(retornarValorStringBD(p.getNome()));
		buffer.append(", id_restaurant=");
		buffer.append(retornarValorStringBD(String.valueOf(p.getIdRestaurante())));
		buffer.append(", quantidade=");
		buffer.append(retornarValorStringBD(String.valueOf(p.getQuantidade())));
		return buffer.toString();
	}

	protected String retornarValoresBD(Produto p) {
		return retornarValorStringBD(String.valueOf(p.getPrice())) + ", " + retornarValorStringBD(p.getNome()) + ", "
				+ retornarValorStringBD(String.valueOf(p.getIdRestaurante())) + ", " + retornarValorStringBD(String.valueOf(p.getQuantidade()));
	} 

	private String retornarValorStringBD(String valor) {
		if (valor != null && !"".equals(valor)) {
			valor = "'" + valor + "'";
		} else {
			valor = "'" + "'";
		}
		return valor;
	}
}
