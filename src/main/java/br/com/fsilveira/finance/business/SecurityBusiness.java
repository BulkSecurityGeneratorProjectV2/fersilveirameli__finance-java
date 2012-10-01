package br.com.fsilveira.finance.business;

import javax.inject.Inject;

import br.com.fsilveira.finance.context.security.SecurityManagerBean;
import br.com.fsilveira.finance.dao.AbstractDAO;
import br.com.fsilveira.finance.dao.DAOException;
import br.com.fsilveira.finance.dao.UsuarioDAO;
import br.com.fsilveira.finance.entity.Usuario;
import br.com.fsilveira.finance.utils.Filter;


public class SecurityBusiness extends AbstractBusiness<Usuario> {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDAO dao;

	@Inject
	private SecurityManagerBean securityManagerBean;

	@Override
	protected AbstractDAO<Usuario> getDao() {
		return dao;
	}

	public void authenticate(String email, String senha) throws DAOException, BusinessException {
		Usuario usuario = dao.get(new Filter().addEqualTo("email", email));

		if (usuario == null)
			throw new BusinessException("Usuário não autorizado!");

		if (!usuario.getSenha().equals(senha))
			throw new BusinessException("Usuário não autorizado!");

		securityManagerBean.setUsuario(usuario);
	}

}
