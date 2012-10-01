package br.com.fsilveira.finance.business;

import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;

import br.com.fsilveira.finance.faces.utils.messagesI18N.AbstractException;
import br.com.fsilveira.finance.faces.utils.messagesI18N.MessageKey;

public class BusinessException extends AbstractException {

	private static final long serialVersionUID = 1L;

	protected BusinessException(String message) {
		super(message);
	}

	protected BusinessException(MessageKey message) {
		super(message);
	}

	protected BusinessException(MessageKey message, Collection<MessageKey> messages) {
		super(message, messages);
	}

	protected BusinessException(MessageKey message, Throwable throwable) {
		super(message, throwable);
	}

	protected BusinessException(MessageKey message, Throwable throwable, Collection<MessageKey> details) {
		super(message, throwable, details);

	}

	protected BusinessException(Throwable causa) {
		super(causa);
	}

	public Severity getSeverity() {
		return FacesMessage.SEVERITY_WARN;
	}

	@Override
	public void printStackTrace() {

	}

}
