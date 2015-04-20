package br.com.comparison.shoop.service;

import br.com.comparison.shoop.entity.Empresa;

public interface EmpresaService extends GenericService<Empresa> {
	
	public Empresa findEmpresaByIdUser(int idUser);
	
}
