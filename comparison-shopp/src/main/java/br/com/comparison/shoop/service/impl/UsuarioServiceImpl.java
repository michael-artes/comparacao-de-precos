package br.com.comparison.shoop.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.comparison.shoop.annotations.Transacao;
import br.com.comparison.shoop.cript.CriptMD5;
import br.com.comparison.shoop.dao.EmpresaDAO;
import br.com.comparison.shoop.dao.UsuarioDAO;
import br.com.comparison.shoop.entity.Empresa;
import br.com.comparison.shoop.entity.Usuario;
import br.com.comparison.shoop.enuns.EnumPerfil;
import br.com.comparison.shoop.service.UsuarioService;
import br.com.comparison.shoop.util.SendMail;

public class UsuarioServiceImpl implements UsuarioService, Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7918918815306884728L;
	
	
	@Inject
	private UsuarioDAO usuarioDAO;
	
	@Inject
	private EmpresaDAO empresaDAO;


	@Override
	@Transacao
	public void salvar(Usuario usuario) {
		usuarioDAO.save(usuario);
	}

	@Override
	@Transacao
	public void update(Usuario usuario) {
		usuarioDAO.update(usuario);
	}

	@Override
	@Transacao
	public void deletar(Usuario usuario) {
		usuarioDAO.delete(usuario);
	}

	@Override
	public List<Usuario> list() {
		return usuarioDAO.list();
	}

	@Override
	public Usuario existeUsuario(String login, String senha) {
		return usuarioDAO.existeUsuario(login, senha);
	}

	
	public void enviarEmailNewUser(Usuario usuario) {
		
		StringBuilder builder = new StringBuilder();
		builder.append("Sua conta foi criada com sucesso Sr(a) " + usuario.getNome());
		builder.append(" Sua senha é: " + CriptMD5.getInstance().decript(usuario.getSenha()));
		builder.append(" Por favor faça o login e utilize nossos serviços.");
		builder.append(" Obrigado!!!");
		
		SendMail mail = new SendMail(usuario.getEmail(), "Conta Criada com sucesso", builder.toString());
		
		//Cria uma thread para enviar e-mail
		Thread t = new Thread(mail);
		t.start();
	}

	@Override
	public Usuario loadById(int id) {
		return usuarioDAO.loadById(id);
	}

	@Override
	public boolean podeCadastrarEmpresa(int idUser, EnumPerfil perfil) {
		
		if (EnumPerfil.EMPRESA == perfil) {
			
			Empresa empresa = empresaDAO.findEmpresaByIdUser(idUser);
			
			if (empresa == null) {
				return true;
			}
			
		}
		
		return false;
	}
	
	
}
