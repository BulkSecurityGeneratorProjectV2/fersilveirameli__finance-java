package br.com.fsilveira.finance.faces;

import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.fsilveira.finance.business.BusinessException;
import br.com.fsilveira.finance.business.CategoriaBusiness;
import br.com.fsilveira.finance.business.CedenteSacadoBusiness;
import br.com.fsilveira.finance.business.ContaBusiness;
import br.com.fsilveira.finance.dao.DAOException;
import br.com.fsilveira.finance.entity.Categoria;
import br.com.fsilveira.finance.entity.CedenteSacado;
import br.com.fsilveira.finance.entity.Conta;

@Named
@ViewScoped
public class ListUtilsBean extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private CedenteSacadoBusiness cedenteSacadoBusiness;
	@Inject
	private CategoriaBusiness categoriaBusiness;
	@Inject
	private ContaBusiness contaBusiness;

	private List<CedenteSacado> cedenteSacados;
	private List<Categoria> categorias;
	private List<Conta> contas;

	public List<CedenteSacado> getCedenteSacados() {
		try {
			if (this.cedenteSacados == null)
				this.cedenteSacados = cedenteSacadoBusiness.list();
			return cedenteSacados;
		} catch (BusinessException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void setCedenteSacados(List<CedenteSacado> cedenteSacados) {
		this.cedenteSacados = cedenteSacados;
	}

	public List<Categoria> getCategorias() {
		try {
			if (this.categorias == null)
				this.categorias = categoriaBusiness.list();
			return categorias;
		} catch (BusinessException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<Conta> getContas() {
		try {
			if (this.contas == null)
				this.contas = contaBusiness.list();
			return contas;
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

}
