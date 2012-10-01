package br.com.fsilveira.fsfaces.component;

import java.io.IOException;

import javax.el.ValueExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.html.HtmlMessage;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;

@FacesComponent(value = "br.com.fsilveira.SelectOneMenu")
@ResourceDependencies({ 
	//@ResourceDependency(name = "jquery-1.7.2.min.js", target = "head", library = "js"), 
	@ResourceDependency(name = "jsf.js", target = "head", library = "javax.faces") })
public class SelectOneMenu extends HtmlSelectOneMenu {

	private Boolean showMessage = Boolean.FALSE;

	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		super.encodeBegin(context);
		encodeOutputLabel(context);
		if (isShowMessage()) {
			encodeMessage(context);
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

	}

	private void encodeOutputLabel(final FacesContext context) throws IOException {
		if (StringUtils.isNotEmpty(getLabel())) {

			OutputLabel label = (OutputLabel) context.getApplication().createComponent(OutputLabel.COMPONENT_TYPE);
			label.setValue(getLabel());
			label.setForUIComponent(this);
			label.setFor(getClientId(context));
			label.encodeAll(context);

		}
	}

	@Override
	public void encodeEnd(FacesContext context) throws IOException {
		super.encodeEnd(context);
		
	}

	@Override
	public Object[] saveState(FacesContext context) {
		Object[] state = new Object[2];

		state[0] = super.saveState(context);
		state[1] = showMessage.booleanValue();

		return state;
	}

	@Override
	public void restoreState(FacesContext context, Object state) {
		Object[] states = (Object[]) state;

		super.restoreState(context, states[0]);
		showMessage = ((Boolean) states[1]).booleanValue();
	}

	public boolean isShowMessage() {
		if (showMessage != null) {
			return showMessage.booleanValue();
		}

		ValueExpression ve = getValueExpression("showMessage");

		if (ve == null) {
			return false;
		} else {
			return Boolean.TRUE.equals(ve.getValue(getFacesContext().getELContext()));
		}
	}

	public void setShowMessage(boolean showMessage) {
		this.showMessage = showMessage;
	}
}