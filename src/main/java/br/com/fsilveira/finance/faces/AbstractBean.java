package br.com.fsilveira.finance.faces;

import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import br.com.fsilveira.finance.faces.utils.messagesI18N.Messages;



public abstract class AbstractBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Logger logger;

	@Inject
	protected Messages messages;

	public AbstractBean() {
		this.logger = Logger.getLogger(getClass());
	}

	public Application getApplication() {
		return getFacesContext().getApplication();
	}

	public void redirectToContext() throws IOException {
		getHttpServletResponse().sendRedirect("/finance");
	}

	public Locale getLocale() {
		return getFacesContext().getViewRoot().getLocale();
	}

	public String getContextPath() {
		ServletContext context = (ServletContext) getExternalContext().getContext();
		return context.getContextPath();
	}

	public ExternalContext getExternalContext() {
		return getFacesContext().getExternalContext();
	}

	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	public HttpServletRequest getHttpServletRequest() {
		return (HttpServletRequest) getExternalContext().getRequest();
	}

	public HttpServletResponse getHttpServletResponse() {
		return (HttpServletResponse) getExternalContext().getResponse();
	}

	public void responseComplete() {
		getFacesContext().responseComplete();
	}

	public HttpSession getHttpSession() {
		return (HttpSession) getExternalContext().getSession(true);
	}

	public boolean isSeverityError() {
		if (getFacesContext().getMaximumSeverity() == FacesMessage.SEVERITY_ERROR) {
			return true;
		} else if (getFacesContext().getMaximumSeverity() == FacesMessage.SEVERITY_FATAL) {
			return true;
		}
		return false;
	}

	protected Logger getLogger() {
		return logger;
	}

	public Messages getMessages() {
		return messages;
	}

}
