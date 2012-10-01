package br.com.fsilveira.fsfaces.component;

import java.io.IOException;

import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;


@FacesComponent(value = "br.com.fsilveira.InputEmail")
public class InputEmail extends InputText {

//	@Inject
//	private EmailValidator validator;

	@Override
	public void encodeBegin(final FacesContext context) throws IOException {
		// if (!hasValidator(EmailValidator.class)) {
		// addValidator((EmailValidator)
		// ComponentCDI.getInstance().getBeanManager(EmailValidator.class));
		// }
		super.encodeBegin(context);
//		if (!hasValidator(EmailValidator.class)) {
//			addValidator((EmailValidator) ComponentCDI.getInstance().getBeanManager(EmailValidator.class));
//		}
	}

	@Override
	public void encodeEnd(FacesContext context) throws IOException {
		super.encodeEnd(context);
		// if (!hasValidator(EmailValidator.class)) {
		// addValidator((EmailValidator)
		// ComponentCDI.getInstance().getBeanManager(EmailValidator.class));
		// }
	}

	@Override
	public void decode(FacesContext context) {
		super.decode(context);
	}
}
