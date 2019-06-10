package com.ufrn.imd.acaideira.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ufrn.imd.acaideira.data.exception.DatabaseException;
import com.ufrn.imd.acaideira.domain.CreditCard;

public class CreditCardDAO extends UtilsDAO<CreditCardDAO, CreditCard> implements DAO<CreditCard> {
	private CreditCardDAO creditCardDAO;
	
	private CreditCardDAO() { }
	
	@Override
	public synchronized CreditCardDAO getInstance() {
		if (creditCardDAO == null)
			creditCardDAO = new CreditCardDAO();
		return creditCardDAO;
	}
	
	@Override
	public void insert(CreditCard creditCard) throws DatabaseException {
		try {
			this.startConnection();

			StringBuffer buffer = new StringBuffer();
			buffer.append("INSERT INTO creditcard (");
			buffer.append(this.returnFieldsBD());
			buffer.append(") VALUES (");
			buffer.append(returnValuesBD(creditCard));
			buffer.append(")");
			
			String sql = buffer.toString();
			command.execute(sql);			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
	}

	@Override
	public CreditCard select(int id) throws DatabaseException {
		try {
			this.startConnection();
			
			String sql = "SELECT * FROM creditcard WHERE id_creditcard = " +
					retornValueStringBD(String.valueOf(id));  // TODO update to get the addresses of each client
			
			ResultSet rs = command.executeQuery(sql);

			CreditCard cc = null;
			if (rs.next()) {
				String number 		= rs.getString("number");
				String validity 	= rs.getString("validity");
				String onwer_name 	= rs.getString("onwer_name");
				
				cc = new CreditCard (id, number, validity, onwer_name);
			} 
			
			return cc;
		} catch (SQLException | ClassNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		} finally {
			this.closeConnection();
		}
	}

	@Override
	public void update(CreditCard creditCard) throws DatabaseException {	
		try {
			this.startConnection();
			
			StringBuffer buffer = new StringBuffer();
			buffer.append("UPDATE credit_card SET ");
			buffer.append(returnFieldValuesBD(creditCard));
			buffer.append(" WHERE id_client=");
			buffer.append(creditCard.getIdCreditCard());
			
			String sql = buffer.toString();
			command.executeUpdate(sql);
		} catch (SQLException | ClassNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		} finally {
			this.closeConnection();
		}
	}

	@Override
	public void delete(CreditCard creditCard) throws DatabaseException {
		try {
			this.startConnection();
			String sql = "DELETE FROM creditcard WHERE id_creditcard="
					+ retornValueStringBD(String.valueOf(creditCard.getIdCreditCard()));
			
			command.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
	}
	
	@Override
	protected String returnFieldsBD() {
		return "number, validity, onwer_name";
	}

	@Override
	protected String returnFieldValuesBD(CreditCard cc) {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("number=");
		buffer.append(retornValueStringBD(cc.getNumber()));
		buffer.append(", validity=");
		buffer.append(retornValueStringBD(cc.getValidity()));
		buffer.append(", onwer_name=");
		buffer.append(retornValueStringBD(cc.getOwnerName()));
		
		return buffer.toString();
	}

	@Override
	protected String returnValuesBD(CreditCard cc) {
		return retornValueStringBD(cc.getNumber()) + ", " + 
				retornValueStringBD(cc.getValidity()) + ", " +
				retornValueStringBD(cc.getValidity());
	}
}
