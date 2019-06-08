package com.ufrn.imd.acaideira.data;

import com.ufrn.imd.acadeira.domain.Address;
import com.ufrn.imd.acaideira.data.exception.DatabaseException;

public class AddressDAO extends UtilsDAO<Address> implements DAO<Address> {

	@Override
	public void insert(Address type) throws DatabaseException {
		// TODO Auto-generated method stub
	}

	@Override
	public Address select(int id) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Address type) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Address type) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String returnFieldsBD() {
		return "cep, street, neighborhood, city, number, complements";
	}

	@Override
	protected String returnFieldValuesBD(Address a) {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("cep=");
		buffer.append(retornValueStringBD(a.getCep()));
		buffer.append(", street=");
		buffer.append(retornValueStringBD(a.getStreet()));
		buffer.append(", neighborhood=");
		buffer.append(retornValueStringBD(a.getNeighborhood()));
		buffer.append(", city=");
		buffer.append(retornValueStringBD(a.getCity()));
		buffer.append(", number=");
		buffer.append(retornValueStringBD(String.valueOf(a.getNumber())));
		buffer.append(", complement=");
		buffer.append(retornValueStringBD(a.getComplement()));
		
		return buffer.toString();
	}

	@Override
	protected String returnValuesBD(Address a) {
		return retornValueStringBD(a.getCep()) + ", " + 
				retornValueStringBD(a.getStreet()) + ", " +
				retornValueStringBD(a.getNeighborhood()) + ", " +
				retornValueStringBD(a.getCity()) + ", " +
				retornValueStringBD(String.valueOf(a.getNumber())) + ", " +
				retornValueStringBD(a.getComplement());
	}
}