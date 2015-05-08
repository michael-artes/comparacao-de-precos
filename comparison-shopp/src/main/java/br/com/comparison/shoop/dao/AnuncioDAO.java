package br.com.comparison.shoop.dao;

import java.util.List;

import br.com.comparison.shoop.entity.Anuncio;

public interface AnuncioDAO extends GenericDAO<Anuncio> {
	
	public List<Anuncio> findAnunciosByEmpresa(int idEmpresa);

	public List<Anuncio> findAnunciosByPesquisa(String nomePesquisa);
}
