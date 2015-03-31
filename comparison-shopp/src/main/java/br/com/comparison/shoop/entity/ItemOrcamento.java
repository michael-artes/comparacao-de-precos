package br.com.comparison.shoop.entity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="item_orcamento", schema="web")
public class ItemOrcamento {
	
	@Id
	@SequenceGenerator(name="generator_item_orcamento", sequenceName="web.item_orcamento_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="generator_item_orcamento")
	@Column(name="id", unique=true, nullable=false)
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_orcamento")
	private Orcamento orcamento;
	
	@Column(name="nome", nullable = false, length = 50)
	private String nome;
	
	@Column(name="descricao", nullable = false, length = 250)
	private String descricao;
	
	@Column(name="valor", precision = 10, scale = 2, nullable = false)
	private BigDecimal valor;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_cadastro", nullable = false)
	private Date dataCadastro;
	
	@Column(name="imagem", nullable = true)
	private byte[] imagem;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Orcamento getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	@Override
	public String toString() {
		return "ItemOrcamento [id=" + id + ", orcamento=" + orcamento
				+ ", nome=" + nome + ", descricao=" + descricao + ", valor="
				+ valor + ", dataCadastro=" + dataCadastro + ", imagem="
				+ Arrays.toString(imagem) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + Arrays.hashCode(imagem);
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((orcamento == null) ? 0 : orcamento.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemOrcamento other = (ItemOrcamento) obj;
		if (dataCadastro == null) {
			if (other.dataCadastro != null)
				return false;
		} else if (!dataCadastro.equals(other.dataCadastro))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (!Arrays.equals(imagem, other.imagem))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (orcamento == null) {
			if (other.orcamento != null)
				return false;
		} else if (!orcamento.equals(other.orcamento))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}
	
}
