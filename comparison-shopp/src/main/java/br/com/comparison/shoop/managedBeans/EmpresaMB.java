package br.com.comparison.shoop.managedBeans;

import java.io.Serializable;
import java.util.Date;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.comparison.shoop.entity.Empresa;
import br.com.comparison.shoop.service.EmpresaService;
import br.com.comparison.shoop.service.UsuarioService;


@Named
@ViewScoped
public class EmpresaMB implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3937376125793296553L;
	
	
	@Inject
	private EmpresaService empresaService;
	
	@Inject
	private UsuarioService usuarioService;
	
	@Inject
	private UsuarioMB usuarioMB;
	
	
	
	
	private Empresa empresa;
	private int idUser;
	

	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	
	
	@PostConstruct
	public void init(){
		
		if (empresa == null) 
			empresa = new Empresa();
			
		}
		
	
	public String salvarEmpresa(){
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle i18n = context.getApplication().getResourceBundle(context, "i18n");

		empresa.setAtivo(true);
		empresa.setDataCadastro(new Date());
		empresa.setUsuario( usuarioService.loadById(usuarioMB.getIdUser()) );
		
		try {
			empresaService.salvar(empresa);
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, i18n.getString("error"), i18n.getString("empresaNotSalve")));
			return null;
		}
		
		return "home";
	}
	
	
	
	public void preencherEmpresaByIdUser(){
		
		if (idUser > 0)
			empresa = empresaService.findEmpresaByIdUser(idUser);
		
	}

}
