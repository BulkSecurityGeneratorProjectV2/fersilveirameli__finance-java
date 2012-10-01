package br.com.fsilveira.finance.dao;

import java.util.Collection;

import br.com.fsilveira.finance.faces.utils.messagesI18N.AbstractException;
import br.com.fsilveira.finance.faces.utils.messagesI18N.MessageKey;


public class DAOException extends AbstractException {

	private static final long serialVersionUID = 1L;

	protected DAOException() {
		super(MessageKey.GENERIC_ERROR);
	}

	protected DAOException(MessageKey message) {
		super(message);
	}

	protected DAOException(MessageKey message, Collection<MessageKey> messages) {
		super(message, messages);
	}

	protected DAOException(MessageKey message, Throwable throwable) {
		super(message, throwable);
	}

	protected DAOException(MessageKey message, Throwable throwable, Collection<MessageKey> details) {
		super(message, throwable, details);

	}

	protected DAOException(Throwable causa) {
		super(MessageKey.GENERIC_ERROR, causa);
	}

}
