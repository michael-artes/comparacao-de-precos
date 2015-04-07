package br.com.comparison.shoop.dao.impl;

import javax.persistence.EntityManager;

import br.com.comparison.shoop.dao.UsuarioDAO;
import br.com.comparison.shoop.entity.Usuario;

public class UsuarioDAOImpl extends GenericDAOImpl<Usuario> implements UsuarioDAO{

	public UsuarioDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

}
