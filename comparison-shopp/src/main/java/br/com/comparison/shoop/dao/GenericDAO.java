package br.com.comparison.shoop.dao;

import java.util.List;

public abstract interface GenericDAO<T> {

	abstract void save(T t);
	abstract void update(T t);
	abstract void delete(T t);
	abstract List<T> list();
	abstract T loadById(int id);
	
}
