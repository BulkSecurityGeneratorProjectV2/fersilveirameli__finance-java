package br.com.fsilveira.finance.faces.utils.converters;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@RequestScoped
@FacesConverter(value = "booleanConverter")
public class BooleanConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Boolean bol = null;
		if (value != null && !value.equals("") && !value.equalsIgnoreCase("todos"))
			bol = value.toLowerCase().contains("sim");
		return bol;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String str = null;
		if (value != null && !value.equals("todos"))
			str = ((Boolean) value) ? "Sim" : "N�o";
		return str;
	}

}
