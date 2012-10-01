package br.com.fsilveira.finance.business;

import java.util.List;

import javax.inject.Inject;

import br.com.fsilveira.finance.dao.AbstractDAO;
import br.com.fsilveira.finance.dao.ParcelaLancamentoDAO;
import br.com.fsilveira.finance.entity.ParcelaLancamento;


public class ParcelaLancamentoBusiness extends AbstractBusiness<ParcelaLancamento> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ParcelaLancamentoDAO dao;

	@Override
	protected AbstractDAO<ParcelaLancamento> getDao() {
		return dao;
	}

	public List<ParcelaLancamento> listPendentes() {
		return dao.listPendentes();
	}

	public List<ParcelaLancamento> listFuturos() {
		return dao.listFuturos();
	}

	public List<ParcelaLancamento> listByPeriod(int month, int year) {
		return dao.listByPeriod(month, year);
	}

}
