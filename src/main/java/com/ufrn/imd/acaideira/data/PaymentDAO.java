package com.ufrn.imd.acaideira.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ufrn.imd.acaideira.data.exception.DatabaseException;
import com.ufrn.imd.acaideira.domain.Client;
import com.ufrn.imd.acaideira.domain.Order;
import com.ufrn.imd.acaideira.domain.Payment;

public class PaymentDAO extends UtilsDAO<PaymentDAO, Payment> implements DAO<Payment> {
	private static PaymentDAO paymentDAO;

	private PaymentDAO() { }

	public static synchronized PaymentDAO getInstance() throws DatabaseException {
		if (paymentDAO == null)
			paymentDAO = new PaymentDAO();
		return paymentDAO;
	}

	public void insert(Payment p) throws DatabaseException {
		try {
			this.startConnection();

			StringBuffer buffer = new StringBuffer();
			buffer.append("INSERT INTO payment (");
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
	public Payment select(int id) throws DatabaseException {
		try {
			this.startConnection();

			String sql = "SELECT * FROM payment WHERE id_payment = " + this.returnValueStringBD(String.valueOf(id));
			ResultSet rs = command.executeQuery(sql);

			if (rs.next()) {
				String status = rs.getString("status");
				double amount = Double.parseDouble(rs.getString("amount"));
				String type = rs.getString("way");

				return new Payment(id, status, amount, type);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}

		return null;
	}

	@Override
	public void update(Payment p) throws DatabaseException {
		try {
			this.startConnection();
			StringBuffer buffer = new StringBuffer();
			buffer.append("UPDATE payment SET ");
			buffer.append(returnFieldValuesBD(p));
			buffer.append(" WHERE id_payment=");
			buffer.append(p.getId());
			String sql = buffer.toString();

			command.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	public void delete(Payment p) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "DELETE FROM payment WHERE id_payment=" + this.returnValueStringBD(String.valueOf(p.getId()));
			command.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
	}

	public List<Payment> retrievePayments() throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT * FROM payment";
			ResultSet rs = command.executeQuery(sql);
			List<Payment> payments = new ArrayList<Payment>();
			while (rs.next()) {
				Payment p = new Payment();
				p.setId(Integer.parseInt(rs.getString("id_payment")));
				p.setStatus(rs.getString("status"));
				p.setValor(Double.parseDouble(rs.getString("amount")));
				p.setTipo(rs.getString("way"));
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

	public List<Payment> retrievePaymentsByType(String type) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT * FROM payment type LIKE " + this.returnValueStringBD("%" + type + "%");
			ResultSet rs = command.executeQuery(sql);
			List<Payment> payments = new ArrayList<Payment>();
			while (rs.next()) {
				Payment p = new Payment();
				p.setId(Integer.parseInt(rs.getString("id_payment")));
				p.setStatus(rs.getString("status"));
				p.setValor(Double.parseDouble(rs.getString("amount")));
				p.setTipo(rs.getString("way"));
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

	/**
	 * Retrieves all payments did it by a client
	 * 
	 * @param client
	 * @return
	 * @throws DatabaseException
	 */
	public List<Payment> retrievePaymentsByClient(Client client) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT id_payment FROM purchase WHERE id_client = "
					+ returnValueStringBD(String.valueOf(client.getId_client()));
			ResultSet rs = command.executeQuery(sql);
			List<String> id_payments = new ArrayList<String>();
			while (rs.next()) {
				String pu = rs.getString("id_payment");
				id_payments.add(pu);
			}
			List<Payment> payments = new ArrayList<Payment>();
			for (String id : id_payments) {
				Payment p = this.select(Integer.parseInt(id));
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

	/**
	 * Retrieves all payments for a order
	 * 
	 * @param order
	 * @return
	 * @throws DatabaseException
	 */
	public List<Payment> retrievePaymentsByPedido(Order order) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT id_payment FROM purchase WHERE id_order = "
					+ returnValueStringBD(String.valueOf(order.getId()));
			ResultSet rs = command.executeQuery(sql);
			List<String> id_payments = new ArrayList<String>();
			while (rs.next()) {
				String pu = rs.getString("id_payment");
				id_payments.add(pu);
			}
			List<Payment> payments = new ArrayList<Payment>();
			for (String id : id_payments) {
				Payment pa = this.select(Integer.parseInt(id));
				payments.add(pa);
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
		return "status, amount, way";
	}

	protected String returnFieldValuesBD(Payment p) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("status=");
		buffer.append(returnValueStringBD(p.getStatus()));
		buffer.append(", amount=");
		buffer.append(returnValueStringBD(String.valueOf(p.getValor())));
		buffer.append(", way=");
		buffer.append(returnValueStringBD(p.getTipo()));
		return buffer.toString();
	}

	@Override
	protected String returnValuesBD(Payment p) {
		return returnValueStringBD(p.getStatus()) + ", " + returnValueStringBD(String.valueOf(p.getValor())) + ", "
				+ returnValueStringBD(p.getTipo());
	}
}
