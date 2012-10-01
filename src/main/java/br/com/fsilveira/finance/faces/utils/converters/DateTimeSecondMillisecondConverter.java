package br.com.fsilveira.finance.faces.utils.converters;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@RequestScoped
@FacesConverter(value = "dateTimeSecondMillisecondConverter")
public class DateTimeSecondMillisecondConverter implements Converter {

	private final String dateFormat = "dd/MM/yyyy HH:mm:ss:SSS";

	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		try {
			if (StringUtils.isEmpty(value) || value.equals("__:__"))
				return null;

			DateTimeFormatter fmt = DateTimeFormat.forPattern(dateFormat);
			return fmt.parseDateTime(value);
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
		return "";
	}

}
