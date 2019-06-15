package employee.presentation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.ufrn.imd.acaideira.data.ClientDAO;
import com.ufrn.imd.acaideira.data.ConnectionFactory;
import com.ufrn.imd.acaideira.data.exception.DatabaseException;
import com.ufrn.imd.acaideira.domain.Client;

@Named(value = "employeeManagedBean")
@RequestScoped
public class EmployeeMB {
	ClientDAO dao;
	
	//Auxiliary fields for JSF
	private ArrayList<Client> clientList = new ArrayList<>();
	private Client client = new Client();
		
	public ArrayList<Client> getClientList() throws DatabaseException {
		dao = ClientDAO.getInstance();
		clientList = dao.selectAllClients();
		return clientList;
	}

	public void setClientList(ArrayList<Client> ClientList) {
		this.clientList = ClientList;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String addNewClient(){
		try {
			dao = ClientDAO.getInstance();
			dao.insert(client);
			clientList = dao.selectAllClients();
			return "employeelist";
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	}
	
	public String editClient (int id) throws DatabaseException {
		dao = ClientDAO.getInstance();
		Client findedClient = dao.retrieve(id);
		if(findedClient != null) { 
			client = findedClient;
			return "employeeedit";
		} else {
			return null;
		}
	}
	
	public String removeClient (int id) throws DatabaseException {
		dao = ClientDAO.getInstance();
		Client c = dao.retrieve(id);
		dao.delete(c);
		clientList = dao.selectAllClients();
		return "employeelist";
	}
	
	public String confirmEdition () throws DatabaseException {
		dao = ClientDAO.getInstance();		
		dao.update(client);
		clientList = dao.selectAllClients();
		return "employeelist";
	}
	
	public String testConecion () {
		try {
			Connection c = ConnectionFactory.getConnection();
			System.out.println("conectei");
			c.close();
			System.out.println("Fechei conexao conectei");
		} catch (DatabaseException e) {
			System.out.println("Não consegui conectar");
		} catch (SQLException e) {
			System.out.println("Não consegui fechar conexao");
		}
		
		return null;
	}
}