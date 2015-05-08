package br.com.comparison.shoop.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.comparison.shoop.annotations.Transacao;
import br.com.comparison.shoop.dao.AnuncioDAO;
import br.com.comparison.shoop.entity.Anuncio;
import br.com.comparison.shoop.service.AnuncioService;

public class AnuncioServiceImpl implements AnuncioService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private AnuncioDAO anuncioDAO;

	@Override
	@Transacao
	public void salvar(Anuncio t) {
		anuncioDAO.save(t);
	}

	@Override
	@Transacao
	public void update(Anuncio t) {
		anuncioDAO.update(t);
	}

	@Override
	@Transacao
	public void deletar(Anuncio t) {
		anuncioDAO.delete(t);
	}

	@Override
	public List<Anuncio> list() {
		return anuncioDAO.list();
	}

	@Override
	public Anuncio loadById(int id) {
		return anuncioDAO.loadById(id);
	}

	@Override
	public List<Anuncio> findAnunciosByEmpresa(int idEmpresa) {
		return anuncioDAO.findAnunciosByEmpresa(idEmpresa);
	}

	@Override
	public List<Anuncio> findAnunciosByPesquisa(String nomePesquisa) {
		return anuncioDAO.findAnunciosByPesquisa(nomePesquisa);
	}

}
