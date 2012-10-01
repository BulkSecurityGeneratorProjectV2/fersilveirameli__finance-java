package br.com.fsilveira.finance.dao;

import br.com.fsilveira.finance.entity.Conta;


public class ContaDAO extends AbstractDAO<Conta> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<Conta> getClazz() {
		return Conta.class;
	}
	
	

}
