package br.com.comparison.shoop.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.lowagie.text.DocumentException;

import br.com.comparison.shoop.annotations.Transacao;
import br.com.comparison.shoop.dao.EmpresaDAO;
import br.com.comparison.shoop.dao.OrcamentoDAO;
import br.com.comparison.shoop.entity.Orcamento;
import br.com.comparison.shoop.pdf.PDFBuild;
import br.com.comparison.shoop.pdf.PDFBuildCorpo;
import br.com.comparison.shoop.service.OrcamentoService;

public class OrcamentoServiceImpl implements OrcamentoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 349923018655660938L;
	
	@Inject private OrcamentoDAO orcamentoDAO;
	@Inject private EmpresaDAO empresaDAO;

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

	@Override
	public StreamedContent gerarPDFOrcamentoStrem(int idOrcamento) throws IOException, DocumentException {
		
		PDFBuild buildCorpo = new PDFBuildCorpo();
		
		buildCorpo.openDocument();
		
		buildCorpo.buildCorpo( orcamentoDAO.loadById(idOrcamento), empresaDAO.findEmpresaByItemOrcamento(idOrcamento) );
		
		buildCorpo.closeDocument();
		
		return new DefaultStreamedContent( new ByteArrayInputStream( buildCorpo.stream.toByteArray() ), "application/pdf", "Orçamento-" + idOrcamento + ".pdf" );
	}
	
	

}
