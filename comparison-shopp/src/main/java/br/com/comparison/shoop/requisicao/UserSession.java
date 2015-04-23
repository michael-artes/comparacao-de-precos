package br.com.comparison.shoop.requisicao;

import java.io.Serializable;
import java.util.Date;

import br.com.comparison.shoop.entity.Empresa;
import br.com.comparison.shoop.enuns.EnumPerfil;


/**
 * Classe que recebera os parametros do usuario da sessao
 * */
public class UserSession implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Integer idUser;
	private String nome;
	private String login;
	private boolean logado;
	private Date dataAcesso;
	private EnumPerfil enumPerfil;
	private Empresa empresa;
	
	
	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
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
	public boolean isLogado() {
		return logado;
	}
	public void setLogado(boolean logado) {
		this.logado = logado;
	}
	public Date getDataAcesso() {
		return dataAcesso;
	}
	public void setDataAcesso(Date dataAcesso) {
		this.dataAcesso = dataAcesso;
	}
	public EnumPerfil getEnumPerfil() {
		return enumPerfil;
	}
	public void setEnumPerfil(EnumPerfil enumPerfil) {
		this.enumPerfil = enumPerfil;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
}
