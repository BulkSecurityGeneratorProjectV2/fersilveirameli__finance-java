package br.com.fsilveira.finance.utils.functions;


public class SubString implements Function {

	private String hql;
	private String field;
	private Object value;

	public SubString(String field, int init, int end) {
		this.field = field;
		hql = "SUBSTRING(alias." + field + ", " + init + ", " + end + ")";
	}

	public SubString(String field, int init) {
		this.field = field;
		hql = "SUBSTRING(alias." + field + ", " + init + ")";
	}

	public SubString(Function function, int init, int end) {
		this(function.toHQL(), init, end);
	}

	public SubString(Function function, int init) {
		this(function.toHQL(), init);
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
