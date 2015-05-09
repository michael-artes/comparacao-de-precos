package br.com.comparison.shoop.pdf;

import br.com.comparison.shoop.entity.Orcamento;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfTable;

public class PDFBuildCorpo extends PDFBuild{

	@Override
	public void buildCorpo(Orcamento orcamento) throws DocumentException {
		
		
		
		
		
		document.add(new Paragraph("Alo Mundo Itext"));
	}

}
