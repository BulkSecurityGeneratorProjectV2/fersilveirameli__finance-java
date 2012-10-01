package br.com.fsilveira.finance.faces.utils.converters;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import br.com.fsilveira.finance.faces.utils.Bundle;

@RequestScoped
@FacesConverter(value = "localTimeConverter")
public class LocalTimeConverter implements Converter {

	private final String dateFormat = "HH:mm";

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		try {
			if (StringUtils.isEmpty(value) || value.equals("__:__"))
				return null;

			DateTimeFormatter fmt = DateTimeFormat.forPattern(dateFormat);
			return fmt.parseDateTime(value).toLocalTime();
			// FIXME: Ajustar esta exce��o
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(Bundle.getInstance().getMessageResourceString("mensagens.genericas.hora-invalida"));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;
		}
	}

	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if (object == null)
			return "";
		if (object instanceof LocalTime) {
			return ((LocalTime) object).toString(dateFormat);
		}
		return "";
	}

}
