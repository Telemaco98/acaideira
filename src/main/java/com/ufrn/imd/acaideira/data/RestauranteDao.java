package com.ufrn.imd.acaideira.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ufrn.imd.acadeira.domain.*;
import com.ufrn.imd.acaideira.data.exception.DatabaseException;

public class RestauranteDAO implements DAO<Restaurante> {
	private Connection connection;
	private static RestauranteDAO restauranteDAO;
	private Statement comando;

	private RestauranteDAO() throws DatabaseException {
		this.connection = ConnectionFactory.getConnection();
	}

	public static synchronized RestauranteDAO getInstance() throws DatabaseException {
		if (restauranteDAO == null)
			restauranteDAO = new RestauranteDAO();
		return restauranteDAO;
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

	public void update(Restaurante r) throws DatabaseException {
		try {
			this.startConnection();
			StringBuffer buffer = new StringBuffer();
			buffer.append("UPDATE restaurante SET ");
			buffer.append(returnFieldValuesBD(r));
			buffer.append(" WHERE id_restaunte=");
			buffer.append(r.getId_restaurante());
			String sql = buffer.toString();

			comando.executeUpdate(sql);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	public void insert(Restaurante r) throws DatabaseException {
		try {
			this.startConnection();

			StringBuffer buffer = new StringBuffer();
			buffer.append("INSERT INTO restaurante (");
			buffer.append(this.retornarCamposBD());
			buffer.append(") VALUES (");
			buffer.append(retornarValoresBD(r));
			buffer.append(")");
			String sql = buffer.toString();
			comando.executeUpdate(sql);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	public Restaurante select(int id) throws DatabaseException {
		try {
			this.startConnection();
			
			String sql = "SELECT * FROM EMPLOYEE WHERE id_restaurante = "
					+ this.retornarValorStringBD(String.valueOf(id));
			ResultSet rs = comando.executeQuery(sql);
			Restaurante r = new Restaurante();
			if (rs.next()) {
				r.setNome(rs.getString("nome"));
				r.setTipo(rs.getString("tipo"));
				r.setEndereco(rs.getString("endereco"));
			}

			return r;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		return null;
	}

	public void delete(Restaurante r) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "DELETE FROM restaurante WHERE id_restaurante="
					+ this.retornarValorStringBD(String.valueOf(r.getId_restaurante()));
			comando.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
	}

	public List<Restaurante> retrieveRestaurantes() throws DatabaseException {
		try {
			this.startConnection();
			String sql = "SELECT * FROM restaurante";
			ResultSet rs = comando.executeQuery(sql);
			List<Restaurante> rest = new ArrayList<Restaurante>();
			while (rs.next()) {
				Restaurante r = new Restaurante();
				r.setNome(rs.getString("nome"));
				r.setTipo(rs.getString("tipo"));
				r.setEndereco(rs.getString("endereco"));
				rest.add(r);
			}

			return rest;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		return null;
	}

	protected String retornarCamposBD() {
		return "nome, tipo, endereco";
	}

	protected String returnFieldValuesBD(Restaurante r) {

		StringBuffer buffer = new StringBuffer();
		buffer.append("nome=");
		buffer.append(retornarValorStringBD(r.getNome()));
		buffer.append(", tipo=");
		buffer.append(retornarValorStringBD(r.getTipo()));
		buffer.append(", endereco=");
		buffer.append(retornarValorStringBD(r.getEndereco()));
		return buffer.toString();
	}

	protected String retornarValoresBD(Restaurante r) {
		return retornarValorStringBD(r.getNome()) + ", " + retornarValorStringBD(r.getTipo()) + ", "
				+ retornarValorStringBD(r.getEndereco());
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
