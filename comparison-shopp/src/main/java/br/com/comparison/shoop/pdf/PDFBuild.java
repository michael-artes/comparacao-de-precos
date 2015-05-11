package br.com.comparison.shoop.pdf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import br.com.comparison.shoop.entity.Orcamento;
import br.com.comparison.shoop.service.OrcamentoService;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfWriter;

public abstract class PDFBuild {
	
	public Document document;
	public ByteArrayOutputStream stream;
	public PdfWriter instance;
	
	
	public abstract void buildCorpo(Orcamento orcamento, OrcamentoService orcamentoService) throws DocumentException;
	
	
	
	

	
	public void openDocument() throws IOException, DocumentException{
		
		document = new Document();
		
		stream = new ByteArrayOutputStream();
		instance = PdfWriter.getInstance(document, stream);
		document.open();
		
	}
	
	public void closeDocument(){
		document.close();
	}

}
