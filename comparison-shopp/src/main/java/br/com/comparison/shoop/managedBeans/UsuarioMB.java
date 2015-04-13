package br.com.comparison.shoop.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.comparison.shoop.entity.Usuario;
import br.com.comparison.shoop.enuns.EnumPerfil;
import br.com.comparison.shoop.service.UsuarioService;

@Named
@SessionScoped
public class UsuarioMB implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Atributo para realizar a persistencia
	private Usuario usuario;
	private List<EnumPerfil> perfis = new ArrayList<EnumPerfil>(0);
	
	//Atributos para identificar o usuario da sessao
	private String nome;
	private String login;
	private boolean logado;
	private Date dataAcesso;
	
	@PostConstruct
	public void init(){
		
		if (usuario == null) {
			usuario = new Usuario();
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
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public Date getDataAcesso() {
		return dataAcesso;
	}
	public void setDataAcesso(Date dataAcesso) {
		this.dataAcesso = dataAcesso;
	}
	public boolean isLogado() {
		return logado;
	}
	public void setLogado(boolean logado) {
		this.logado = logado;
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
	
	
	public void salvarUser(ActionEvent event){
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle i18n = context.getApplication().getResourceBundle(context, "i18n");
		
		usuario.setDataCadastro(new Date());
		usuario.setAtivo(true);
		usuarioService.salvar(usuario);
		
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, i18n.getString("sucess"), i18n.getString("userSalvo")));
		
		usuario = new Usuario();
		
		
	}
}
