package br.com.fsilveira.finance.dao;

import br.com.fsilveira.finance.entity.Usuario;


public class UsuarioDAO extends AbstractDAO<Usuario> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<Usuario> getClazz() {
		return Usuario.class;
	}

}
