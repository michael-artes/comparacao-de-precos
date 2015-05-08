package br.com.comparison.shoop.managedBeans;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
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
	private Anuncio anuncioSelected;
	
	private Set<Anuncio> anunciosByPesquisa = new TreeSet<Anuncio>(new Comparator<Anuncio>() {

		@Override
		public int compare(Anuncio o1, Anuncio o2) {
			return Integer.compare(o1.getId(), o2.getId());
		}
	});
	
	private Set<Anuncio> countAnuncios = new TreeSet<Anuncio>(new Comparator<Anuncio>() {

		@Override
		public int compare(Anuncio o1, Anuncio o2) {
			return Integer.compare(o1.getId(), o2.getId());
		}
	});
	
	
	public int getCountAnuncios(){
		return countAnuncios.size();
	}
	public void setCountAnuncios(Set<Anuncio> countAnuncios) {
		this.countAnuncios = countAnuncios;
	}

	public String getNomePesquisa() {
		return nomePesquisa;
	}

	public void setNomePesquisa(String nomePesquisa) {
		this.nomePesquisa = nomePesquisa;
	}

	public Set<Anuncio> getAnunciosByPesquisa() {
		return anunciosByPesquisa;
	}
	
	public Anuncio getAnuncioSelected() {
		return anuncioSelected;
	}

	public void setAnuncioSelected(Anuncio anuncioSelected) {
		this.anuncioSelected = anuncioSelected;
	}
	
	

	
	public void adicionarAnuncio(ActionEvent event){
		
		FacesContext context = FacesContext.getCurrentInstance();
		String idAnuncioString = context.getExternalContext().getRequestParameterMap().get("idAnuncio");
		
		Anuncio a = anuncioService.loadById(Integer.parseInt(idAnuncioString));
		
		countAnuncios.add(a);
		
	}
	
	public void pesquisarAnuncios(ActionEvent event){
		anunciosByPesquisa = new HashSet<Anuncio>(0);
		List<Anuncio> listAnuncios = anuncioService.findAnunciosByPesquisa(this.nomePesquisa);
		anunciosByPesquisa.addAll(listAnuncios);
		
		this.nomePesquisa = ""; //Limpa o campo
	}
	
	
}
