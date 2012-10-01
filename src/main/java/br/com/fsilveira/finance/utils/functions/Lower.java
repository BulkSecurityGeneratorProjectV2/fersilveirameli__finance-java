package br.com.fsilveira.finance.utils.functions;


public class Lower implements Function {

	private String hql;
	private String field;
	private Object value;

	public Lower(String field) {
		this.field = field;
		this.value = field;
		hql = "LOWER(alias." + field + ")";
	}
	
	public Lower(Object value) {
		this.value = value;
	}

	public Lower(Function function) {
		this(function.toHQL());
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
