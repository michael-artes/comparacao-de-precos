package br.com.comparison.shoop.service;

import java.util.List;

import br.com.comparison.shoop.entity.Anuncio;

public interface AnuncioService extends GenericService<Anuncio> {
	
	public List<Anuncio> findAnunciosByEmpresa(int idEmpresa);
	
	public List<Anuncio> findAnunciosByPesquisa(String nomePesquisa);

}
