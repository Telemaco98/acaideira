package com.ufrn.imd.acaideira.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.ufrn.imd.acaideira.data.exception.DatabaseException;

public abstract class UtilsDAO <Type, EntityType> {
	protected Connection connection; 
	protected Statement command;
	
	public abstract Type getInstance();
	
	protected void startConnection() throws ClassNotFoundException, SQLException, DatabaseException {
		connection = ConnectionFactory.getConnection();
		command = connection.createStatement();
		System.out.println("Connection Success!");
	}

	protected void closeConnection() {
		try {
			command.close();
			connection.close();
			System.out.println("Connection Failed");
		} catch (SQLException e) {
		}
	}
	
	public String retornValueStringBD(String value) {
		if (value != null && !"".equals(value)) value = "'" + value + "'";
		else value = "'" + "'";
		
		return value;
	}
	
	protected abstract String returnFieldsBD();

	protected abstract String returnFieldValuesBD(EntityType t);

	protected abstract String returnValuesBD(EntityType t);
}