package br.com.fsilveira.finance.faces.utils.messagesI18N;

import java.util.Arrays;

public class MessageKey {

	public static final MessageKey GENERIC_SAVE_SUCCESS = new MessageKey("mensagens.genericas.cadastro-sucesso");
	public static final MessageKey GENERIC_UPDATE_SUCCESS = new MessageKey("mensagens.genericas.editar-sucesso");
	public static final MessageKey GENERIC_REMOVE_SUCCESS = new MessageKey("mensagens.genericas.remover-sucesso");
	public static final MessageKey GENERIC_ERROR = new MessageKey("Ocorreu um erro imprevisto. Contate nossa Central de Atendimento (siga-channel@jexperts.com.br)");

	private String key;
	private Object[] parameters;

	public MessageKey(final String key, final Object... parameters) {
		if (key == null)
			throw new IllegalArgumentException("key is null");

		this.key = key;
		this.parameters = parameters;
	}

	public boolean hasParameters() {
		return parameters.length > 0;
	}

	public Object[] getParameters() {
		return Arrays.copyOf(parameters, parameters.length);
	}

	@Override
	public String toString() {
		return String.format("Mensagem [chave=%s, parametros=%s]", key, Arrays.toString(parameters));
	}

	public String getKey() {
		return key;
	}
}
