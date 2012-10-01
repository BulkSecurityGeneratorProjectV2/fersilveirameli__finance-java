package br.com.fsilveira.finance.entity;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "conta")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "conta_type")
public class Conta extends AbstractEntity {

	@Id
	@SequenceGenerator(name = "conta", sequenceName = "conta")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conta")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "instancia")
	private Instancia instancia;

	@Column(name = "nome", nullable = false, length = 30)
	private String nome;

	@Column(name = "limite", precision = 18)
	private BigDecimal limite;

	@Column(name = "saldo", precision = 18)
	private BigDecimal saldo;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "conta")
	private List<ParcelaLancamento> parcelaLancamentos = Collections.emptyList();

	public List<ParcelaLancamento> getParcelaLancamentos() {
		return this.parcelaLancamentos;
	}

	public void setParcelaLancamentos(List<ParcelaLancamento> parcelaLancamentos) {
		this.parcelaLancamentos = parcelaLancamentos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instancia getInstancia() {
		return instancia;
	}

	public void setInstancia(Instancia instancia) {
		this.instancia = instancia;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public void setLimite(BigDecimal limite) {
		this.limite = limite;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

}
