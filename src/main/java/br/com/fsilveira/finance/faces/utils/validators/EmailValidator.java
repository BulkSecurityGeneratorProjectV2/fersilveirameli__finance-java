package br.com.fsilveira.finance.faces.utils.validators;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@RequestScoped
@FacesValidator(value = "br.com.jexperts.EmailValidator")
public class EmailValidator implements Validator, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public void validate(final FacesContext context, final UIComponent component, final Object value) throws ValidatorException {

		if (value != null && !((String) value).equals("")) {
//			try {
//				//Utils.validateEmail((String) value);
//			} catch (ValidateException e) {
//				context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_WARN, Bundle.getInstance().getMessageResourceString(e.getKey()), null));
//			}
		}
	}

}
