package br.com.fsilveira.finance.context.listeners;

import java.io.IOException;

import javax.faces.FacesException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.apache.commons.lang.StringUtils;

@SuppressWarnings("serial")
public class SecurityPhaseListener implements PhaseListener {

	public void afterPhase(PhaseEvent event) {
		FacesContext fc = event.getFacesContext();

		String loginPage = (String) fc.getExternalContext().getRequestMap().get("br.com.fsilveira.finance.authenticator.login");
		if (StringUtils.isNotBlank(loginPage)) {
			doRedirect(fc, loginPage);
		}

	}

	public void beforePhase(PhaseEvent event) {

	}

	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;

	}

	/**
	 * Does a regular or ajax redirect.
	 */
	public void doRedirect(FacesContext fc, String redirectPage) throws FacesException {
		ExternalContext ec = fc.getExternalContext();

		try {
			ec.redirect(ec.getRequestContextPath() + (redirectPage != null ? redirectPage : ""));
		} catch (IOException e) {
			throw new FacesException(e);
		}
	}
}