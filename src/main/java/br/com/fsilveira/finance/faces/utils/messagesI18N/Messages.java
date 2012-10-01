package br.com.fsilveira.finance.faces.utils.messagesI18N;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class Messages {

	public static Severity DEFAULT_MESSAGE_SEVERITY = FacesMessage.SEVERITY_INFO;
	public static Severity DEFAULT_EXCEPTION_SEVERITY = FacesMessage.SEVERITY_ERROR;

	@Inject
	private FacesContext facesContext;

	public void addMessage(MessageKey message) {
		addMessage(DEFAULT_MESSAGE_SEVERITY, message);
	}

	public void addMessage(Severity severity, MessageKey message) {
		addMessage(null, severity, message);
	}

	public void addMessage(Severity severity) {
		if (severity.equals(FacesMessage.SEVERITY_ERROR))
			addMessage(null, severity, MessageKey.GENERIC_ERROR);
		else
			addMessage(null, severity, null);
	}
	
	

	public void addMessage(String id, MessageKey message) {
		addMessage(id, DEFAULT_MESSAGE_SEVERITY, message);
	}

	public void addMessage(String clientId, Severity severity, MessageKey message) {
		if (message == null) {
			facesContext.addMessage(clientId, new FacesMessage(severity, "Unknow", "Unknow"));
		} else {
			//facesContext.addMessage(clientId, new FacesMessage(severity, Bundle.getInstance().getMessageResourceString(message.getKey(), message.getParameters()), null));
			facesContext.addMessage(clientId, new FacesMessage(severity, message.getKey(), null));
		}
	}

	public void addMessage(String message, Object... params) {
		addMessage(null, DEFAULT_MESSAGE_SEVERITY, new MessageKey(message, params));
	}

	public void addMessage(Severity severity, String message, Object... params) {
		addMessage(null, severity, message, params);
	}

	public void addMessage(String id, String message, Object... params) {
		addMessage(id, DEFAULT_MESSAGE_SEVERITY, message, params);
	}

	public void addExceptionMessage(AbstractException exception) {
		addExceptionMessage(exception.getSeverity(), exception);
	}

	public void addExceptionMessage(Severity severity, AbstractException exception) {
		addExceptionMessage(null, severity, exception);
	}

	public void addExceptionMessage(String id, AbstractException exception) {
		addExceptionMessage(id, DEFAULT_EXCEPTION_SEVERITY, exception);
	}

	public void addExceptionMessage(String id, Severity severity, AbstractException exception) {
		if (exception.hasDetails()) {
			for (MessageKey message : exception.getDetails()) {
				addMessage(id, severity, message);
			}
		} else {
			addMessage(id, severity, exception.getMessageKey());
		}
		exception.printStackTrace();
	}

}
