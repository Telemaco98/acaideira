package com.ufrn.imd.acaideira.data;

import com.ufrn.imd.acaideira.data.exception.DatabaseException;

public interface DAO <Type>{
	public void insert (Type type) throws DatabaseException;
	
	public Type retrieve (int id) throws DatabaseException;
	
	public void update (Type type) throws DatabaseException;
	
	public void delete (Type type) throws DatabaseException;
}