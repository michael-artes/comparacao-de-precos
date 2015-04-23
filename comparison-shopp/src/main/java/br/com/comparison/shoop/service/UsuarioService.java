package br.com.comparison.shoop.service;

import br.com.comparison.shoop.entity.Usuario;
import br.com.comparison.shoop.enuns.EnumPerfil;

public interface UsuarioService extends GenericService<Usuario>{
	
	public Usuario existeUsuario(String login, String senha);
	
	public void enviarEmailNewUser(Usuario usuario);
	
	public boolean isCadastrouEmpresa(int idUser, EnumPerfil perfil);
	
}
