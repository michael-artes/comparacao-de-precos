package br.com.comparison.shoop.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.com.comparison.shoop.dao.GenericDAO;

public class GenericDAOImpl<T> implements GenericDAO<T>, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private EntityManager entityManager;
	private Class<T> klass;
	
	//Alterar pois sera usado a injecao de dependencia
	@SuppressWarnings("unchecked")
	public GenericDAOImpl() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.klass = (Class<T>) type.getActualTypeArguments()[0];
	}
	
	@Override
	public void save(T t) {
		entityManager.persist(t);
	}
	
	@Override
	public void update(T t) {
		entityManager.merge(t);
	}
	
	@Override
	public void delete(T t) {
		entityManager.remove(t);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> list() {
		Session session = (Session) entityManager.getDelegate();
		Criteria crit = session.createCriteria(klass);
		return crit.list();
	}

	@Override
	public T loadById(int id) {
		return entityManager.getReference(klass, id);
	}
	
}
