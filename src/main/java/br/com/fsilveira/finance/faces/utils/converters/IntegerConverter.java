package br.com.fsilveira.finance.faces.utils.converters;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@RequestScoped
@FacesConverter(value = "integerConverter")
public class IntegerConverter implements Converter {

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
		if(object==null)
			return "";
		return object.toString();
	}

}
