package br.com.fsilveira.finance.faces.utils.converters;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.joda.time.Duration;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

@RequestScoped
@FacesConverter(value = "durationConverter")
public class DurationTimeConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		try {
			if (value == null || value.isEmpty())
				return null;

			PeriodFormatter hoursMinutes = new PeriodFormatterBuilder().appendHours().appendSeparator(":").appendMinutes().toFormatter();

			return new Duration(hoursMinutes.parsePeriod(value));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if (object == null)
			return "";
		if (object instanceof Duration) {
			String format = String.format("%%0%dd", 2);
			return String.format(format, ((Duration) object).getStandardHours() ) + ":" + String.format(format, ((Duration) object).getStandardMinutes() );
		}
		return "";
	}

}
