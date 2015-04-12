package br.com.comparison.shoop.managedBeans;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.comparison.shoop.entity.Usuario;

//@Login
@Named
@SessionScoped
public class UsuarioMB implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Atributo para realizar a persistencia
	private Usuario usuario;
	
	//Atributos para identificar o usuario da sessao
	private String nome;
	private String login;
	private boolean logado;
	private Date dataAcesso;
	
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
	
}
