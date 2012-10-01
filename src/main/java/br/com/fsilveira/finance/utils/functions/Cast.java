package br.com.fsilveira.finance.utils.functions;

import br.com.fsilveira.finance.utils.enums.Type;


public class Cast implements Function {

	private String field;
	private Object value;
	private String hql;

	public Cast(String field, Type type) {
		this.field = field;
		hql = " CAST(alias." + field + " AS " + type + ") ";
	}
	
	public Cast(Object value, Type type) {
		this.value = value;
		hql = " CAST(alias." + field + " AS " + type + ") ";
	}

	public Cast(Function function, Type type) {
		this(function.toHQL(), type);
	}

	@Override
	public String getField() {
		return field;
	}

	@Override
	public String toHQL() {
		return hql;
	}

	@Override
	public Object getValue() {
		return value;
	}
}
