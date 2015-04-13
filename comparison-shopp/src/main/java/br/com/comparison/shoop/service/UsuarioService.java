package br.com.comparison.shoop.service;

import br.com.comparison.shoop.entity.Usuario;

public interface UsuarioService {
	
	public Usuario findUser(Usuario usuario);

	public void salvar(Usuario usuario);
	
	public boolean isExisteUsuario(String login, String senha);
	
}
