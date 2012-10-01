package br.com.fsilveira.finance.business;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.fsilveira.finance.dao.AbstractDAO;
import br.com.fsilveira.finance.dao.DAOException;
import br.com.fsilveira.finance.entity.AbstractEntity;
import br.com.fsilveira.finance.faces.utils.ImportResults;
import br.com.fsilveira.finance.utils.Filter;
import br.com.fsilveira.finance.utils.ResultQuery;
import br.com.fsilveira.finance.utils.importation.FileImportException;

public abstract class AbstractBusiness<T extends AbstractEntity> implements Serializable {

	private static final long serialVersionUID = 1L;
	protected Logger logger = Logger.getLogger(getClass());

	protected abstract AbstractDAO<T> getDao();

	public void remove(T obj) throws BusinessException, DAOException {
		getDao().remove(obj);
	}

	public Boolean isExist(Filter filter) throws DAOException {
		return getDao().isExist(filter);
	}

	public Long countRows(Filter filter) throws BusinessException, DAOException {
		return getDao().countRows(filter);
	}

	

	

	public T get(Filter filter) throws BusinessException, DAOException {
		return getDao().get(filter);
	}

	public T get(Long id) throws BusinessException, DAOException {
		return getDao().get(id);
	}

	public List<T> list() throws BusinessException, DAOException {
		return list(0, 0, null).getList();
	}

	public List<T> list(Filter filter) throws BusinessException, DAOException {
		return list(0, 0, filter).getList();
	}

	public ResultQuery<T> list(int max, int currentPage, Filter filter) throws BusinessException, DAOException {
		return getDao().list(max, currentPage, filter);
	}

	public void saveOrUpdate(List<T> list) throws BusinessException, DAOException {
		getDao().saveOrUpdate(list);
	}

	public void saveOrUpdate(T t) throws BusinessException, DAOException {
		getDao().saveOrUpdate(t);
	}

	public ImportResults doUploadFile(InputStream is, Object... str) throws BusinessException, DAOException, FileImportException {
		throw new UnsupportedOperationException("Opera��o n�o implementada");
	}
}
