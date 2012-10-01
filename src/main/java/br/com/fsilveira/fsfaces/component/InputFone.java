package br.com.fsilveira.fsfaces.component;

import java.io.IOException;

import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;

@FacesComponent(value = "br.com.fsilveira.InputFone")
public class InputFone extends InputText {

	public void encodeBegin(final FacesContext context) throws IOException {
		setMask("(99) 9999-9999");

		super.encodeBegin(context);

		// FIXME: validar ffone
		// /* adicionando validadores */
		// if (!hasValidator(TelefoneValidator.class)) {
		// addValidator((TelefoneValidator)
		// Component.getInstance(TelefoneValidator.class));
		// }
	}

}
