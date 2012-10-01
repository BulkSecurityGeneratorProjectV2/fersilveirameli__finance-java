package br.com.fsilveira.finance.utils.importation;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractImport {

	private List<String> messages;
	private Action action;

	public AbstractImport() {
		this.messages = new ArrayList<String>();
	}

	protected void clear() {
		this.setMessages(new ArrayList<String>());
		this.setAction(Action.IMPORT);
	}

	public abstract void validate();

	protected void addInvalidMessage(String message) {
		this.messages.add(message);
		this.setAction(Action.INVALID);
	}

	protected void addMessage(String message) {
		this.messages.add(message);
	}

	public enum Action {
		IMPORT("geral.textos.importar"), NOT_IMPORT("geral.textos.nao-importar"), DUPLICATED("geral.textos.duplicado"), INVALID("geral.textos.invalido");

		private String key;

		Action(String key) {
			this.key = key;
		}

		public String getKey() {
			return key;
		}
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public List<String> getMessages() {
		return messages;
	}

	public String getMessagesStr() {
		StringBuilder str = new StringBuilder();
		for (String message : messages)
			str.append(message + " ");
		return str.toString();
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

}