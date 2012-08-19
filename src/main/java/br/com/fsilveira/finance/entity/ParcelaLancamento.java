package br.com.fsilveira.finance.entity;

// Generated 17/08/2012 23:01:50 by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
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

/**
 * ParcelaLancamento generated by hbm2java
 */
@Entity
@Table(name = "parcela_lancamento")
public class ParcelaLancamento extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "seq_parcela_lancamento", sequenceName = "seq_parcela_lancamento")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_parcela_lancamento")
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lancamento")
	private Lancamento lancamento;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "conta")
	private Conta conta;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_pgto", length = 13)
	private Date dataPgto;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_vencimento", length = 13)
	private Date dataVencimento;
	@Column(name = "parcela")
	private Integer parcela;
	@Column(name = "valor", precision = 18)
	private BigDecimal valor;
	@Column(name = "valor_real", precision = 18)
	private BigDecimal valorReal;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Lancamento getLancamento() {
		return lancamento;
	}
	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	public Date getDataPgto() {
		return dataPgto;
	}
	public void setDataPgto(Date dataPgto) {
		this.dataPgto = dataPgto;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public Integer getParcela() {
		return parcela;
	}
	public void setParcela(Integer parcela) {
		this.parcela = parcela;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public BigDecimal getValorReal() {
		return valorReal;
	}
	public void setValorReal(BigDecimal valorReal) {
		this.valorReal = valorReal;
	}

}
