package br.com.comparison.shoop.managedBeans;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.comparison.shoop.service.AnuncioService;

@Named
@RequestScoped
public class ImagemMB implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2583851912673523505L;
	
	
	
	@Inject
	private AnuncioService anuncioService;


	public StreamedContent getAnuncioImagem(){
		FacesContext context = FacesContext.getCurrentInstance();
		
		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		}
		
		int id = Integer.parseInt(context.getExternalContext().getRequestParameterMap().get("idAnuncio"));
		return new DefaultStreamedContent( new ByteArrayInputStream( anuncioService.loadById(id).getImagem() ) );
		
	}
	
}
