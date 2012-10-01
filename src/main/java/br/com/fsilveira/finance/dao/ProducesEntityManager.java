package br.com.fsilveira.finance.dao;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@ViewScoped
public class ProducesEntityManager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceUnit
	EntityManagerFactory emf;

	private EntityManager entityManager;

	@PostConstruct
	public void init() {
		entityManager = emf.createEntityManager();
	}

	@Produces
	@ViewScopedEntityManager
	public EntityManager getEntityManager() {
		return entityManager;
	}

}
