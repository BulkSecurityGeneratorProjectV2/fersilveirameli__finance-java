package br.com.fsilveira.finance.faces.utils.converters.entity;

import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.fsilveira.finance.entity.AbstractEntity;

@RequestScoped
@FacesConverter(value = "beanConverter")
public class BeanConverter implements Converter {

	protected void addAttribute(UIComponent component, AbstractEntity bean) {
		String key = String.valueOf(bean.getId());
		this.getAttributesFrom(component).put(key, bean);
	}

	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		if (value != null) {
			return this.getAttributesFrom(component).get(value);
		}
		return null;
	}

	public String getAsString(FacesContext ctx, UIComponent component, Object value) {
		if (value != null && !"".equals(value)) {
			AbstractEntity entity = (AbstractEntity) value;
			// adiciona item como atributo do componente
			this.addAttribute(component, entity);
			Long id = entity.getId();
			if (id != null) {
				return String.valueOf(id);
			}
		}
		// return (String) value;
		return "";
	}

	protected Map<String, Object> getAttributesFrom(UIComponent component) {
		return component.getAttributes();
	}

}
