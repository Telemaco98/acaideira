package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConFactory {

	public static final int MYSQL = 0;  
	public static final int POSTGRES = 1;  
	   private static final String MySQLDriver = "com.mysql.cj.jdbc.Driver";  
	   private static final String PostgreSQLDriver = "org.postgresql.Driver";  
	  
	   public static Connection conexao(String url, String nome, String senha,  
	         int banco) throws ClassNotFoundException, SQLException {  
	      switch (banco) {        
	      case MYSQL:           
	         Class.forName(MySQLDriver);  
	         break;  
	      
	      case POSTGRES:           
		         Class.forName(PostgreSQLDriver);  
		         break;  
	      }
	      return DriverManager.getConnection(url, nome, senha);  
	   }


	
	
}
