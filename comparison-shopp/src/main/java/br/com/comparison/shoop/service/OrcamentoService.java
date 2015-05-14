package br.com.comparison.shoop.service;

import java.io.IOException;

import org.primefaces.model.StreamedContent;

import com.lowagie.text.DocumentException;

import br.com.comparison.shoop.entity.Orcamento;

public interface OrcamentoService extends GenericService<Orcamento> {
	
	public StreamedContent gerarPDFOrcamentoStrem(int idOrcamento) throws IOException, DocumentException;

}
