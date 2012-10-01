package br.com.fsilveira.fsfaces.component;

import java.io.IOException;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;

@FacesComponent(value = "br.com.fsilveira.InputNumeric")
@ResourceDependencies({ @ResourceDependency(name = "jquery.alphanumeric.js", target = "head", library = "js") })
public class InputNumeric extends InputText {

	public void encodeBegin(final FacesContext context) throws IOException {
		super.encodeBegin(context);
		encodeNumeric(context);
		
//		if (!hasValidator(NumericValidator.class)) {
//			addValidator((NumericValidator) ComponentCDI.getInstance().getBeanManager(NumericValidator.class));
//		}

	}

	@Override
	public void encodeEnd(FacesContext context) throws IOException {
		super.encodeEnd(context);
		
	}

	public void encodeNumeric(final FacesContext context) throws IOException {

		JQuery jquery = (JQuery) context.getApplication().createComponent(JQuery.COMPONENT_TYPE);
		jquery.setSelector("#" + getClientId(context).replace(":", "\\\\:"));
		jquery.setQuery("numeric()");
		jquery.encodeAll(context);		
		

	}
}
