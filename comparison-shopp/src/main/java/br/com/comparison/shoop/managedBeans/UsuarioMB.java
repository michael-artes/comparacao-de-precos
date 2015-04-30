package br.com.comparison.shoop.managedBeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;

import br.com.comparison.shoop.entity.Usuario;
import br.com.comparison.shoop.enuns.EnumPerfil;
import br.com.comparison.shoop.requisicao.UserSession;
import br.com.comparison.shoop.service.UsuarioService;

@Named
@SessionScoped
public class UsuarioMB implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7187653576021409841L;

	private static final Logger LOGGER = Logger.getLogger(UsuarioMB.class);

	//Atributo para realizar a persistencia
	private Usuario usuario;
	private List<EnumPerfil> perfis = new ArrayList<EnumPerfil>(0);
	
	//Atributos para identificar o usuario da sessao
	private UserSession userSession;
	
	
	@PostConstruct
	public void init(){
		
		if (usuario == null) {
			usuario = new Usuario();
		}
		
		if (userSession == null) {
			userSession = new UserSession();
		}
	}
	
	
	@Inject
	private UsuarioService usuarioService;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public UserSession getUserSession() {
		return userSession;
	}
	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}
	
	
	
	
	public List<EnumPerfil> getPerfils() {
		
		if (perfis.isEmpty()) {
			for (EnumPerfil e : EnumPerfil.values()) {
				if(e != EnumPerfil.ADMINISTRADOR){
					perfis.add(e);
				}
			}
		} 
		
		return perfis;
	}
	
	
	public String salvarUser() throws IOException{
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle i18n = context.getApplication().getResourceBundle(context, "i18n");
		
		usuario.setDataCadastro(new Date());
		usuario.setAtivo(true);
		try {
			usuarioService.salvar(usuario);
		} catch (Exception e) {
			usuario.setId(null);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, i18n.getString("error"), i18n.getString("userNotSave")));
			return null;
		}

		try {
			usuarioService.enviarEmailNewUser(usuario);
		} catch (Exception e) {
			LOGGER.error("Não foi possivel enviar e-mail de criação de conta", e);
		}
		
		usuario = new Usuario();
		
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, i18n.getString("sucess"), i18n.getString("userSalvo")));
		
		return null;
	}
	
	
	public boolean isVisualizaDetalhesCliente(){
		
		if (userSession.getEnumPerfil() == EnumPerfil.EMPRESA) {
			return true;
		}
		
		return false;
	}
	
	public boolean isCadastrouEmpresa(){
		return usuarioService.isCadastrouEmpresa(userSession.getIdUser(), userSession.getEnumPerfil());
	}
	
}
