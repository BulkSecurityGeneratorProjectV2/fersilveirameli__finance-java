package br.com.fsilveira.finance.business;

import javax.inject.Inject;

import br.com.fsilveira.finance.dao.AbstractDAO;
import br.com.fsilveira.finance.dao.CedenteSacadoDAO;
import br.com.fsilveira.finance.entity.CedenteSacado;

@SuppressWarnings("serial")

public class CedenteSacadoBusiness extends AbstractBusiness<CedenteSacado>{
	
	@Inject
	private CedenteSacadoDAO dao;


	@Override
	protected AbstractDAO<CedenteSacado> getDao() {
		return dao;
	}

	

}
