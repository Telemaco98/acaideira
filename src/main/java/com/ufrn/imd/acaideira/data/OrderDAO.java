package com.ufrn.imd.acaideira.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ufrn.imd.acaideira.data.exception.DatabaseException;
import com.ufrn.imd.acaideira.domain.Product;
import com.ufrn.imd.acaideira.domain.Order;
import com.ufrn.imd.acaideira.domain.Client;

public class OrderDAO extends UtilsDAO<OrderDAO, Order> implements DAO<Order> {
	private static OrderDAO orderDAO;

	private OrderDAO() throws DatabaseException { }

	public static synchronized OrderDAO getInstance() throws DatabaseException {
		if (orderDAO == null)
			orderDAO = new OrderDAO();
		return orderDAO;
	}

	@Override
	public void insert(Order order) throws DatabaseException {
		try {
			this.startConnection();
			StringBuffer buffer = new StringBuffer();
			buffer.append("INSERT INTO `order` (");
			buffer.append(returnFieldsBD());
			buffer.append(") VALUES (");
			buffer.append(returnValuesBD(order));
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
	public Order select(int id) throws DatabaseException {
		try {
			this.startConnection();

			String sql = "SELECT * FROM `order` WHERE id_order = " + returnValueStringBD(String.valueOf(id));
			ResultSet rs = command.executeQuery(sql);
			Order p = new Order();
			if (rs.next()) {
				p.setId(Integer.parseInt(rs.getString("id_order")));
				p.setStatus(rs.getString("status"));
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
	public void update(Order order) throws DatabaseException {
		try {
			this.startConnection();
			StringBuffer buffer = new StringBuffer();
			buffer.append("INSERT INTO order (");
			buffer.append(this.returnFieldsBD());
			buffer.append(") VALUES(");
			buffer.append(returnValuesBD(order));
			buffer.append(")");
			String sql = buffer.toString();
			command.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	public void delete(Order order) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "DELETE FROM `order` WHERE id_order=" + returnValueStringBD(String.valueOf(order.getId()));
			command.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
	}

	public List<Order> retrievePayments() throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT * FROM `order`";
			ResultSet rs = command.executeQuery(sql);
			List<Order> payments = new ArrayList<Order>();
			while (rs.next()) {
				Order p = new Order();
				p.setId(Integer.parseInt(rs.getString("id_order")));
				p.setStatus(rs.getString("status"));
				payments.add(p);
			}

			return payments;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		return null;
	}

	public List<Order> retrievePaymentsBYId(Client client) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT * FROM order_client JOIM `order` WHERE id_client = "+client.getId_client();
			ResultSet rs = command.executeQuery(sql);
			List<Order> payments = new ArrayList<Order>();
			while (rs.next()) {
				Order p = new Order();
				p.setId(Integer.parseInt(rs.getString("id_order")));
				p.setStatus(rs.getString("status"));
				payments.add(p);
			}

			return payments;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		return null;
	}

	public List<Order> retrievePaymentsByType(String type) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT * FROM `order` WHERE type=" + this.returnValueStringBD("%" + type + "%");
			ResultSet rs = command.executeQuery(sql);
			List<Order> payments = new ArrayList<Order>();
			while (rs.next()) {
				Order p = new Order();
				p.setId(Integer.parseInt(rs.getString("id_order")));
				p.setStatus(rs.getString("status"));
				payments.add(p);
			}

			return payments;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		return null;
	}

	@Override
	protected String returnFieldsBD() {
		return "`status`";
	}

	@Override
	protected String returnFieldValuesBD(Order order) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("status=");
		buffer.append(returnValueStringBD(order.getStatus()));
		return buffer.toString();
	}

	@Override
	protected String returnValuesBD(Order order) {
		return returnValueStringBD(order.getStatus());
	}
	
	public void creatOrderClient(Client client, Order order) throws DatabaseException{
		try {
			this.startConnection();
			String sql = "INSERT INTO order_client(`id_client`, `id_order`) VALUES("+returnValueStringBD(String.valueOf(client.getId_client()))+","
			+returnValueStringBD(String.valueOf(order.getId()))+")";
			command.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}

	}
	public Order selectOrderbyClient(int idClient) throws DatabaseException{
		try {
			this.startConnection();
			String sql = "select * from order_client JOIN `order` WHERE id_client = "+ idClient+" ORDER BY id_client desc limit 1";
			ResultSet rs = command.executeQuery(sql);
			Order p = new Order();
			if (rs.next()) {
				p.setId(Integer.parseInt(rs.getString("id_order")));
				p.setStatus(rs.getString("status"));
			}

			return p;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		return null;
	}
	
	public void addToCart(Product product, Order order, int qtd) throws DatabaseException{
		
		try {
			this.startConnection();
			String sql = "INSERT INTO product_order (`id_product`,`id_order`,`quantity`) VALUES("
			+returnValueStringBD(String.valueOf(product.getId()))+ ","
			+returnValueStringBD(String.valueOf(order.getId())) + ","
			+returnValueStringBD(String.valueOf(qtd))+")";
			command.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
	}
	public void repeatOrderProducts(Order order) throws DatabaseException{
		try{
			this.startConnection();
			String sql = "SELECT * FROM product_order JOIN product WHERE id_order = `"+order.getId()+"`";
			ResultSet rs = command.executeQuery(sql);
			List<Product> products = new ArrayList<Product>();
			if(rs.next()) {
				Product p = new Product();
				p.setId(Integer.parseInt(rs.getString("id_order")));
				p.setQuantidade(Integer.parseInt(rs.getString("product_order.quantity")));
				products.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
	}
	
	public float valueToPay(Order order) throws DatabaseException{
		try {
			this.startConnection();
			String sql = "SELECT * FROM product_order JOIN product WHERE id_order = "+order.getId();
			ResultSet rs = command.executeQuery(sql);
			float total = 0;
			if(rs.next()) {
				int value = Integer.parseInt(rs.getString("price"));
				total += value * (Integer.parseInt(rs.getString("quantity")));
			}
			return total;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		return 0;
	}

	public Order lastOrder() throws Exception{
		try {
			this.startConnection();
			String sql = "select * from `order` ORDER BY id_order desc limit 1";
			ResultSet rs = command.executeQuery(sql);
			Order p = new Order();
			if (rs.next()) {
				p.setId(Integer.parseInt(rs.getString("id_order")));
				p.setStatus(rs.getString("status"));
			}
			return p;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		return null;
	}
	
}
