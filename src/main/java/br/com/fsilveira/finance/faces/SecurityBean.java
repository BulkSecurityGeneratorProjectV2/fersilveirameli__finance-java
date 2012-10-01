package br.com.fsilveira.finance.faces;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.fsilveira.finance.business.BusinessException;
import br.com.fsilveira.finance.business.SecurityBusiness;
import br.com.fsilveira.finance.dao.DAOException;

@Named
@ViewScoped
public class SecurityBean extends AbstractBean  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	private String senha;

	@Inject
	private SecurityBusiness business;

	public String authenticate() {
		try {
			business.authenticate(email, senha);
			return "success";
		} catch (DAOException e) {
			messages.addExceptionMessage(e);
		} catch (BusinessException e) {
			messages.addExceptionMessage(e);
		}
		return "failed";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
