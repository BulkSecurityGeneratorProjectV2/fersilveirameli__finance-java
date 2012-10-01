package br.com.fsilveira.finance.business;

import javax.inject.Inject;

import br.com.fsilveira.finance.dao.AbstractDAO;
import br.com.fsilveira.finance.dao.LancamentoDAO;
import br.com.fsilveira.finance.entity.Lancamento;

@SuppressWarnings("serial")

public class LancamentoBusiness extends AbstractBusiness<Lancamento>{
	
	@Inject
	private LancamentoDAO dao;


	@Override
	protected AbstractDAO<Lancamento> getDao() {
		return dao;
	}

	

}
