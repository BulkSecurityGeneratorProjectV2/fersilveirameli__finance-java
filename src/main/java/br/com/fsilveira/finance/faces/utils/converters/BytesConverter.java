package br.com.fsilveira.finance.faces.utils.converters;

import java.text.DecimalFormat;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@RequestScoped
@FacesConverter(value = "bytesConverter")
public class BytesConverter implements Converter {

	static final double BYTES = 1;
	static final double KILO_BYTES = 1024;
	static final double MEGA_BYTES = 1048576;
	static final double GIGA_BYTES = 1073741824;

	private DecimalFormat decimalFormat;

	public BytesConverter() {
		this.decimalFormat = new DecimalFormat("0.00");
	}

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Integer integer = null;
		try {
			integer = Integer.parseInt(value);
			return integer;
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if (object == null)
			return "";

		double bytes = ((Long) object).doubleValue();
		if (bytes < KILO_BYTES)
			return decimalFormat.format(bytes) + " Bytes";
		if (bytes < MEGA_BYTES)
			return decimalFormat.format((bytes / 1024)) + " Kb";
		if (bytes < GIGA_BYTES)
			return decimalFormat.format(((bytes / 1024) / 1024)) + " Mb";

		return "";
	}

}
