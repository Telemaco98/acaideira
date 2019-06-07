package com.ufrn.imd.acaideira.data.exception;

/**
 * Class to the database exceptions
 * @author Shirley Ohara
 */
public class DatabaseException extends Exception {
	private static final long serialVersionUID = 1L;

	public DatabaseException (String msg) {
		super (msg);
	}
}