package br.com.comparison.shoop.managedBeans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.comparison.shoop.entity.Anuncio;
import br.com.comparison.shoop.service.AnuncioService;

@Named
@SessionScoped
public class OrcamentoMB implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2029103509647593931L;
	
	
	@Inject
	private AnuncioService anuncioService;
	
	private String nomePesquisa; 
	private Set<Anuncio> anunciosByPesquisa = new HashSet<Anuncio>(0);
	
	
	public String getNomePesquisa() {
		return nomePesquisa;
	}

	public void setNomePesquisa(String nomePesquisa) {
		this.nomePesquisa = nomePesquisa;
	}

	public Set<Anuncio> getAnunciosByPesquisa() {
		return anunciosByPesquisa;
	}
	
	public int getCountAnuncios(){
		return anunciosByPesquisa.size();
	}
	
	

	/*********************************************************
	 * GATO - NO MOMENTO SEM PESQUISA
	 * */
	public List<Anuncio> getListAnuncios(){
		return anuncioService.list(); //Listar todos
	}
	
	/**
	 * FIM DO GATO
	 * ********************************************************/
	
	public void adicionarAnuncio(){
		
		
		FacesContext context = FacesContext.getCurrentInstance();
		String idAnuncioString = context.getExternalContext().getRequestParameterMap().get("idAnuncio");
		
		Anuncio a = anuncioService.loadById(Integer.parseInt(idAnuncioString));
		
		if (!anunciosByPesquisa.contains(a)) {
			anunciosByPesquisa.add(a);
		}
		
	}
	
	
}
