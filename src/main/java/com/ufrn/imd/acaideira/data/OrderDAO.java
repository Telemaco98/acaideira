package com.ufrn.imd.acaideira.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ufrn.imd.acaideira.data.exception.DatabaseException;
import com.ufrn.imd.acaideira.domain.Order;

public class OrderDAO extends UtilsDAO<Order> implements DAO<Order> {
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
	public Order retrieve(int id) throws DatabaseException {
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

}
