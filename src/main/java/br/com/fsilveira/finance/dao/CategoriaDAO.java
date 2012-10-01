package br.com.fsilveira.finance.dao;

import br.com.fsilveira.finance.entity.Categoria;


public class CategoriaDAO extends AbstractDAO<Categoria> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<Categoria> getClazz() {
		return Categoria.class;
	}

}
