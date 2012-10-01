package br.com.fsilveira.finance.business;

import javax.inject.Inject;

import br.com.fsilveira.finance.dao.AbstractDAO;
import br.com.fsilveira.finance.dao.CategoriaDAO;
import br.com.fsilveira.finance.entity.Categoria;

@SuppressWarnings("serial")

public class CategoriaBusiness extends AbstractBusiness<Categoria>{
	
	@Inject
	private CategoriaDAO dao;


	@Override
	protected AbstractDAO<Categoria> getDao() {
		return dao;
	}

	

}
