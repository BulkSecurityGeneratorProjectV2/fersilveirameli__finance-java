package br.com.fsilveira.finance.faces.utils.converters.entity;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;

import br.com.fsilveira.finance.business.AbstractBusiness;
import br.com.fsilveira.finance.business.BusinessException;
import br.com.fsilveira.finance.dao.DAOException;
import br.com.fsilveira.finance.entity.AbstractEntity;
import br.com.fsilveira.finance.faces.utils.messagesI18N.Messages;

@RequestScoped
public abstract class AbstractEntityConverter implements Converter {

	@Inject
	private Messages messages;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String string) {
		if (string == null || string.isEmpty()) {
			return null;
		}
		try {
			return getBusiness().get(Long.valueOf(string));

		} catch (NumberFormatException e) {
			e.printStackTrace();
			messages.addMessage(FacesMessage.SEVERITY_ERROR);
		} catch (BusinessException e) {
			messages.addExceptionMessage(e);
		} catch (DAOException e) {
			messages.addExceptionMessage(e);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		AbstractEntity abstractEntity = (AbstractEntity) object;
		if (abstractEntity == null || abstractEntity.getId() == null) {
			return null;
		}
		return String.valueOf(abstractEntity.getId());
	}

	public abstract AbstractBusiness<?> getBusiness();

}
