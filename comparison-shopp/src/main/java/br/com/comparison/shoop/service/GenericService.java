package br.com.comparison.shoop.service;

import java.util.List;

public abstract interface GenericService<T> {
	
	abstract void salvar(T t);
	
	abstract void update(T t);
	
	abstract void deletar(T t);
	
	abstract List<T> list();
	
	abstract T loadById(int id);

}
