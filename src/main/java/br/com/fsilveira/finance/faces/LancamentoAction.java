package br.com.fsilveira.finance.faces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.fsilveira.finance.business.LancamentoBusiness;
import br.com.fsilveira.finance.entity.Lancamento;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class LancamentoAction implements Serializable {

	private List<Lancamento> lancamentos = new ArrayList<Lancamento>();

	@Inject
	private LancamentoBusiness business;

	public void buildGrid() {
		lancamentos = business.list();
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

}
