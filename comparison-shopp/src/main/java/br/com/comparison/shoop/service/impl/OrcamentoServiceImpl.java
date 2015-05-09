package br.com.comparison.shoop.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.comparison.shoop.annotations.Transacao;
import br.com.comparison.shoop.dao.OrcamentoDAO;
import br.com.comparison.shoop.entity.Orcamento;
import br.com.comparison.shoop.service.OrcamentoService;

public class OrcamentoServiceImpl implements OrcamentoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 349923018655660938L;
	
	@Inject
	private OrcamentoDAO orcamentoDAO;

	@Override
	@Transacao
	public void salvar(Orcamento t) {
		orcamentoDAO.save(t);
	}

	@Override
	@Transacao
	public void update(Orcamento t) {
		orcamentoDAO.update(t);
	}

	@Override
	@Transacao
	public void deletar(Orcamento t) {
		orcamentoDAO.delete(t);
	}

	@Override
	public List<Orcamento> list() {
		return orcamentoDAO.list();
	}

	@Override
	public Orcamento loadById(int id) {
		return orcamentoDAO.loadById(id);
	}
	
	

}
