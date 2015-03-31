package br.com.comparison.shoop.entity;

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

@Entity
@Table(name="palavras_chave", schema="web")
public class PalavrasChave {
	
	@Id
	@SequenceGenerator(name="generator_palavras_chave", sequenceName="web.palavras_chave_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="generator_palavras_chave")
	@Column(name="id", unique=true, nullable=false)
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_anuncio", nullable = false)
	private Anuncio anuncio;
	
	@Column(name="nome_chave", nullable = false, length = 100)
	private String nomeChave;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Anuncio getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}

	public String getNomeChave() {
		return nomeChave;
	}

	public void setNomeChave(String nomeChave) {
		this.nomeChave = nomeChave;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((anuncio == null) ? 0 : anuncio.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((nomeChave == null) ? 0 : nomeChave.hashCode());
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
		PalavrasChave other = (PalavrasChave) obj;
		if (anuncio == null) {
			if (other.anuncio != null)
				return false;
		} else if (!anuncio.equals(other.anuncio))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nomeChave == null) {
			if (other.nomeChave != null)
				return false;
		} else if (!nomeChave.equals(other.nomeChave))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PalavrasChave [id=" + id + ", anuncio=" + anuncio
				+ ", nomeChave=" + nomeChave + "]";
	}
	
}