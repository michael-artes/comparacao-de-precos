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
import java.util.Map.Entry;

import br.com.comparison.shoop.entity.Empresa;
import br.com.comparison.shoop.entity.ItemOrcamento;
import br.com.comparison.shoop.entity.Orcamento;

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
	public void buildCorpo(Orcamento orcamento, List<Empresa> list) throws DocumentException {
		
		preencherMapEmpresaByItem(orcamento, map, list);
		
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
		cell2.setBackgroundColor(Color.DARK_GRAY);
		cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell2);
		
		PdfPCell cell3 = new PdfPCell(new Phrase("Descrição", f));
		cell3.setBorder(Rectangle.NO_BORDER);
		cell3.setBackgroundColor(Color.DARK_GRAY);
		cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell3);
		
		PdfPCell cell4 = new PdfPCell(new Phrase("Valor", f));
		cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell4.setBorder(Rectangle.NO_BORDER);
		cell4.setBackgroundColor(Color.DARK_GRAY);
		cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell4);
		
		PdfPCell cell5 = new PdfPCell(new Phrase("Empresa", f));
		cell5.setBorder(Rectangle.NO_BORDER);	
		cell5.setBackgroundColor(Color.DARK_GRAY);
		cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell5);
		
		BigDecimal decimal = null;
		Locale locale = new Locale("pt", "BR");
		NumberFormat format = NumberFormat.getInstance(locale);
		
		for (Entry<Empresa, List<ItemOrcamento>> entry : map.entrySet()) {
			
			List<ItemOrcamento> itens = entry.getValue();
			ordenarByValor(itens);
			
			table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			for (ItemOrcamento item : itens) {
				
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
				table.addCell(entry.getKey().getNomeFantasia());
			}
			
			int a = 2;
			
			while(a != 0){
				PdfPCell cellEmpty = new PdfPCell();
				cellEmpty.setBorder(Rectangle.NO_BORDER);
				cellEmpty.setBackgroundColor(Color.WHITE);
				cellEmpty.setColspan(7); 
				table.addCell(cellEmpty);
				
				a--;
			}
			
		}
		
		
		document.add(table);
	}

	
	private void preencherMapEmpresaByItem(Orcamento orc, Map<Empresa, List<ItemOrcamento>> m, List<Empresa> list) {
		
		ordenarByName(list);
		
		for (Empresa e : list) {
			List<ItemOrcamento> itens = new ArrayList<ItemOrcamento>(0);
			
			for (ItemOrcamento item : orc.getItemOrcamentos()) {
				
				if (item.getEmpresa().getId() == e.getId()) {
					itens.add(item);
				}
				
			}
			
			m.put(e, itens);
		}
		
		
	}

	
	private void ordenarByName(List<Empresa> list){
		
		Collections.sort(list, new Comparator<Empresa>() {

			@Override
			public int compare(Empresa o1, Empresa o2) {
				return o1.getNomeFantasia().compareToIgnoreCase(o2.getNomeFantasia());
			}
		});
		
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
