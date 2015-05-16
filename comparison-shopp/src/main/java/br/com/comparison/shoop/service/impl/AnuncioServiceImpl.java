package br.com.comparison.shoop.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.primefaces.event.FileUploadEvent;

import br.com.comparison.shoop.annotations.Transacao;
import br.com.comparison.shoop.dao.AnuncioDAO;
import br.com.comparison.shoop.entity.Anuncio;
import br.com.comparison.shoop.entity.Empresa;
import br.com.comparison.shoop.service.AnuncioService;

public class AnuncioServiceImpl implements AnuncioService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private AnuncioDAO anuncioDAO;

	@Override
	@Transacao
	public void salvar(Anuncio t) {
		anuncioDAO.save(t);
	}

	@Override
	@Transacao
	public void update(Anuncio t) {
		anuncioDAO.update(t);
	}

	@Override
	@Transacao
	public void deletar(Anuncio t) {
		anuncioDAO.delete(t);
	}

	@Override
	public List<Anuncio> list() {
		return anuncioDAO.list();
	}

	@Override
	public Anuncio loadById(int id) {
		return anuncioDAO.loadById(id);
	}

	@Override
	public List<Anuncio> findAnunciosByEmpresa(int idEmpresa) {
		return anuncioDAO.findAnunciosByEmpresa(idEmpresa);
	}

	@Override
	public List<Anuncio> findAnunciosByPesquisa(String nomePesquisa) {
		return anuncioDAO.findAnunciosByPesquisa(nomePesquisa);
	}

	@SuppressWarnings("resource")
	@Override
	public void handleUploadAnuncio(FileUploadEvent event, Empresa empresa, List<Anuncio> anuncios) throws IOException, ParseException {
		
		boolean isPrimeiraLinha = true;
		
		InputStream inputstream = event.getFile().getInputstream();
		
		Workbook workbook = new HSSFWorkbook(inputstream);
		
		Sheet sheet = workbook.getSheet("Sheet1");
		
		Iterator<Row> iterator = sheet.iterator();
		
		while(iterator.hasNext()){
			
			Anuncio anuncio = new Anuncio();
		
			Row row = iterator.next();
			
			Iterator<Cell> cellIterator = row.cellIterator();
			
			int i = 0;
			while(cellIterator.hasNext()){
				i++;
				
				Cell cell = cellIterator.next();
				
				if (isPrimeiraLinha){
					
					if (i == 3) {
						isPrimeiraLinha = false;
						i = 0;
					}
					continue;
				}
				
				
				if (i == 1) {
					anuncio.setNome(cell.getStringCellValue().trim());
					continue;
				}
				
				if (i == 2) {
					anuncio.setDescricao(cell.getStringCellValue().trim());
					continue;
				}
				
				if (i == 3) {
					i = 0;
					double d = 0;
					
					if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
						d = cell.getNumericCellValue();
						
					} else {
						
						String val = cell.getStringCellValue().trim();
						
						try {
							d = Double.parseDouble(val);
						} catch (NumberFormatException e) {
							Locale locale = new Locale("pt", "BR");
							d = NumberFormat.getInstance(locale).parse(val).doubleValue();
						}
					}
					
					
					BigDecimal decimal = new BigDecimal(d);
					anuncio.setValor(decimal);
				}
				
				
				anuncio.setDataCadastro(new Date());
				anuncio.setEmpresa(empresa);
				
				anuncios.add(anuncio);
			}
			
		
		}
		
		inputstream.close();
		
	}

}

