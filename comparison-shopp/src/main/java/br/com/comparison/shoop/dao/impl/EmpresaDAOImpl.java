package br.com.comparison.shoop.dao.impl;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.comparison.shoop.dao.EmpresaDAO;
import br.com.comparison.shoop.entity.Empresa;
import br.com.comparison.shoop.entity.ItemOrcamento;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<Empresa> findEmpresaByItemOrcamento(int idOrcamento) {
		
		Session session = (Session) entityManager.getDelegate();
		Criteria crit = session.createCriteria(ItemOrcamento.class, "item");
		crit.add(Restrictions.eq("item.orcamento.id", idOrcamento));
		
		crit.setProjection(Projections.distinct(Projections.property("item.empresa")));
		
		return crit.list();
	}
 
}
