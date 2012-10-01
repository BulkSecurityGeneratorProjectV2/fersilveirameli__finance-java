package br.com.fsilveira.finance.faces.utils.messagesI18N;

import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;

public abstract class AbstractException extends Exception {

	private static final long serialVersionUID = 1L;

	private MessageKey messageKey;
	private Collection<MessageKey> details;

	protected AbstractException(String message) {
		this((message != null) ? new MessageKey(message) : null, null, null);
	}

	protected AbstractException(MessageKey message) {
		this(message, null, null);
	}

	protected AbstractException(MessageKey messageKey, Collection<MessageKey> messages) {
		this(messageKey, null, messages);
	}

	protected AbstractException(MessageKey messageKey, Throwable throwable) {
		this(messageKey, throwable, null);
	}

	protected AbstractException(MessageKey messageKey, Throwable throwable, Collection<MessageKey> details) {
		super(throwable);

		if (throwable instanceof AbstractException) {
			this.messageKey = ((AbstractException) throwable).getMessageKey();
			this.details = ((AbstractException) throwable).getDetails();
		} else {
			this.messageKey = messageKey;
			this.details = details;
		}
	}

	protected AbstractException(final Throwable causa) {
		this(null, causa, null);
	}

	public final Collection<MessageKey> getDetails() {
		return details;
	}

	public final boolean hasDetails() {
		return details != null && !details.isEmpty();
	}

	public Severity getSeverity() {
		return FacesMessage.SEVERITY_ERROR;
	}

	public String getMessage() {
		if (messageKey == null)
			throw new IllegalArgumentException("message is null");
		return messageKey.getKey();
	}

	public MessageKey getMessageKey() {
		return messageKey;
	}

}