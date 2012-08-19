package br.com.fsilveira.finance.entity;

// Generated 17/08/2012 23:01:50 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Lancamento generated by hbm2java
 */
@Entity
@Table(name = "lancamento")
public class Lancamento extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seq_lancamento", sequenceName = "seq_lancamento")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_lancamento")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cedente_sacado")
	private CedenteSacado cedenteSacado;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoria")
	private Categoria categoria;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "instancia")
	private Instancia instancia;
	@Column(name = "descricao", length = 60)
	private String descricao;
	@Column(name = "historico")
	private String historico;
	@Column(name = "tipo_lancamento")
	private Integer tipoLancamento;
	@Column(name = "repeticao_indefinida")
	private Boolean repeticaoIndefinida;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "lancamento")
	private Set<ParcelaLancamento> parcelaLancamentos = new HashSet<ParcelaLancamento>(0);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CedenteSacado getCedenteSacado() {
		return cedenteSacado;
	}

	public void setCedenteSacado(CedenteSacado cedenteSacado) {
		this.cedenteSacado = cedenteSacado;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Instancia getInstancia() {
		return instancia;
	}

	public void setInstancia(Instancia instancia) {
		this.instancia = instancia;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public Integer getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(Integer tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}

	public Boolean getRepeticaoIndefinida() {
		return repeticaoIndefinida;
	}

	public void setRepeticaoIndefinida(Boolean repeticaoIndefinida) {
		this.repeticaoIndefinida = repeticaoIndefinida;
	}

	public Set<ParcelaLancamento> getParcelaLancamentos() {
		return parcelaLancamentos;
	}

	public void setParcelaLancamentos(Set<ParcelaLancamento> parcelaLancamentos) {
		this.parcelaLancamentos = parcelaLancamentos;
	}

}
