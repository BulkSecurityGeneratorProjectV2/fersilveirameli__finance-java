package br.com.fsilveira.finance.faces;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.joda.time.LocalDateTime;
import org.primefaces.event.RowEditEvent;

import br.com.fsilveira.finance.business.AbstractBusiness;
import br.com.fsilveira.finance.business.BusinessException;
import br.com.fsilveira.finance.business.Month;
import br.com.fsilveira.finance.business.ParcelaLancamentoBusiness;
import br.com.fsilveira.finance.dao.DAOException;
import br.com.fsilveira.finance.entity.ParcelaLancamento;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class ParcelaLancamentoBean extends AbstractCrudBean<ParcelaLancamento> {

	private List<ParcelaLancamento> parcelaLancamentos = new ArrayList<ParcelaLancamento>();

	@Inject
	private ParcelaLancamentoBusiness business;

	private int currentMonth;

	public ParcelaLancamentoBean() {
		this.currentMonth = new LocalDateTime().getMonthOfYear();
	}

	public boolean isCurrentMonth(Month month) {
		return (month.ordinal() + 1) == currentMonth;
	}

	public void buildGrid() {
		setParcelaLancamentos(business.listByPeriod(currentMonth, 2012));
	}

	public List<ParcelaLancamento> getParcelaLancamentos() {
		return parcelaLancamentos;
	}

	public void setParcelaLancamentos(List<ParcelaLancamento> parcelaLancamentos) {
		this.parcelaLancamentos = parcelaLancamentos;
	}

	@Override
	protected AbstractBusiness<ParcelaLancamento> getBusiness() {
		return business;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void clean() {
		// TODO Auto-generated method stub

	}

	@Override
	public ParcelaLancamento getBean() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBean(ParcelaLancamento t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preBuildTable() {
		// TODO Auto-generated method stub

	}

	@Override
	public void posBuildTable() {
		// TODO Auto-generated method stub

	}

	public int getCurrentMonth() {
		return currentMonth;
	}

	public void setCurrentMonth(int currentMonth) {
		this.currentMonth = currentMonth;
	}

	public void onEdit(RowEditEvent event) {
		FacesMessage msg = null;
		try {
			ParcelaLancamento parcelaLancamento = (ParcelaLancamento) event.getObject();
			getBusiness().saveOrUpdate(parcelaLancamento);
			msg = new FacesMessage("Lançamento alterado", parcelaLancamento.getLancamento().getDescricao());

		} catch (BusinessException e) {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", e.getMessageKey().getKey());

		} catch (DAOException e) {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e.getMessageKey().getKey());
		} finally {
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	public void onCancel(RowEditEvent event) {

	}

}
