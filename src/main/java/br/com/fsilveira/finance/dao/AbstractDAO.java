package br.com.fsilveira.finance.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import br.com.fsilveira.finance.entity.AbstractEntity;
import br.com.fsilveira.finance.utils.Condition;
import br.com.fsilveira.finance.utils.Filter;
import br.com.fsilveira.finance.utils.ResultQuery;
import br.com.fsilveira.finance.utils.enums.Operator;

public abstract class AbstractDAO<T extends AbstractEntity> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1519611681625884637L;
	protected Logger logger = Logger.getLogger(this.getClass());

	@Inject
	@ViewScopedEntityManager
	protected EntityManager entityManager;

	protected abstract Class<T> getClazz();

	public void remove(T obj) throws DAOException {
		try {
			entityManager.remove(obj);
			entityManager.flush();
		} catch (PersistenceException e) {
			throw new DAOException(e);
		}
	}

	// protected long getSize(Query query) {
	// ScrollableResults src = null;
	//
	// try {
	// src = query.scroll();
	// src.last();
	// return src.getRowNumber() + 1;
	// } finally {
	// if (src != null)
	// src.close();
	// }
	// }

	public Long countRows(Filter filter) throws DAOException {

		try {

			Query query = entityManager.createQuery("SELECT COUNT(o) FROM " + this.getClazz().getSimpleName() + " o " + ((filter != null) ? filter.getConditionsStr() : ""));

			if (filter != null) {
				for (Condition condition : ((Filter) filter).getConditions()) {
					if (condition.getOperator() == Operator.IN) {
						query.setParameter(condition.getParam(), (Collection<?>) condition.getValue());
					} else if (condition.getParam() != null) {
						query.setParameter(condition.getParam(), condition.getValue());
						if (condition.getOperator() == Operator.BETWEEN) {
							query.setParameter(condition.getParamEnd(), condition.getValueEnd());
						}
					}
				}

			}

			return (Long) query.getSingleResult();

		} catch (PersistenceException e) {
			throw new DAOException(e);
		}
	}

	public T get(Long id) throws DAOException {

		try {
			return (T) entityManager.find(getClazz(), id);
		} catch (PersistenceException e) {
			throw new DAOException(e);
		}

	}

	@SuppressWarnings("unchecked")
	public T get(Filter filter) throws DAOException {

		try {

			String hql = "SELECT o FROM " + this.getClazz().getSimpleName() + " o " + filter.getConditionsStr() + filter.getOrderByStr();

			Query query = entityManager.createQuery(hql);

			for (Condition condition : ((Filter) filter).getConditions()) {
				if (condition.getOperator() == Operator.IN) {
					query.setParameter(condition.getParam(), (Collection<?>) condition.getValue());
				} else if (condition.getParam() != null) {
					query.setParameter(condition.getParam(), condition.getValue());
					if (condition.getOperator() == Operator.BETWEEN) {
						query.setParameter(condition.getParamEnd(), condition.getValueEnd());
					}
				}
			}

			return (T) query.getSingleResult();

		} catch (NonUniqueResultException e) {
			throw new DAOException();
		} catch (NoResultException e) {
			return null;
		} catch (PersistenceException e) {
			throw new DAOException(e);
		}

	}

	public List<T> list() throws DAOException {
		return list(0, 0, null).getList();
	}

	public List<T> list(Filter filter) throws DAOException {
		return list(0, 0, filter).getList();
	}

	@SuppressWarnings("unchecked")
	public ResultQuery<T> list(int max, int currentPage, Filter filter) throws DAOException {

		try {

			Query query = entityManager.createQuery("SELECT o FROM " + this.getClazz().getSimpleName() + " o " + ((filter != null) ? filter.getConditionsStr() + filter.getOrderByStr() : ""));

			if (filter != null) {
				for (Condition condition : ((Filter) filter).getConditions()) {
					if (condition.getOperator() == Operator.IN) {
						query.setParameter(condition.getParam(), (Collection<?>) condition.getValue());
					} else if (condition.getParam() != null) {
						query.setParameter(condition.getParam(), condition.getValue());
						if (condition.getOperator() == Operator.BETWEEN) {
							query.setParameter(condition.getParamEnd(), condition.getValueEnd());
						}
					}
				}
			}

			long size = 0;
			if (max > 0) {
				size = 0;
				query.setMaxResults(max);
			}
			if (currentPage > 0)
				query.setFirstResult(currentPage);

			return new ResultQuery<T>(size, query.getResultList());

		} catch (PersistenceException e) {
			throw new DAOException(e);
		}

	}

	public void saveOrUpdate(List<T> list) throws DAOException {
		try {
			for (T t : list)
				entityManager.merge(t);

			entityManager.flush();
		} catch (PersistenceException e) {
			throw new DAOException(e);
		}
	}

	public void saveOrUpdate(T t) throws DAOException {
		try {
			entityManager.getTransaction().begin();

			entityManager.merge(t);
			entityManager.flush();

			entityManager.getTransaction().commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			throw new DAOException(e);
		}
	}

	public Boolean isExist(Filter filter) throws DAOException {

		try {

			Query query = entityManager.createQuery("SELECT COUNT(o) FROM " + this.getClazz().getSimpleName() + " o " + ((filter != null) ? filter.getConditionsStr() : ""));

			if (filter != null) {
				for (Condition condition : filter.getConditions()) {
					if (condition.getOperator() == Operator.IN) {
						query.setParameter(condition.getParam(), (Collection<?>) condition.getValue());
					} else if (condition.getParam() != null) {
						query.setParameter(condition.getParam(), condition.getValue());
						if (condition.getOperator() == Operator.BETWEEN) {
							query.setParameter(condition.getParamEnd(), condition.getValueEnd());
						}
					}
				}

			}

			return ((Long) query.getSingleResult() > 0);

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

}
