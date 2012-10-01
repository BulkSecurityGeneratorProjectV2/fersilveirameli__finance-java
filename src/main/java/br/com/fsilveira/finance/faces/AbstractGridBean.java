package br.com.fsilveira.finance.faces;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.model.DataModel;

import br.com.fsilveira.finance.business.AbstractBusiness;
import br.com.fsilveira.finance.business.BusinessException;
import br.com.fsilveira.finance.dao.DAOException;
import br.com.fsilveira.finance.entity.AbstractEntity;
import br.com.fsilveira.finance.faces.utils.ItemPage;
import br.com.fsilveira.finance.utils.Filter;
import br.com.fsilveira.finance.utils.OrderBy;
import br.com.fsilveira.finance.utils.ResultQuery;
import br.com.fsilveira.finance.utils.enums.Order;

public abstract class AbstractGridBean<T extends AbstractEntity, E extends HtmlDataTable> extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer currentPage;
	private int maxRegPage;
	private DataModel<T> dataModel;
	private Filter filter;
	private List<ItemPage> pages;

	public AbstractGridBean() {
		this(null);
	}

	public AbstractGridBean(OrderBy defaultOrderBy) {
		this.maxRegPage = 10;
		this.filter = new Filter(defaultOrderBy);
		this.buildPages();
	}

	protected void buildPages() {
		setPages(new ArrayList<ItemPage>());
		// FIXME: corrigir internacionaliza��o
		getPages().add(new ItemPage("5 �tens por p�gina", 5));
		getPages().add(new ItemPage("10 �tens por p�gina", 10));
		getPages().add(new ItemPage("25 �tens por p�gina", 25));
		getPages().add(new ItemPage("50 �tens por p�gina", 50));
		getPages().add(new ItemPage("100 �tens por p�gina", 100));
		getPages().add(new ItemPage("N�o paginar", -1));
	}

	public void refreshBean(T obj) {
		try {
			obj = getBusiness().get(obj.getId());
		} catch (BusinessException e) {
			getMessages().addExceptionMessage(e);
		} catch (DAOException e) {
			getMessages().addExceptionMessage(e);
		}
	}

	public void sort() {

		OrderBy orderBy = getFilter().getOrderBy();

		String field = getHttpServletRequest().getParameter("field");

		if (orderBy.getField().equals(field)) {
			orderBy.setOrder(orderBy.getOrder().invert());
		} else {
			orderBy.setField(field);
			orderBy.setOrder(Order.ASC);
		}

		this.setCurrentPage(1);
	}

	public void filter() {
		this.setCurrentPage(1);
	}

	public void filterReset() {
		this.setCurrentPage(1);
	}

	protected abstract AbstractBusiness<T> getBusiness();

	public abstract void init();

	public abstract void clean();

	public abstract T getBean();

	public abstract void setBean(T t);

	public List<ItemPage> getPages() {
		return pages;
	}

	public abstract void preBuildTable();

	public abstract void posBuildTable();

	public boolean isContainsItens() {
		return getDataModel() != null && getDataModel().getRowCount() > 0;
	}

	public ResultQuery<T> getList(int maxRegPage, int first, Filter filter) throws BusinessException, DAOException {
		return getBusiness().list(maxRegPage, first, getFilter());
	}

	public boolean isCanHide() {
		return getFacesContext().getMaximumSeverity() == null || getFacesContext().getMaximumSeverity().equals(FacesMessage.SEVERITY_INFO);
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public int getMaxRegPage() {
		return maxRegPage;
	}

	public void setMaxRegPage(int maxRegPage) {
		this.maxRegPage = maxRegPage;
		this.setCurrentPage(1);
	}

	public DataModel<T> getDataModel() {
		return dataModel;
	}

	public void setDataModel(DataModel<T> dataModel) {
		this.dataModel = dataModel;
	}

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	public void setPages(List<ItemPage> pages) {
		this.pages = pages;
	}

	public Integer countRows(Filter filter) throws BusinessException, DAOException {
		return new Long(getBusiness().countRows(filter)).intValue();
	}

}
