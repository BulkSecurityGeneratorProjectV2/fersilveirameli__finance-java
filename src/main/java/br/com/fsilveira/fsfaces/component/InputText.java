package br.com.fsilveira.fsfaces.component;

import java.io.IOException;

import javax.el.ValueExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;

import org.apache.commons.lang.StringUtils;


@FacesComponent(value = "br.com.fsilveira.InputText")
@ResourceDependencies({ 
	//@ResourceDependency(name = "jquery-1.7.2.min.js", target = "head", library = "js"),
		@ResourceDependency(name = "jquery.maskedinput-1.3.min.js", target = "head", library = "js"), @ResourceDependency(name = "jsf.js", target = "head", library = "javax.faces") })
public class InputText extends HtmlInputText {

	private String mask;
	private Boolean showMessage;
	//private AjaxBehavior ajaxBehavior;

	// private void createAjaxBehaviorMessage(final FacesContext context) throws
	// IOException {
	// ajaxBehavior = new AjaxBehavior();
	// addClientBehavior("blur", ajaxBehavior);
	// }

	@Override
	public void encodeBegin(final FacesContext context) throws IOException {
		super.encodeBegin(context);
		encodeOutputLabel(context);
		// createAjaxBehaviorMessage(context);
		if (isShowMessage()) {
			encodeMessage(context);
		}
		encodeMask(context);

	}

	@Override
	public void encodeEnd(final FacesContext context) throws IOException {
		super.encodeEnd(context);

	}

	public void encodeMask(final FacesContext context) throws IOException {
		if (StringUtils.isNotEmpty(getMask())) {

			final JQuery jquery = (JQuery) context.getApplication().createComponent(JQuery.COMPONENT_TYPE);
			jquery.setSelector("#" + getClientId(context).replace(":", "\\\\:"));
			jquery.setQuery("mask('" + getMask() + "')");
			jquery.encodeAll(context);

		}
	}

	private void encodeMessage(final FacesContext context) throws IOException {
		String idComponentMessage = context.getViewRoot().createUniqueId();
		final HtmlMessage message = (HtmlMessage) context.getApplication().createComponent(HtmlMessage.COMPONENT_TYPE);
		message.setFor(getClientId(context));
		message.setId(idComponentMessage);
		message.setStyleClass("sg-message");
		message.setInfoClass("sg-message-info");
		message.setErrorClass("sg-message-error");
		message.setWarnClass("sg-message-warn");
		message.encodeAll(context);

		// ajaxBehavior.setRender(Arrays.asList(idComponentMessage));

	}

	private void encodeOutputLabel(final FacesContext context) throws IOException {
		if (StringUtils.isNotEmpty(getLabel())) {

			OutputLabel label = (OutputLabel) context.getApplication().createComponent(OutputLabel.COMPONENT_TYPE);

			// OutputLabelUI label = (OutputLabelUI)
			// context.getApplication().createComponent(OutputLabelUI.COMPONENT_TYPE);
			label.setValue(getLabel());
			// label.setParent(getParent());
			label.setForUIComponent(this);
			label.setFor(getClientId(context));
			// label.setId(context.getViewRoot().createUniqueId());
			label.encodeAll(context);

		}
	}

	public String getMask() {
		if (mask != null) {
			return mask;
		}

		final ValueExpression ve = getValueExpression("mask");

		if (ve == null) {
			return null;
		} else {
			return (String) ve.getValue(getFacesContext().getELContext());
		}
	}

	public Validator getValidator(final Class<? extends Validator> clazz) {
		for (final Validator validator : getValidators()) {
			if (clazz.isInstance(validator)) {
				return validator;
			}
		}
		return null;
	}

	public boolean hasValidator(final Class<? extends Validator> clazz) {
		return (getValidator(clazz) != null);
	}

	public boolean isShowMessage() {
		if (showMessage != null)
			return showMessage.booleanValue();

		final ValueExpression ve = getValueExpression("showMessage");

		if (ve == null)
			return false;
		else
			return Boolean.TRUE.equals(ve.getValue(getFacesContext().getELContext()));

	}

	public void removeValidator(final Class<? extends Validator> clazz) {
		final Validator validator = getValidator(clazz);

		if (validator != null) {
			removeValidator(validator);
		}
	}

	@Override
	public void restoreState(final FacesContext context, final Object state) {
		final Object[] states = (Object[]) state;

		super.restoreState(context, states[0]);
		mask = (String) states[1];
		showMessage = (Boolean) states[2];
	}

	@Override
	public Object[] saveState(final FacesContext context) {
		final Object[] state = new Object[3];

		state[0] = super.saveState(context);
		state[1] = mask;
		state[2] = showMessage;

		return state;
	}

	public void setMask(final String mask) {
		this.mask = mask;
	}

	public void setShowMessage(final boolean showMessage) {
		this.showMessage = Boolean.valueOf(showMessage);
	}

	@Override
	public void setValue(final Object value) {
		if (value instanceof String) {
			final String valueTrim = ((String) value).trim().replaceAll("[\\s]{2,}", " ");

			if (valueTrim.isEmpty()) {
				super.setValue(null);
			} else {
				super.setValue(valueTrim);
			}
		} else {
			super.setValue(value);
		}
	}

}
