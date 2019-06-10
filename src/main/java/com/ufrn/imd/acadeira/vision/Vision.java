package com.ufrn.imd.acadeira.vision;

public interface Vision<T> {
	public void add(T entity);
	public void remove(T entity);
	public void alter(T entity);
	public void visualize(T entity);
}
