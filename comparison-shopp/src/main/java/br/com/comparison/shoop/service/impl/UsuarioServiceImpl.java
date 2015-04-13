package br.com.comparison.shoop.service.impl;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.comparison.shoop.annotations.Transacao;
import br.com.comparison.shoop.dao.UsuarioDAO;
import br.com.comparison.shoop.entity.Usuario;
import br.com.comparison.shoop.service.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioDAO usuarioDAO;

	@Override
	public Usuario findUser(Usuario usuario) {
		
		try {
			usuarioDAO.save(usuario);
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}

		return usuario;
	}

	@Override
	@Transacao
	public void salvar(Usuario usuario) {
		usuarioDAO.save(usuario);
	}
	
	

	@Override
	public boolean isExisteUsuario(String login, String senha) {
		return usuarioDAO.isExisteUsuario(login, senha);
	}

}
