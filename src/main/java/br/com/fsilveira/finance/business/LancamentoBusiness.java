package br.com.fsilveira.finance.business;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.fsilveira.finance.entity.Lancamento;

@Named
public class LancamentoBusiness {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Lancamento> list() {
		Query query = entityManager.createQuery(" SELECT l FROM Lancamento l");
		return query.getResultList();
	}

}
