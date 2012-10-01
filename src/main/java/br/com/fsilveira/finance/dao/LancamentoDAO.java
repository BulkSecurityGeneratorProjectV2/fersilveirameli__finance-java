package br.com.fsilveira.finance.dao;

import br.com.fsilveira.finance.entity.Lancamento;


public class LancamentoDAO extends AbstractDAO<Lancamento> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<Lancamento> getClazz() {
		return Lancamento.class;
	}

}
