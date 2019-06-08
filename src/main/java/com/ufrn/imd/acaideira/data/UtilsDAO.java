package com.ufrn.imd.acaideira.data;

public abstract class UtilsDAO <Type>{
	public static String retornValueStringBD(String value) {
		if (value != null && !"".equals(value)) value = "'" + value + "'";
		else value = "'" + "'";
		
		return value;
	}
	
	protected abstract String returnFieldsBD();

	protected abstract String returnFieldValuesBD(Type t);

	protected abstract String returnValuesBD(Type t);
}