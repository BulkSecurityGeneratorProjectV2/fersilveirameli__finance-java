package br.com.fsilveira.finance.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
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

@Entity
@Table(name = "conta_bancaria")
@DiscriminatorValue("B")
public class ContaBancaria extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seq_conta_bancaria", sequenceName = "seq_conta_bancaria")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_conta_bancaria")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "banco")
	private Banco banco;

	@Column(name = "agencia")
	private Long agencia;

	@Column(name = "tipo_conta", nullable = false)
	private int tipoConta;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contaBancaria")
	private List<ContaCartaoCredito> contaCartaoCreditos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public Long getAgencia() {
		return agencia;
	}

	public void setAgencia(Long agencia) {
		this.agencia = agencia;
	}

	public int getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(int tipoConta) {
		this.tipoConta = tipoConta;
	}

	public List<ContaCartaoCredito> getContaCartaoCreditos() {
		return contaCartaoCreditos;
	}

	public void setContaCartaoCreditos(List<ContaCartaoCredito> contaCartaoCreditos) {
		this.contaCartaoCreditos = contaCartaoCreditos;
	}

}
