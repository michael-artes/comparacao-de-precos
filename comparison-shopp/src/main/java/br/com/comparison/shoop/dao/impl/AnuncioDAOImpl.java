package br.com.comparison.shoop.dao.impl;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.comparison.shoop.dao.AnuncioDAO;
import br.com.comparison.shoop.entity.Anuncio;

public class AnuncioDAOImpl extends GenericDAOImpl<Anuncio> implements AnuncioDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager entityManager;
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Anuncio> findAnunciosByEmpresa(int idEmpresa) {
		
		Session session = (Session) entityManager.getDelegate();
		Criteria crit = session.createCriteria(Anuncio.class, "a");
		crit.add(Restrictions.eq("a.empresa.id", idEmpresa));
		
		return crit.list();
	}
	
}
