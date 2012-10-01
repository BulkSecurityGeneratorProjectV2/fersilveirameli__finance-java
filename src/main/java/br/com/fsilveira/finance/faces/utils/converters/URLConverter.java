package br.com.fsilveira.finance.faces.utils.converters;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@RequestScoped
@FacesConverter(value = "urlConverter")
public class URLConverter implements Converter {

    public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
	if (value != null)
	    return value;

	return null;
    }

    public String getAsString(FacesContext ctx, UIComponent component, Object value) {

	return "http://" + ((String) value).replace("http://", "");
    }

}
