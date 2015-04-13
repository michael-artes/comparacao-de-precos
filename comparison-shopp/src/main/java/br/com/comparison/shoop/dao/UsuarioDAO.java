package br.com.comparison.shoop.dao;

import br.com.comparison.shoop.entity.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario>{
	
	public boolean isExisteUsuario(String login, String senha);

}
