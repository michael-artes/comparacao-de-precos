package br.com.comparison.shoop.dao.impl;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.comparison.shoop.dao.UsuarioDAO;
import br.com.comparison.shoop.entity.Usuario;

public class UsuarioDAOImpl extends GenericDAOImpl<Usuario> implements UsuarioDAO, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager entityManager;

	@Override
	public boolean isExisteUsuario(String login, String senha) {
		
		Session session = (Session) entityManager.getDelegate();
		Criteria crit = session.createCriteria(Usuario.class, "user");
		crit.add(Restrictions.eq("user.login", login));
		crit.add(Restrictions.eq("user.senha", senha));
		
		Usuario user = (Usuario) crit.uniqueResult();
		
		if (user != null) {
			return true;
		}

		return false;
	}

}
