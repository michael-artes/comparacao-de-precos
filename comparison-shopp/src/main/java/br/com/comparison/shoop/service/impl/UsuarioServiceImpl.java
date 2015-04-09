package br.com.comparison.shoop.service.impl;

import javax.inject.Inject;

import br.com.comparison.shoop.annotations.Transacao;
import br.com.comparison.shoop.dao.UsuarioDAO;
import br.com.comparison.shoop.entity.Usuario;
import br.com.comparison.shoop.service.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService{
	
	@Inject
	private UsuarioDAO usuarioDAO;

	@Override
	@Transacao
	public Usuario findUser(Usuario usuario) {
		
		try {
			usuarioDAO.save(usuario);
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}

		return usuario;
	}

}
