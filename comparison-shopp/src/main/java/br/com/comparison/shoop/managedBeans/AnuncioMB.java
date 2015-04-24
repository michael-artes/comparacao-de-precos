package br.com.comparison.shoop.managedBeans;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.com.comparison.shoop.entity.Anuncio;
import br.com.comparison.shoop.service.AnuncioService;

@Named
@ViewScoped
public class AnuncioMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Anuncio anuncio;
	private Anuncio anuncioSelected;
	private UploadedFile file;
	private List<Anuncio> anuncios;
	private Map<Integer, Anuncio> mapAnuncios;
	
	
	@Inject
	private UsuarioMB usuarioMB;
	
	@Inject
	private AnuncioService anuncioService;

	public Anuncio getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}
	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
	
	public List<Anuncio> getAnuncios() {
		return anuncios;
	}

	public void setAnuncios(List<Anuncio> anuncios) {
		this.anuncios = anuncios;
	}

	public Anuncio getAnuncioSelected() {
		return anuncioSelected;
	}

	public void setAnuncioSelected(Anuncio anuncioSelected) {
		this.anuncioSelected = anuncioSelected;
	}
	
	
	

	@PostConstruct
	public void init(){

		if (anuncio == null) {
			anuncio = new Anuncio();
		}
		
	}
	
	
	public String salvarAnuncio() throws IOException{
		
		anuncio.setImagem(IOUtils.toByteArray(file.getInputstream()));
		anuncio.setEmpresa(usuarioMB.getUserSession().getEmpresa());
		anuncio.setDataCadastro( new Date() );
		
		anuncioService.salvar(anuncio);

		return "detalhes-anuncio";
	}
	
	
	public List<Anuncio> getListAnuncios(){
		
		if (anuncios == null) {
			anuncios = anuncioService.list();
			
			mapAnuncios = new HashMap<Integer, Anuncio>(0);
			
			for (Anuncio a : anuncios) {
				mapAnuncios.put(a.getId(), a);
			}
			
		}
		
		return anuncios;
	}
	
	
	public StreamedContent getImagen(){
		return new DefaultStreamedContent( new ByteArrayInputStream( mapAnuncios.get(8).getImagem() ) );
	}
	

}
