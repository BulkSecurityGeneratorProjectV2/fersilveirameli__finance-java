package br.com.fsilveira.finance.faces.utils.validators;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@RequestScoped
@FacesValidator(value = "numericValidator")
public class NumericValidator implements Validator, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public void validate(final FacesContext context, final UIComponent component, final Object value) throws ValidatorException {
		if (value != null) {
//			try {
//				UtilsValidator.validateNumeric(String.valueOf(value));
//			} catch (ValidateException e) {
//				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), null));
//			}
		}
	}
}