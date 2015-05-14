package br.com.comparison.shoop.managedBeans;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.io.IOUtils;
import org.jboss.logging.Logger;
import org.primefaces.event.FileUploadEvent;
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
	private static final long serialVersionUID = 8900844371010166277L;
	
	private static Logger LOGGER = Logger.getLogger(AnuncioMB.class);
	
	
	private Anuncio anuncio;
	private Anuncio anuncioSelected;
	private UploadedFile file;
	private List<Anuncio> anuncios;
	
	
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
		
		if (anuncioSelected == null) {
			anuncioSelected = new Anuncio();
		}
		
	}
	
	
	public String salvarAnuncio() throws IOException{
		
		anuncio.setImagem(IOUtils.toByteArray(file.getInputstream()));
		anuncio.setEmpresa(usuarioMB.getUserSession().getEmpresa());
		anuncio.setDataCadastro( new Date() );
		
		anuncioService.salvar(anuncio);

		return "detalhes-anuncio";
	}
	
	public String atualizarAnuncio() throws IOException{
		
		if (anuncioSelected == null) {
			throw new IllegalArgumentException("Nao localizou este anuncio");
		}
		
		anuncioSelected.setImagem(IOUtils.toByteArray(file.getInputstream()));
		anuncioService.update(anuncioSelected);
		
		return "detalhes-anuncio?faces-redirect=true";
	}
	
	public String deletarAnuncio(int idAnuncio){
		
		anuncioService.deletar( anuncioService.loadById(idAnuncio) );
		
		return "detalhes-anuncio";
	}
	
	
	public List<Anuncio> getListAnuncios(){
		
		if (anuncios == null) {
			anuncios = anuncioService.findAnunciosByEmpresa(usuarioMB.getUserSession().getEmpresa().getId());
		}
		
		return anuncios;
	}
	
	
	
	public StreamedContent getDownloadExemploImport(){
		InputStream stream = getClass().getResourceAsStream("/exemplos/anuncio/anuncio-example.xls");
		StreamedContent file = new DefaultStreamedContent(stream, "application/download", "anuncio-example.xls");
		return file;
	}
	
	
	public void handleUploadAnuncio(FileUploadEvent event) throws IOException{
		List<Anuncio> anuncios = new ArrayList<Anuncio>(0);
		
		try {
			anuncioService.handleUploadAnuncio(event, usuarioMB.getUserSession().getEmpresa(), anuncios);
			
			for (Anuncio anuncio : anuncios) {
				anuncioService.salvar(anuncio);
			}
		} catch (Exception e) {
			LOGGER.error("Nao foi possivel realizar o upload do arquivo");
		}
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso: ", "Arquivo importado com sucesso!"));
	}
}
