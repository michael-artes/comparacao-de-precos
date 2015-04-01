package br.com.comparison.shoop.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.comparison.shoop.enuns.EnumStatusOrcamento;

@Entity
@Table(name="orcamento", schema="web")
public class Orcamento {
	
	@Id
	@SequenceGenerator(name="generator_orcamento", sequenceName="web.orcamento_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="generator_orcamento")
	@Column(name="id", unique=true, nullable=false)
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_usuario", nullable = false)
	private Usuario usuario;
	
	@Enumerated
	@Column(name="status", nullable = false)
	private EnumStatusOrcamento statusOrcamento;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_cadastro", nullable = false)
	private Date dataCadastro;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="orcamento")
	private Set<ItemOrcamento> itemOrcamentos = new HashSet<ItemOrcamento>(0);
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public EnumStatusOrcamento getStatusOrcamento() {
		return statusOrcamento;
	}

	public void setStatusOrcamento(EnumStatusOrcamento statusOrcamento) {
		this.statusOrcamento = statusOrcamento;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Set<ItemOrcamento> getItemOrcamentos() {
		return itemOrcamentos;
	}

	public void setItemOrcamentos(Set<ItemOrcamento> itemOrcamentos) {
		this.itemOrcamentos = itemOrcamentos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((statusOrcamento == null) ? 0 : statusOrcamento.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Orcamento other = (Orcamento) obj;
		if (dataCadastro == null) {
			if (other.dataCadastro != null)
				return false;
		} else if (!dataCadastro.equals(other.dataCadastro))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (itemOrcamentos == null) {
			if (other.itemOrcamentos != null)
				return false;
		} else if (!itemOrcamentos.equals(other.itemOrcamentos))
			return false;
		if (statusOrcamento != other.statusOrcamento)
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Orcamento [id=" + id + ", usuario=" + usuario
				+ ", statusOrcamento=" + statusOrcamento + ", dataCadastro=" + dataCadastro + "]";
	}
	
}
