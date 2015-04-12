package br.com.comparison.shoop.managedBeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.ResourceBundle;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class LoginMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean lembrarMe;
	private String login;
	private String senha;
	
	@Inject
	private UsuarioMB usuarioMB;

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
	
	
	
	
	public void logar() throws IOException{
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle i18n = context.getApplication().getResourceBundle(context, "i18n");
		
		if ("admin".equals(login) && "123456".equals(senha)) {
			usuarioMB.setNome(getLogin());
			usuarioMB.setLogado(true);
			usuarioMB.setDataAcesso(new Date());
			context.getExternalContext().redirect("pags/home/home.xhtml");
		}

		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, i18n.getString("loginNaoEfetuado"), i18n.getString("loginError")));
		
	}
	
	public String logout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "login";
	}
	
}
