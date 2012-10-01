package br.com.fsilveira.finance.dao;

import java.util.List;

import javax.persistence.Query;

import org.joda.time.LocalDateTime;

import br.com.fsilveira.finance.entity.ParcelaLancamento;


public class ParcelaLancamentoDAO extends AbstractDAO<ParcelaLancamento> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<ParcelaLancamento> getClazz() {
		return ParcelaLancamento.class;
	}

	@SuppressWarnings("unchecked")
	public List<ParcelaLancamento> listPendentes() {
		Query query = entityManager.createQuery("SELECT p FROM ParcelaLancamento as p WHERE p.dataPgto is null AND p.dataVencimento <= :currentDate ORDER BY p.dataVencimento ASC ");
		query.setParameter("currentDate", new LocalDateTime());
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<ParcelaLancamento> listFuturos() {
		Query query = entityManager.createQuery("SELECT p FROM ParcelaLancamento as p WHERE p.dataPgto is null and p.dataVencimento > :currentDate and p.dataVencimento <= :endDate ORDER BY p.dataVencimento ASC ");

		query.setParameter("currentDate", new LocalDateTime());
		query.setParameter("endDate", new LocalDateTime().plusMonths(1));
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<ParcelaLancamento> listByPeriod(int month, int year) {

		String hql = "SELECT pl FROM ParcelaLancamento pl join pl.lancamento l WHERE month(pl.dataVencimento) = :month AND year(pl.dataVencimento) = :year ORDER BY pl.dataVencimento, l.descricao ASC";

		Query query = entityManager.createQuery(hql);

		query.setParameter("month", month);
		query.setParameter("year", year);
		return query.getResultList();
	}

}
