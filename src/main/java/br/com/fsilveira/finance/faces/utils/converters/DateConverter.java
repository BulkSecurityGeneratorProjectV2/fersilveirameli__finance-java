package br.com.fsilveira.finance.faces.utils.converters;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@RequestScoped
@FacesConverter(value = "dateConverter")
public class DateConverter implements Converter {

	private final String dateFormat = "dd/MM/yyyy";

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		try {
			if (value == null || value.isEmpty())
				return null;

			DateTimeFormatter fmt = DateTimeFormat.forPattern(dateFormat);
			return fmt.parseLocalDateTime(value);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if (object == null)
			return "";
		if (object instanceof DateTime) {
			return ((DateTime) object).toString(dateFormat);
		}
		if (object instanceof LocalDateTime) {
			return ((LocalDateTime) object).toString(dateFormat);
		}
		return "";
	}

}
