package br.com.fsilveira.finance.dao;

import br.com.fsilveira.finance.entity.CedenteSacado;


public class CedenteSacadoDAO extends AbstractDAO<CedenteSacado> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<CedenteSacado> getClazz() {
		return CedenteSacado.class;
	}

}
