package br.com.fsilveira.finance.context.security;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.fsilveira.finance.entity.Usuario;
import br.com.fsilveira.finance.faces.AbstractBean;

@SuppressWarnings("serial")
@Named("security")
@SessionScoped
public class SecurityManagerBean extends AbstractBean implements Serializable {

	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	// public boolean hasPermission(String transaction) {
	//
	// try {
	//
	// return TransacaoBusiness.getInstance().contaisTransacao(sessionContextMB.getUserSession().getPerfil(), transaction);
	//
	// } catch (NullPointerException e) {
	// e.printStackTrace();
	// getMessages().addMessage(FacesMessage.SEVERITY_ERROR);
	//
	// } catch (DAOException e) {
	// getMessages().addExceptionMessage(e);
	// } catch (ResultadoNaoUnicoException e) {
	// getMessages().addExceptionMessage(e);
	// }
	// return false;
	// }

}
