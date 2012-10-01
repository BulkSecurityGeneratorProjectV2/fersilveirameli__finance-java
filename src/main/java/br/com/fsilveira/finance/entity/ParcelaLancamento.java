package br.com.fsilveira.finance.entity;


import java.math.BigDecimal;

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

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;


@Entity
@Table(name = "parcela_lancamento")
public class ParcelaLancamento extends AbstractEntity {

	
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
	
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	@Column(name = "data_pgto", length = 13)
	private LocalDateTime  dataPgto;
	
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	@Column(name = "data_vencimento", length = 13)
	private LocalDateTime dataVencimento;
	
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
	public LocalDateTime getDataPgto() {
		return dataPgto;
	}
	public void setDataPgto(LocalDateTime dataPgto) {
		this.dataPgto = dataPgto;
	}
	public LocalDateTime getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(LocalDateTime dataVencimento) {
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
