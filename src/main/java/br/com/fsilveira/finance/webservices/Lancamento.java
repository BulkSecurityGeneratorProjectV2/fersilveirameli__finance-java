package br.com.fsilveira.finance.webservices;

public class Lancamento {

	private String descricao;
	private String historico;
	private int tipo;
	private boolean repeticaoIndefinida;
	private long conta;
	private long cedenteSacado;
	private long categoria;
	private long dataPgto;
	private long dataVencimento;
	private int parcelas;
	private double valor;

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

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public boolean isRepeticaoIndefinida() {
		return repeticaoIndefinida;
	}

	public void setRepeticaoIndefinida(boolean repeticaoIndefinida) {
		this.repeticaoIndefinida = repeticaoIndefinida;
	}

	public long getConta() {
		return conta;
	}

	public void setConta(long conta) {
		this.conta = conta;
	}

	public long getCedenteSacado() {
		return cedenteSacado;
	}

	public void setCedenteSacado(long cedenteSacado) {
		this.cedenteSacado = cedenteSacado;
	}

	public long getCategoria() {
		return categoria;
	}

	public void setCategoria(long categoria) {
		this.categoria = categoria;
	}

	public long getDataPgto() {
		return dataPgto;
	}

	public void setDataPgto(long dataPgto) {
		this.dataPgto = dataPgto;
	}

	public long getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(long dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public int getParcelas() {
		return parcelas;
	}

	public void setParcelas(int parcelas) {
		this.parcelas = parcelas;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}
