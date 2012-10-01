package br.com.fsilveira.finance.faces.utils.converters;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import br.com.fsilveira.finance.faces.utils.messagesI18N.Messages;

@RequestScoped
@FacesConverter(value = "timeSecondConverter")
public class TimeSecondConverter implements Converter {

	private final String dateFormat = "HH:mm:ss";

	@Inject
	private Messages messages;

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		try {
			if (StringUtils.isEmpty(value) || value.equals("__:__"))
				return null;

			DateTimeFormatter fmt = DateTimeFormat.forPattern(dateFormat);
			return fmt.parseDateTime(value);
		} catch (Exception e) {
			messages.addMessage(FacesMessage.SEVERITY_ERROR, "mensagens.genericas.hora-invalida");
			return null;
		}
	}

	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if (object == null)
			return "";
		if (object instanceof DateTime) {
			return ((DateTime) object).toString(dateFormat);
		}
		return "";
	}

}
