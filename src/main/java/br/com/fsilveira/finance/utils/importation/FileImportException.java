package br.com.fsilveira.finance.utils.importation;

import java.util.Collection;

import br.com.fsilveira.finance.faces.utils.messagesI18N.AbstractException;
import br.com.fsilveira.finance.faces.utils.messagesI18N.MessageKey;

public class FileImportException extends AbstractException {

	private static final long serialVersionUID = 1L;

	public FileImportException(MessageKey message) {
		super(message);
	}

	public FileImportException(MessageKey message, Collection<MessageKey> messages) {
		super(message, messages);
	}

	public FileImportException(MessageKey message, Throwable throwable) {
		super(message, throwable);
	}

	public FileImportException(MessageKey message, Throwable throwable, Collection<MessageKey> details) {
		super(message, throwable, details);

	}

	public FileImportException(Throwable causa) {
		super(causa);
	}

}
