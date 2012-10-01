package br.com.fsilveira.finance.business;

import javax.inject.Inject;

import br.com.fsilveira.finance.dao.AbstractDAO;
import br.com.fsilveira.finance.dao.ContaDAO;
import br.com.fsilveira.finance.entity.Conta;

@SuppressWarnings("serial")

public class ContaBusiness extends AbstractBusiness<Conta>{
	
	@Inject
	private ContaDAO dao;


	@Override
	protected AbstractDAO<Conta> getDao() {
		return dao;
	}

	

}
