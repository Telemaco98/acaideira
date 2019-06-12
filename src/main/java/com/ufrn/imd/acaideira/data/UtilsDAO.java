package com.ufrn.imd.acaideira.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.ufrn.imd.acaideira.data.exception.DatabaseException;

public abstract class UtilsDAO <Type, EntityType> {
	protected Connection connection; 
	protected Statement command;
	
	public UtilsDAO() { }
	
	protected void startConnection() throws DatabaseException {
		try {
			connection = ConnectionFactory.getConnection();
			command = connection.createStatement();
			System.out.println("Connection Success!");
		} catch (SQLException e) {
			System.out.println("Connection Failed!");
			System.out.println(e.getMessage());
		}
	}

	protected void closeConnection() {
		try {
			if (!command.isClosed())
				command.close();
			if (connection.isReadOnly())
				connection.close();
			System.out.println("Closing Success!");
		} catch (SQLException e) {
			System.out.println("Closing Failed!");
			System.out.println(e.getMessage());
		}
		
		
	}
	
	public String returnValueStringBD(String value) {
		if (value != null && !"".equals(value)) value = "'" + value + "'";
		else value = "'" + "'";
		
		return value;
	}
	
	public void commit() throws Exception {
    	try {
			this.commitTransaction();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	
    }
    
	public void cancelTransaction() throws Exception {
	    if (connection == null) {
	        throw new Exception("There is no opened connection");
	    }
	    try {
	    	connection.rollback();
	    } finally {
	    	connection.close();
	    	connection = null;
	    }
	}

	public void commitTransaction() throws Exception {
	    if (connection == null) {
	        throw new Exception("There is no opened connection");
	    }
	    try {
	    	connection.commit();
	    } finally {
	    	connection.close();
	    	connection = null;
	    }
	}
	
	protected abstract String returnFieldsBD();

	protected abstract String returnFieldValuesBD(EntityType t);

	protected abstract String returnValuesBD(EntityType t);
}