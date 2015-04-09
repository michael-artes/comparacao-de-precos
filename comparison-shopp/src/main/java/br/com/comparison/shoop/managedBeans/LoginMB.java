package br.com.comparison.shoop.managedBeans;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.comparison.shoop.entity.Usuario;
import br.com.comparison.shoop.service.UsuarioService;

@ApplicationScoped
@Named
public class LoginMB {

	private boolean lembrarMe;
	private String login;
	private String senha;

	@Inject
	private UsuarioService service;
	
	public boolean isLembrarMe() {
		return lembrarMe;
	}
	public void setLembrarMe(boolean lembrarMe) {
		this.lembrarMe = lembrarMe;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	

	public String entrar(){
		return "home";
	}
	
	public String logout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "login?faces-redirect=true";
	}
	
}
