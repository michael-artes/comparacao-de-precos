package br.com.comparison.shoop.managedBeans;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.comparison.shoop.entity.Anuncio;
import br.com.comparison.shoop.entity.ItemOrcamento;
import br.com.comparison.shoop.entity.Orcamento;
import br.com.comparison.shoop.enuns.EnumStatusOrcamento;
import br.com.comparison.shoop.pdf.PDFBuild;
import br.com.comparison.shoop.pdf.PDFBuildCorpo;
import br.com.comparison.shoop.service.AnuncioService;
import br.com.comparison.shoop.service.OrcamentoService;
import br.com.comparison.shoop.service.UsuarioService;

@Named
@SessionScoped
public class OrcamentoMB implements Serializable {
	
	private static Logger LOGGER = Logger.getLogger(OrcamentoMB.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2029103509647593931L;
	
	
	@Inject private AnuncioService anuncioService;
	@Inject private UsuarioService usuarioService;
	@Inject private OrcamentoService orcamentoService;
	@Inject private UsuarioMB usuarioMB;
	
	private String nomePesquisa; 
	private Anuncio anuncioSelected;
	
	private Orcamento orcamento;
	private List<ItemOrcamento> itemOrcamentos = new ArrayList<ItemOrcamento>(0);
	
	private Set<Anuncio> anunciosByPesquisa = new TreeSet<Anuncio>(new Comparator<Anuncio>() {

		@Override
		public int compare(Anuncio o1, Anuncio o2) {
			return Integer.compare(o1.getId(), o2.getId());
		}
	});
	
	private Set<Anuncio> countAnunciosEscolhidos = new TreeSet<Anuncio>(new Comparator<Anuncio>() {

		@Override
		public int compare(Anuncio o1, Anuncio o2) {
			return Integer.compare(o1.getId(), o2.getId());
		}
	});
	
	
	public int getCountAnunciosEscolhidos(){
		return countAnunciosEscolhidos.size();
	}
	public void setCountAnunciosEscolhidos(Set<Anuncio> countAnunciosEscolhidos) {
		this.countAnunciosEscolhidos = countAnunciosEscolhidos;
	}
	public Orcamento getOrcamento() {
		return orcamento;
	}
	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}
	public List<ItemOrcamento> getItemOrcamentos() {
		return itemOrcamentos;
	}
	public void setItemOrcamentos(List<ItemOrcamento> itemOrcamentos) {
		this.itemOrcamentos = itemOrcamentos;
	}
	public String getNomePesquisa() {
		return nomePesquisa;
	}

	public void setNomePesquisa(String nomePesquisa) {
		this.nomePesquisa = nomePesquisa;
	}

	public Set<Anuncio> getAnunciosByPesquisa() {
		return anunciosByPesquisa;
	}
	
	public Anuncio getAnuncioSelected() {
		return anuncioSelected;
	}

	public void setAnuncioSelected(Anuncio anuncioSelected) {
		this.anuncioSelected = anuncioSelected;
	}
	
	

	/**
	 * *****************************************************************************   GATO
	 * *//*
	@PostConstruct
	public void init(){
		orcamento = orcamentoService.loadById(12);
		itemOrcamentos.addAll(orcamento.getItemOrcamentos());
		
		LOGGER.debug(orcamento.toString());
	}
	*//**
	 * *****************************************************************************   FIM GATO
	 * */
	
	public void adicionarAnuncio(ActionEvent event){
		
		FacesContext context = FacesContext.getCurrentInstance();
		String idAnuncioString = context.getExternalContext().getRequestParameterMap().get("idAnuncio");
		
		Anuncio a = anuncioService.loadById(Integer.parseInt(idAnuncioString));
		
		countAnunciosEscolhidos.add(a);
		
	}
	
	public void pesquisarAnuncios(ActionEvent event){
		anunciosByPesquisa = new HashSet<Anuncio>(0);
		List<Anuncio> listAnuncios = anuncioService.findAnunciosByPesquisa(this.nomePesquisa);
		anunciosByPesquisa.addAll(listAnuncios);
		
		this.nomePesquisa = ""; //Limpa o campo
	}
	
	
	public String geraOrcamento(){
		FacesContext context = FacesContext.getCurrentInstance();
		
		
		if (countAnunciosEscolhidos.isEmpty()) {
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn",  "Para gerar um orçamento é necessário selecionar anuncios") );
			
			return null;
		}
		
		
		orcamento = new Orcamento();
		orcamento.setDataCadastro(new Date());
		orcamento.setStatusOrcamento(EnumStatusOrcamento.ABERTO);
		orcamento.setUsuario( usuarioService.loadById(usuarioMB.getUserSession().getIdUser()) );
		
		for (Anuncio anuncio : countAnunciosEscolhidos) {
			
			ItemOrcamento item = new ItemOrcamento();
			item.setDataCadastro(new Date());
			item.setDescricao(anuncio.getDescricao());
			item.setEmpresa(anuncio.getEmpresa());
			item.setImagem(anuncio.getImagem());
			item.setNome(anuncio.getNome());
			item.setValor(anuncio.getValor());
			item.setOrcamento(orcamento);
			
			orcamento.getItemOrcamentos().add(item);
		}
		
		
		try {
			orcamentoService.salvar(orcamento);
		} catch (Exception e) {

			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",  "Não foi possível salvar o orçamento") );
			return null;
			
		}
		
		itemOrcamentos.addAll(orcamento.getItemOrcamentos());
		countAnunciosEscolhidos.clear(); //Remover a lista de anuncios
		
		return "gerar-orcamento";
	}
	
	
	
	
	public StreamedContent gerarPDFOrcamentoStrem(){
		
		
		
		try {
			return orcamentoService.gerarPDFOrcamentoStrem(orcamento.getId());
		} catch (Exception e) {
			LOGGER.error("Não foi possível gerar o pdf", e);
		}
		
		return new DefaultStreamedContent();
	}
	
	public StreamedContent getFileOrcamento(){
		return gerarPDFOrcamentoStrem();
	}
	
	
}
