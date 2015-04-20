package br.com.comparison.shoop.dao.impl;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.comparison.shoop.dao.EmpresaDAO;
import br.com.comparison.shoop.entity.Empresa;

public class EmpresaDAOImpl extends GenericDAOImpl<Empresa> implements EmpresaDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager entityManager;

	@Override
	public Empresa findEmpresaByIdUser(int idUser) {
		
		Session session = (Session) entityManager.getDelegate();
		Criteria crit = session.createCriteria(Empresa.class, "ep");
		crit.add(Restrictions.eq("ep.usuario.id", idUser));
		
		return (Empresa) crit.uniqueResult();
	}
 
}
