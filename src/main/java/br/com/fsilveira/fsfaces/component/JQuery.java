package br.com.fsilveira.fsfaces.component;

import java.io.IOException;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

@FacesComponent(value = "br.com.fsilveira.JQuery")
public class JQuery extends UIComponentBase {

	public static final String COMPONENT_TYPE = "br.com.fsilveira.JQuery";
	public static final String COMPONENT_FAMILY = "br.com.fsilveira.JQuery";

	public JQuery() {
		super();
		setRendererType("javax.faces.Text");
	}

	@Override
	public void encodeEnd(FacesContext facesContext) throws IOException {

		ResponseWriter responseWriter = facesContext.getResponseWriter();
		String clientId = getClientId(facesContext);
		responseWriter.startElement("span", this);

		String value = clientId;
		if (null != value && value.length() > 0) {
			responseWriter.writeAttribute("id", value, null);
		}

		responseWriter.writeAttribute("style", "display: none;", null);
		responseWriter.startElement("script", this);
		responseWriter.writeAttribute("type", "text/javascript", null);

		Object text = "jQuery(function($jQuery){ $jQuery('"+getSelector()+"')."+getQuery()+"; });";
		if (text != null) {
			responseWriter.writeText(text, null);
		}

		responseWriter.endElement("script");
		responseWriter.endElement("span");

	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	protected enum Properties {
		event, name, query, selector;
	}

	public String getEvent() {
		String value = (String) getStateHelper().eval(Properties.event);
		return value;
	}

	public void setEvent(String event) {
		getStateHelper().put(Properties.event, event);
	}

	public String getName() {
		String value = (String) getStateHelper().eval(Properties.name);
		return value;
	}

	public void setName(String name) {
		getStateHelper().put(Properties.name, name);
	}

	public String getQuery() {
		String value = (String) getStateHelper().eval(Properties.query);
		return value;
	}

	public void setQuery(String query) {
		getStateHelper().put(Properties.query, query);
	}

	public String getSelector() {
		String value = (String) getStateHelper().eval(Properties.selector);
		return value;
	}

	public void setSelector(String selector) {
		getStateHelper().put(Properties.selector, selector);
	}

}
