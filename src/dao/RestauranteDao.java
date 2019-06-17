package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexao.ConFactory;
import entidade.Restaurante;

public class RestauranteDao {

	
	private String URL;
	private String NOME;
	private String SENHA;
	private int BANCO;
	
	private Connection con;  
	private Statement comando;
	
	public RestauranteDao(String URL, String user, String password) throws SQLException {
		this.URL = URL + "?useTimezone=true&serverTimezone=UTC";
		this.NOME = user;
		this.SENHA = password;
		this.BANCO = 0;
	}


	public void update(Restaurante r) {
		
        try {
        	conectar();
        	StringBuffer buffer = new StringBuffer();
            buffer.append("UPDATE restaurante SET ");
            buffer.append(returnFieldValuesBD(r));
            buffer.append(" WHERE id_restaunte=");
            buffer.append(r.getId_restaurante());
            String sql = buffer.toString();
 
			comando.executeUpdate(sql);
			fechar();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		
	}


	public void insert(Restaurante r) {
		try {
			
				conectar();
			
		        StringBuffer buffer = new StringBuffer();
		        buffer.append("INSERT INTO restaurante (");
		        buffer.append(this.retornarCamposBD());
		        buffer.append(") VALUES (");
		        buffer.append(retornarValoresBD(r));
		        buffer.append(")");
		        String sql = buffer.toString();
		        comando.executeUpdate(sql);
		        
		        fechar();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public Restaurante search(int id) {
		try{
			conectar();
			String sql = "SELECT * FROM EMPLOYEE WHERE id_restaurante = " + this.retornarValorStringBD(String.valueOf(id)); 
			ResultSet rs = comando.executeQuery(sql);
			Restaurante r  = new Restaurante();
			if (rs.next()){
				r.setNome(rs.getString("nome"));
				r.setTipo(rs.getString("tipo"));
				r.setEndereco(rs.getString("endereco"));
			}
			
			fechar();
			return r;
		} catch(SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
		
		
	}


	public void remove(Restaurante r) {

        try {
        	conectar();
    		String sql ="DELETE FROM restaurante WHERE id_restaurante=" + this.retornarValorStringBD(String.valueOf(r.getId_restaurante()));
			comando.executeUpdate(sql);
			fechar();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public List<Restaurante> retrieveRestaurantes() {
		try {
			conectar();
			String sql = "SELECT * FROM restaurante";
			ResultSet rs = comando.executeQuery(sql);
			List<Restaurante> rest = new ArrayList<Restaurante>();
			while (rs.next()){
				Restaurante r = new Restaurante();
				r.setNome(rs.getString("nome"));
				r.setTipo(rs.getString("tipo"));
				r.setEndereco(rs.getString("endereco"));
				rest.add(r);
			}
			fechar();
			return rest;
		} catch(SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void conectar() throws ClassNotFoundException, SQLException  {
        con = ConFactory.conexao(URL, NOME, SENHA, BANCO);  
		comando = con.createStatement();  
        System.out.println("Conectado!");     
	}	  
	
	private void fechar() {  
		try {  
			comando.close();  
			con.close();  
			System.out.println("Conexão Fechada");  
		} catch (SQLException e) {  
		}  
	}

	protected String retornarCamposBD() {
    	return "fname, minit, lname, ssn, bdate, address, sex, salary, super_ssn, dno";
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
    	return
	        retornarValorStringBD(r.getNome())
	        + ", "
	        + retornarValorStringBD(r.getTipo())
	        + ", "
	        + retornarValorStringBD(r.getEndereco());
    }
    
    private String retornarValorStringBD(String valor) {
        if (valor != null && !"".equals(valor)) {
            valor = "'" + valor + "'";
        } else {
            valor = "'"+"'";
        }
        return valor;
    }
}


