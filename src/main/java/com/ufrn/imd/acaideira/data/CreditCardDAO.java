package com.ufrn.imd.acaideira.data;

import com.ufrn.imd.acadeira.domain.CreditCard;
import com.ufrn.imd.acaideira.data.exception.DatabaseException;

public class CreditCardDAO extends UtilsDAO<CreditCard> implements DAO<CreditCard> {

	@Override
	public void insert(CreditCard type) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CreditCard select(int id) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(CreditCard type) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(CreditCard type) throws DatabaseException {
		// TODO Auto-generated method stub
		
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
