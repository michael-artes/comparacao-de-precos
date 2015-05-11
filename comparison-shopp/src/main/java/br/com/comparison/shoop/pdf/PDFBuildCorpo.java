package br.com.comparison.shoop.pdf;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import br.com.comparison.shoop.entity.Empresa;
import br.com.comparison.shoop.entity.ItemOrcamento;
import br.com.comparison.shoop.entity.Orcamento;
import br.com.comparison.shoop.service.OrcamentoService;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class PDFBuildCorpo extends PDFBuild{
	
	private boolean pintou;
	private Map<Empresa, List<ItemOrcamento>> map = new HashMap<Empresa, List<ItemOrcamento>>(0);
	
	@Override
	public void buildCorpo(Orcamento orcamento, OrcamentoService orcamentoService) throws DocumentException {
		
		Orcamento orc = orcamentoService.loadById(orcamento.getId());
		
		preencherMapEmpresaByItem(orc);
		
		//Pdf tera 4 colunas
		PdfPTable table = new PdfPTable( new float[]{5, 6, 3, 5} );
		table.setWidthPercentage(100f);
		
		//Titulo da Tabela
		Font f = new Font(Font.BOLD);
		f.setColor(Color.WHITE);
		
		PdfPCell cell = new PdfPCell(new Phrase("Orçamento #" + orcamento.getId(), f));
		cell.setBackgroundColor(Color.BLACK);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(7); 
		
		table.addCell(cell);
		
		
		PdfPCell cell2 = new PdfPCell(new Phrase("Nome", f));
		cell2.setBorder(Rectangle.NO_BORDER);
		cell2.setBackgroundColor(Color.RED);
		cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell2);
		
		PdfPCell cell3 = new PdfPCell(new Phrase("Descrição", f));
		cell3.setBorder(Rectangle.NO_BORDER);
		cell3.setBackgroundColor(Color.RED);
		cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell3);
		
		PdfPCell cell4 = new PdfPCell(new Phrase("Valor", f));
		cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell4.setBorder(Rectangle.NO_BORDER);
		cell4.setBackgroundColor(Color.RED);
		cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell4);
		
		PdfPCell cell5 = new PdfPCell(new Phrase("Empresa", f));
		cell5.setBorder(Rectangle.NO_BORDER);	
		cell5.setBackgroundColor(Color.RED);
		cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell5);
		
		BigDecimal decimal = null;
		Locale locale = new Locale("pt", "BR");
		NumberFormat format = NumberFormat.getInstance(locale);
		
		List<ItemOrcamento> list = new ArrayList<ItemOrcamento>(orc.getItemOrcamentos());
		ordenarByValor(list);
		
		table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		for (ItemOrcamento item : list) {
			
			if (!pintou) {
				table.getDefaultCell().setBackgroundColor(new Color(220, 220, 220));
				pintou = true;
			} else {
				table.getDefaultCell().setBackgroundColor(new Color(235, 235, 235));
				pintou = false;
			}
			
			table.addCell(item.getNome());
			table.addCell(item.getDescricao());
			
			decimal = item.getValor();
			decimal.setScale(2, RoundingMode.HALF_UP);
			
			table.addCell( format.format(decimal) );
			table.addCell(item.getEmpresa().getNomeFantasia());
			
			
		}
		
		document.add(table);
	}

	
	private void preencherMapEmpresaByItem(Orcamento orc) {
		//TODO: Implementar metodo
	}


	private void ordenarByValor(List<ItemOrcamento> list) {
		
		Collections.sort(list, new Comparator<ItemOrcamento>() {

			@Override
			public int compare(ItemOrcamento o1, ItemOrcamento o2) {
				return Double.compare(o1.getValor().doubleValue(), o2.getValor().doubleValue());
			}
		});
		
		
	}
	

}
