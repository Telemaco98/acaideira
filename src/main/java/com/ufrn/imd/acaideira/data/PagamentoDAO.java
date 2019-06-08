package com.ufrn.imd.acaideira.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ufrn.imd.acadeira.domain.*;
import com.ufrn.imd.acaideira.data.exception.DatabaseException;

public class PagamentoDAO implements DAO<Pagamento> {
	private Connection connection;
	private static PagamentoDAO pagamentoDAO;
	private Statement comando;

	private PagamentoDAO() throws DatabaseException {
		this.connection = ConnectionFactory.getConnection();
	}

	public static synchronized PagamentoDAO getInstance() throws DatabaseException {
		if (pagamentoDAO == null)
			pagamentoDAO = new PagamentoDAO();
		return pagamentoDAO;
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

	public void update(Pagamento p) throws DatabaseException {
		try {
			this.startConnection();
			StringBuffer buffer = new StringBuffer();
			buffer.append("UPDATE payment SET ");
			buffer.append(returnFieldValuesBD(p));
			buffer.append(" WHERE id_payment=");
			buffer.append(p.getId());
			String sql = buffer.toString();

			comando.executeUpdate(sql);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	public void insert(Pagamento p) throws DatabaseException {
		try {
			this.startConnection();

			StringBuffer buffer = new StringBuffer();
			buffer.append("INSERT INTO payment (");
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

	public Pagamento select(int id) throws DatabaseException {
		try {
			this.startConnection();
			
			String sql = "SELECT * FROM payment WHERE id_payment = "
					+ this.retornarValorStringBD(String.valueOf(id));
			ResultSet rs = comando.executeQuery(sql);
			Pagamento p = new Pagamento();
			if (rs.next()) {
				p.setId(Integer.parseInt(rs.getString("id_payment")));
				p.setStatus(rs.getString("status"));
				p.setValor(Double.parseDouble(rs.getString("amount")));
				p.setTipo(rs.getString("way"));
			}

			return p;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		return null;
	}

	public void delete(Pagamento p) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "DELETE FROM payment WHERE id_payment="
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

	public List<Pagamento> retrievePayments() throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT * FROM payment";
			ResultSet rs = comando.executeQuery(sql);
			List<Pagamento> payments = new ArrayList<Pagamento>();
			while (rs.next()) {
				Pagamento p = new Pagamento();
				p.setId(Integer.parseInt(rs.getString("id_payment")));
				p.setStatus(rs.getString("status"));
				p.setValor(Double.parseDouble(rs.getString("amount")));
				p.setTipo(rs.getString("way"));
				payments.add(p);
			}

			return payments;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		return null;
	}
	
	public List<Pagamento> retrievePaymentsByType(String type) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT * FROM payment type LIKE "
					+ this.retornarValorStringBD("%" + type + "%");
			ResultSet rs = comando.executeQuery(sql);
			List<Pagamento> payments = new ArrayList<Pagamento>();
			while (rs.next()) {
				Pagamento p = new Pagamento();
				p.setId(Integer.parseInt(rs.getString("id_payment")));
				p.setStatus(rs.getString("status"));
				p.setValor(Double.parseDouble(rs.getString("amount")));
				p.setTipo(rs.getString("way"));
				payments.add(p);
			}

			return payments;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		return null;
	}
	
	//retrive all payments did it by a client
	public List<Pagamento> retrivePaymentsByClient(Cliente c) throws DatabaseException{
		try {
			this.startConnection();
			String sql = "SELECT id_payment FROM purchase WHERE id_client = " + retornarValorStringBD(String.valueOf(c.getId_cliente()));
			ResultSet rs = comando.executeQuery(sql);
			List<String> id_payments = new ArrayList<String>();
			while (rs.next()) {
				String pu = rs.getString("id_payment");
				id_payments.add(pu);
			}
			List<Pagamento> payments = new ArrayList<Pagamento>();
			for(String id: id_payments) {
				Pagamento p = this.select(Integer.parseInt(id));
				payments.add(p);
			}
			return payments;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		return null;
	}
	
	//retrive all payments for a order
	public List<Pagamento> retrivePaymentsByPedido(Pedido p) throws DatabaseException{
		try {
			this.startConnection();
			String sql = "SELECT id_payment FROM purchase WHERE id_order = " + retornarValorStringBD(String.valueOf(p.getId()));
			ResultSet rs = comando.executeQuery(sql);
			List<String> id_payments = new ArrayList<String>();
			while (rs.next()) {
				String pu = rs.getString("id_payment");
				id_payments.add(pu);
			}
			List<Pagamento> payments = new ArrayList<Pagamento>();
			for(String id: id_payments) {
				Pagamento pa = this.select(Integer.parseInt(id));
				payments.add(pa);
			}
			return payments;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		return null;
	}
	
	protected String retornarCamposBD() {
		return "status, amount, way";
	}

	protected String returnFieldValuesBD(Pagamento p) {

		StringBuffer buffer = new StringBuffer();
		buffer.append("status=");
		buffer.append(retornarValorStringBD(p.getStatus()));
		buffer.append(", amount=");
		buffer.append(retornarValorStringBD(String.valueOf(p.getValor())));
		buffer.append(", way=");
		buffer.append(retornarValorStringBD(p.getTipo()));
		return buffer.toString();
	}

	protected String retornarValoresBD(Pagamento p) {
		return retornarValorStringBD(p.getStatus()) + ", " + retornarValorStringBD(String.valueOf(p.getValor())) + ", "
				+ retornarValorStringBD(p.getTipo());
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
