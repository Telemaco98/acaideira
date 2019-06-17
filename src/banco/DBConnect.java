package banco;

import java.sql.*;

public class DBConnect {
	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	public DBConnect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurante?useTimezone=true&serverTimezone=UTC","root","root");
			st = con.createStatement();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void getData() {
		try {
			String query = "SELECT * FROM restaurante";
			rs = st.executeQuery(query);
			System.out.println("database");
			while(rs.next()) {
				String name = rs.getString("nome");
				System.out.println("Nome" + name);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
