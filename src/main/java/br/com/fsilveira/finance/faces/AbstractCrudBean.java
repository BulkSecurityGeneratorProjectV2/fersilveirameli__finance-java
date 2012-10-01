package br.com.fsilveira.finance.faces;

import javax.faces.component.html.HtmlDataTable;

import br.com.fsilveira.finance.business.BusinessException;
import br.com.fsilveira.finance.dao.DAOException;
import br.com.fsilveira.finance.entity.AbstractEntity;
import br.com.fsilveira.finance.faces.utils.ImportUtils;
import br.com.fsilveira.finance.faces.utils.messagesI18N.MessageKey;
import br.com.fsilveira.finance.utils.OrderBy;

public abstract class AbstractCrudBean<T extends AbstractEntity> extends AbstractGridBean<T, HtmlDataTable> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImportUtils<T> importUtils;

	public AbstractCrudBean() {
		this(null);
		
	}

	public AbstractCrudBean(OrderBy orderBy) {
		super(orderBy);
		this.importUtils = new ImportUtils<T>(getBusiness());
	}

	public void save() {

		try {
			boolean isPersisted = getBean().isPersisted();
			getBusiness().saveOrUpdate(getBean());
			getMessages().addMessage((isPersisted) ? MessageKey.GENERIC_UPDATE_SUCCESS : MessageKey.GENERIC_SAVE_SUCCESS);
			this.setCurrentPage(1);
			clean();
		} catch (BusinessException e) {
			getMessages().addExceptionMessage(e);
		} catch (DAOException e) {
			getMessages().addExceptionMessage(e);
		}
	}

	public void remove() {
		try {
			getBusiness().remove(getBean());
			getMessages().addMessage(MessageKey.GENERIC_REMOVE_SUCCESS);
			this.setCurrentPage(1);
			clean();
		} catch (BusinessException e) {
			getMessages().addExceptionMessage(e);

		} catch (DAOException e) {
			getMessages().addExceptionMessage(e);

		} 
	}

	public ImportUtils<T> getImportUtils() {
		return importUtils;
	}

	public void setImportUtils(ImportUtils<T> importUtils) {
		this.importUtils = importUtils;
	}

}
