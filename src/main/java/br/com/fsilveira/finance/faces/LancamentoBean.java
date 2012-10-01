package br.com.fsilveira.finance.faces;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.joda.time.LocalDateTime;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;

import br.com.fsilveira.finance.business.AbstractBusiness;
import br.com.fsilveira.finance.business.LancamentoBusiness;
import br.com.fsilveira.finance.business.Month;
import br.com.fsilveira.finance.entity.Lancamento;
import br.com.fsilveira.finance.entity.ParcelaLancamento;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class LancamentoBean extends AbstractCrudBean<Lancamento> {

	private List<ParcelaLancamento> parcelaLancamentos = new ArrayList<ParcelaLancamento>();

	@Inject
	private LancamentoBusiness business;

	private Lancamento lancamento;

	private int currentMonth;

	public LancamentoBean() {
		this.currentMonth = new LocalDateTime().getMonthOfYear();
	}

	public boolean isCurrentMonth(Month month) {
		return (month.ordinal() + 1) == currentMonth;
	}

	public List<ParcelaLancamento> getParcelaLancamentos() {
		return parcelaLancamentos;
	}

	public void setParcelaLancamentos(List<ParcelaLancamento> parcelaLancamentos) {
		this.parcelaLancamentos = parcelaLancamentos;
	}

	@Override
	protected AbstractBusiness<Lancamento> getBusiness() {
		return business;
	}

	public void handleFileUpload(FileUploadEvent event) {
		FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
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
	public Lancamento getBean() {
		return this.lancamento;
	}

	@Override
	public void setBean(Lancamento lancamento) {
		this.lancamento = lancamento;

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
		FacesMessage msg = new FacesMessage("Car Edited", ((Lancamento) event.getObject()).getDescricao());

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Car Cancelled", ((Lancamento) event.getObject()).getDescricao());

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
