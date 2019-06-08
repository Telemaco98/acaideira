package com.ufrn.imd.acaideira.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ufrn.imd.acadeira.domain.*;
import com.ufrn.imd.acaideira.data.exception.DatabaseException;

public class PedidoDAO implements DAO<Pedido>{
	private Connection connection;
	private static PedidoDAO pedidoDAO;
	private Statement comando;

	private PedidoDAO() throws DatabaseException {
		this.connection = ConnectionFactory.getConnection();
	}

	public static synchronized PedidoDAO getInstance() throws DatabaseException {
		if (pedidoDAO == null)
			pedidoDAO = new PedidoDAO();
		return pedidoDAO;
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
	
	public void update(Pedido p) throws DatabaseException {
		try {
			this.startConnection();
			StringBuffer buffer = new StringBuffer();
			buffer.append("UPDATE order SET ");
			buffer.append(returnFieldValuesBD(p));
			buffer.append(" WHERE id_order=");
			buffer.append(p.getId());
			String sql = buffer.toString();

			comando.executeUpdate(sql);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	public void insert(Pedido p) throws DatabaseException {
		try {
			this.startConnection();

			StringBuffer buffer = new StringBuffer();
			buffer.append("INSERT INTO order (");
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

	public Pedido select(int id) throws DatabaseException {
		try {
			this.startConnection();
			
			String sql = "SELECT * FROM order WHERE id_order = "
					+ this.retornarValorStringBD(String.valueOf(id));
			ResultSet rs = comando.executeQuery(sql);
			Pedido p = new Pedido();
			if (rs.next()) {
				p.setId(Integer.parseInt(rs.getString("id_order")));
				p.setStatus(rs.getString("status"));
			}

			return p;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		return null;
	}

	public void delete(Pedido p) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "DELETE FROM order WHERE id_order="
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

	public List<Pedido> retrievePayments() throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT * FROM order";
			ResultSet rs = comando.executeQuery(sql);
			List<Pedido> payments = new ArrayList<Pedido>();
			while (rs.next()) {
				Pedido p = new Pedido();
				p.setId(Integer.parseInt(rs.getString("id_order")));
				p.setStatus(rs.getString("status"));
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
	
	public List<Pedido> retrievePaymentsByType(String type) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT * FROM order WHERE type="
					+ this.retornarValorStringBD("%" + type + "%");
			ResultSet rs = comando.executeQuery(sql);
			List<Pedido> payments = new ArrayList<Pedido>();
			while (rs.next()) {
				Pedido p = new Pedido();
				p.setId(Integer.parseInt(rs.getString("id_order")));
				p.setStatus(rs.getString("status"));
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
	
	protected String retornarCamposBD() {
		return "status";
	}

	protected String returnFieldValuesBD(Pedido p) {

		StringBuffer buffer = new StringBuffer();
		buffer.append("status=");
		buffer.append(retornarValorStringBD(p.getStatus()));
		return buffer.toString();
	}

	protected String retornarValoresBD(Pedido p) {
		return retornarValorStringBD(p.getStatus());
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
