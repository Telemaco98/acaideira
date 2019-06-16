package com.ufrn.imd.acaideira.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.Data;

import com.ufrn.imd.acaideira.data.exception.DatabaseException;
import com.ufrn.imd.acaideira.domain.Client;
import com.ufrn.imd.acaideira.domain.Order;
import com.ufrn.imd.acaideira.domain.Product;

public class OrderDAO extends UtilsDAO<OrderDAO, Order> implements DAO<Order> {
	private static OrderDAO orderDAO;

	private OrderDAO() throws DatabaseException {
		this.connection = ConnectionFactory.getConnection();
	}

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
			buffer.append("INSERT INTO order (");
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

			String sql = "SELECT * FROM order WHERE id_order = " + returnValueStringBD(String.valueOf(id));
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
			buffer.append("UPDATE order SET ");
			buffer.append(returnFieldValuesBD(order));
			buffer.append(" WHERE id_order=");
			buffer.append(order.getId());
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
			String sql = "DELETE FROM order WHERE id_order=" + returnValueStringBD(String.valueOf(order.getId()));
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
			String sql = "SELECT * FROM order";
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
			String sql = "SELECT * FROM order_client JOIM order WHERE id_client = `"+client.getId_client()+"`";
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
			String sql = "SELECT * FROM order WHERE type=" + this.returnValueStringBD("%" + type + "%");
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
		return "status";
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
	
	public void creatOrderClient(int clientID, int orderID) throws DatabaseException{
		try {
			this.startConnection();
			String sql = "INSERT INTO order_client(`id_client`, `id_order`) VALUES("+clientID+","+orderID+")";
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
			String sql = "select * from order_client JOIN order WHERE id_client = "+ idClient+" ORDER BY id_client desc limit 1";
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
			String sql = "INSERT INTO product_order(`id_product`, `id_order`, `quantity`) VALUES()";
			command.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
	}
}
