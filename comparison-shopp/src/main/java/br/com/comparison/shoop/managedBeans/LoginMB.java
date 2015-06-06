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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.comparison.shoop.entity.Usuario;
import br.com.comparison.shoop.service.EmpresaService;
import br.com.comparison.shoop.service.UsuarioService;

@Named
@RequestScoped
@Component
public class LoginMB implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4505173608545429000L;
	
	
	private boolean lembrarMe;
	private String login;
	private String senha;
	
	@Inject
	private UsuarioMB usuarioMB;
	
	@Inject
	private UsuarioService usuarioService;
	
	@Inject
	private EmpresaService empresaService;
	
	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;
	

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
		
		Usuario usuario = usuarioService.existeUsuario(login, senha);
		
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, senha));
		
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		
		if (usuario != null) {
			
			usuarioMB.getUserSession().setNome(getLogin());
			usuarioMB.getUserSession().setLogado(true);
			usuarioMB.getUserSession().setDataAcesso(new Date());
			usuarioMB.getUserSession().setIdUser(usuario.getId());
			usuarioMB.getUserSession().setEnumPerfil(usuario.getPerfil());
			usuarioMB.getUserSession().setEmpresa( empresaService.findEmpresaByIdUser( usuarioMB.getUserSession().getIdUser() ) );
			
			context.getExternalContext().redirect("pags/home/home.xhtml");
		}

		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, i18n.getString("loginNaoEfetuado"), i18n.getString("loginError")));
		
	}
	
	public String logout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "login";
	}
	
}
