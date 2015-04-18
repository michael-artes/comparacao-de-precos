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

import br.com.comparison.shoop.cript.CriptMD5;
import br.com.comparison.shoop.entity.Usuario;
import br.com.comparison.shoop.enuns.EnumPerfil;
import br.com.comparison.shoop.service.UsuarioService;
import br.com.comparison.shoop.util.SendMail;

@Named
@SessionScoped
public class UsuarioMB implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(UsuarioMB.class);

	//Atributo para realizar a persistencia
	private Usuario usuario;
	private List<EnumPerfil> perfis = new ArrayList<EnumPerfil>(0);
	
	//Atributos para identificar o usuario da sessao
	private Integer idUser;
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
	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
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
			enviarEmailNewUser(usuario);
		} catch (Exception e) {
			LOGGER.error("Não foi possivel enviar e-mail de criação de conta", e);
		}
		
		usuario = new Usuario();
		
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, i18n.getString("sucess"), i18n.getString("userSalvo")));
		
		return null;
	}
	
	
	private void enviarEmailNewUser(Usuario usuario) {
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
}
