package br.com.fsilveira.fsfaces.component;

import java.io.IOException;

import javax.faces.component.FacesComponent;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UINamingContainer;
import javax.faces.component.UISelectOne;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

@FacesComponent(value = "br.com.fsilveira.OutputLabel")
public class OutputLabel extends HtmlOutputLabel {

	public static final String COMPONENT_TYPE = "br.com.fsilveira.OutputLabel";

	private String requiredMarker = "*";
	private String requiredClass = "required";
	private String requiredStyle;
	private UIComponent forUIComponent;

	public OutputLabel() {
		super();
	}

	@Override
	public void encodeBegin(final FacesContext context) throws IOException {

		super.encodeBegin(context);

		if ((forUIComponent instanceof UIInput) && ((UIInput) forUIComponent).isRequired())
			writerSpan(context);

		else if ((forUIComponent instanceof UISelectOne) && ((UISelectOne) forUIComponent).isRequired())
			writerSpan(context);

	}

	private void writerSpan(FacesContext context) throws IOException {

		ResponseWriter writer = context.getResponseWriter();
		writer.write(" ");
		writer.startElement("span", null);
		writer.writeAttribute("id", getClientId(), null);

		if (getRequiredClass() != null)
			writer.writeAttribute("class", getRequiredClass(), "requiredClass");

		if (getRequiredStyle() != null)
			writer.writeAttribute("style", getRequiredStyle(), "requiredStyle");

		writer.writeText(getRequiredMarker(), "requiredMarker");
		writer.write(" ");
		writer.endElement("span");
	}

	protected String getForComponentClientId(final FacesContext context, final String forName) {
		UIComponent parent = getParent();

		while (parent != null) {
			if (parent instanceof NamingContainer) {
				break;
			}

			parent = parent.getParent();
		}

		if (parent == null) {
			return null;
		}

		return parent.getClientId(context) + UINamingContainer.getSeparatorChar(context) + forName;
	}

	public String getRequiredMarker() {
		return requiredMarker;
	}

	public void setRequiredMarker(final String requiredMarker) {
		this.requiredMarker = requiredMarker;
	}

	public String getRequiredClass() {
		return requiredClass;
	}

	public void setRequiredClass(final String requiredClass) {
		this.requiredClass = requiredClass;
	}

	public String getRequiredStyle() {
		return requiredStyle;
	}

	public void setRequiredStyle(final String requiredStyle) {
		this.requiredStyle = requiredStyle;
	}

	public UIComponent getForUIComponent() {
		return forUIComponent;
	}

	public void setForUIComponent(UIComponent forUIComponent) {
		this.forUIComponent = forUIComponent;
	}
}
