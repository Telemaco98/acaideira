package com.ufrn.imd.acaideira.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.ufrn.imd.acaideira.data.exception.DatabaseException;

/**
 * Class to do the connection with the database
 * @author Shirley Ohara
 *
 */
public class ConnectionFactory {
	private static final String timezone = "?useTimezone=true&serverTimezone=UTC";
	private static final String bd = "mydb";
	private static final String url = "jdbc:mysql://localhost:3306/" + bd + timezone;
	private static final String user = "root";
	private static final String psw = "root";
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	
	public static Connection getConnection() throws DatabaseException {
		try {
			Class.forName(driver);
			return DriverManager.getConnection(url, user, psw);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		try {
			ConnectionFactory.getConnection();
			System.out.println("Connection sucessfull");
		} catch (DatabaseException e) {
			System.out.println("Connection failed");
		}
	} 
}