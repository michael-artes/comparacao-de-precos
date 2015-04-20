package br.com.comparison.shoop.dao;

import br.com.comparison.shoop.entity.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario>{
	
	public Usuario existeUsuario(String login, String senha);

}
