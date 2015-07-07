package br.com.comparison.shoop.managedBeans;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.comparison.shoop.enuns.EnumPerfil;

@Named
@RequestScoped
public class AutorizacaoMB implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8686283618283568618L;
	
	@Inject
	private UsuarioMB usuarioMB;
	
	
	public boolean isCadastrouEmpresa() {
		return usuarioMB.getUserSession().getEmpresa() != null;
	}
	
	public String cadastrouEmpresa(){
		
		if (isCadastrouEmpresa()) {
			return "home";
		}
		
		return null;
	}

	public String verificaAutorizacaoConsumidor(){
		return "home";
	}
	
	public String verificaAutorizacaoAdmin(){
		return "home";
	}
	
	public boolean isUserConsumidorAutorizado(){
		
		if (usuarioMB.getUserSession().getEnumPerfil() == EnumPerfil.CONSUMIDOR)
			return true;
		
		return false;
	}
	
	public boolean isUserAdminAutorizado(){
		
		if (usuarioMB.getUserSession().getEnumPerfil() == EnumPerfil.ADMINISTRADOR)
			return true;
		
		return false;
	}
	
	public boolean isUserEmpresaAutorizado(){
		
		if (usuarioMB.getUserSession().getEnumPerfil() == EnumPerfil.EMPRESA)
			return true;
		
		return false;
	}
	
	public String verificaAutorizacaoEmpresa(){
		
		if (usuarioMB.getUserSession().getEnumPerfil() != EnumPerfil.EMPRESA) {
			return "home";
		}
		
		return null;
	}

}
