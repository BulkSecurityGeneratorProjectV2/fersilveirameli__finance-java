package br.com.fsilveira.finance.faces;

import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import br.com.fsilveira.finance.business.Month;

@Named
@ViewScoped
public class UtilsBean extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<Month> getMonths() {
		return Month.getList();
	}

}
