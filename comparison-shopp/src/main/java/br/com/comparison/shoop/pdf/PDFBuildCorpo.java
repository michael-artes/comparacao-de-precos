package br.com.comparison.shoop.pdf;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;

public class PDFBuildCorpo extends PDFBuild{

	@Override
	public void buildCorpo() throws DocumentException {
		document.add(new Paragraph("Alo Mundo Itext"));
	}

}
