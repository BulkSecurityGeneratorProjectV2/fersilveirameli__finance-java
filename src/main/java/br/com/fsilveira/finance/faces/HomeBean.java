package br.com.fsilveira.finance.faces;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.fsilveira.finance.business.ParcelaLancamentoBusiness;
import br.com.fsilveira.finance.entity.ParcelaLancamento;


@Named
@ViewScoped
public class HomeBean extends AbstractBean  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ParcelaLancamentoBusiness business;

	private List<ParcelaLancamento> lancamentosFuturos = new ArrayList<ParcelaLancamento>();
	private List<ParcelaLancamento> lancamentosPendentes = new ArrayList<ParcelaLancamento>();

	public void buildGridFuturos() {
		setLancamentosFuturos(business.listFuturos());
	}

	public void buildGridPendentes() {
		setLancamentosPendentes(business.listPendentes());
	}

	public List<ParcelaLancamento> getLancamentosFuturos() {
		return lancamentosFuturos;
	}

	public void setLancamentosFuturos(List<ParcelaLancamento> lancamentosFuturos) {
		this.lancamentosFuturos = lancamentosFuturos;
	}

	public List<ParcelaLancamento> getLancamentosPendentes() {
		return lancamentosPendentes;
	}

	public void setLancamentosPendentes(List<ParcelaLancamento> lancamentosPendentes) {
		this.lancamentosPendentes = lancamentosPendentes;
	}

}
