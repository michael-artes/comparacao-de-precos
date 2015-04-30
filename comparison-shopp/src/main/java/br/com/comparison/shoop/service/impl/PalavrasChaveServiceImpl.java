package br.com.comparison.shoop.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.comparison.shoop.annotations.Transacao;
import br.com.comparison.shoop.dao.PalavrasChaveDAO;
import br.com.comparison.shoop.entity.PalavrasChave;
import br.com.comparison.shoop.service.PalavrasChaveService;


public class PalavrasChaveServiceImpl implements PalavrasChaveService, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -787975833206755141L;

	@Inject
	private PalavrasChaveDAO palavrasChaveDAO;
	
	@Override
	@Transacao
	public void salvar(PalavrasChave t) {
		palavrasChaveDAO.save(t);
	}

	@Override
	@Transacao
	public void update(PalavrasChave t) {
		palavrasChaveDAO.update(t);
	}

	@Override
	@Transacao
	public void deletar(PalavrasChave t) {
		palavrasChaveDAO.delete(t);
	}

	@Override
	public List<PalavrasChave> list() {
		return palavrasChaveDAO.list();
	}

	@Override
	public PalavrasChave loadById(int id) {
		return palavrasChaveDAO.loadById(id);
	}

}
