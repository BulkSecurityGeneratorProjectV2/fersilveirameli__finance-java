package br.com.fsilveira.finance.entity;

import java.io.Serializable;

public abstract class AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		// Evita problema com proxies do Hibernate
		// if (getClass() != obj.getClass()) {
//		if (Hibernate.getClass(this) != Hibernate.getClass(obj)) {
//			return false;
//		}
		AbstractBean other = (AbstractBean) obj;
		if (getId() == null) {
			if (other.getId() != null) {
				return false;
			}
		} else if (!getId().equals(other.getId())) {
			return false;
		}
		return true;
	}

	public abstract Long getId();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	public abstract void setId(Long id);

	

}
