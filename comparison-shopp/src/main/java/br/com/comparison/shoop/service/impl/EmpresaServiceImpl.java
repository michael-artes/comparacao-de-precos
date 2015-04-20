package br.com.comparison.shoop.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.comparison.shoop.annotations.Transacao;
import br.com.comparison.shoop.dao.EmpresaDAO;
import br.com.comparison.shoop.entity.Empresa;
import br.com.comparison.shoop.service.EmpresaService;

public class EmpresaServiceImpl implements EmpresaService, Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1141381474568205621L;
	
	
	@Inject
	private EmpresaDAO empresaDAO;

	@Override
	@Transacao
	public void salvar(Empresa empresa) {
		empresaDAO.save(empresa);
	}

	@Override
	@Transacao
	public void update(Empresa empresa) {
		empresaDAO.update(empresa);
	}

	@Override
	@Transacao
	public void deletar(Empresa empresa) {
		empresaDAO.delete(empresa);
	}

	@Override
	public List<Empresa> list() {
		return empresaDAO.list();
	}

	@Override
	public Empresa loadById(int id) {
		return empresaDAO.loadById(id);
	}

	@Override
	public Empresa findEmpresaByIdUser(int idUser) {
		return empresaDAO.findEmpresaByIdUser(idUser);
	}
	
}
