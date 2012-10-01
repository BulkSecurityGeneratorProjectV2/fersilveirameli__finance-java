package br.com.fsilveira.fsfaces.component;

import java.io.IOException;

import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;

@FacesComponent(value = "br.com.fsilveira.InputTime")
public class InputTime extends InputText {

	public void encodeBegin(final FacesContext context) throws IOException {
		setMask("99:99");
		super.encodeBegin(context);
		//setConverter(new CalendarTimeConverter());
	}
}