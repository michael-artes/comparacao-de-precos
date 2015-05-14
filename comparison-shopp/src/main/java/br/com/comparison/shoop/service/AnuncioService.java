package br.com.comparison.shoop.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.primefaces.event.FileUploadEvent;

import br.com.comparison.shoop.entity.Anuncio;
import br.com.comparison.shoop.entity.Empresa;

public interface AnuncioService extends GenericService<Anuncio> {
	
	public List<Anuncio> findAnunciosByEmpresa(int idEmpresa);
	
	public List<Anuncio> findAnunciosByPesquisa(String nomePesquisa);
	
	public void handleUploadAnuncio(FileUploadEvent event, Empresa empresa, List<Anuncio> anuncios) throws IOException, ParseException;

}
