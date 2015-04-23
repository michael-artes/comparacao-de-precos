package br.com.comparison.shoop.managedBeans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.comparison.shoop.enuns.EnumPerfil;

@Named
@SessionScoped
public class AutorizacaoMB implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
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
	
	public String verificaAutorizacaoEmpresa(){
		
		if (usuarioMB.getUserSession().getEnumPerfil() != EnumPerfil.EMPRESA) {
			return "home";
		}
		
		/*if (usuarioMB.getUserSession().getEmpresa() != null) {
			return "home";
		}*/
		
		return null;
	}
	
	public boolean isUserConsumidorAutorizado(){
		return false;
	}
	
	public boolean isUserAdminAutorizado(){
		return false;
	}
	
	public boolean isUserEmpresaAutorizado(){
		
		if (usuarioMB.getUserSession().getEnumPerfil() == EnumPerfil.EMPRESA)
			return true;
		
		return false;
	}

}
