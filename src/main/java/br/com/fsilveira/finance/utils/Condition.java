package br.com.fsilveira.finance.utils;

import java.util.Collection;

import br.com.fsilveira.finance.utils.enums.Operator;
import br.com.fsilveira.finance.utils.functions.Function;

public class Condition {

	private String hql;
	private String field;
	private String paramInit;
	private String paramEnd;
	private Object valueInit;
	private Object valueEnd;
	private Operator operator;

	public Condition(String field, Operator operator, Object value) {
		this.setOperator(operator);
		this.field = field;
		this.paramInit = generateParam(memoryAddress() + field);
		this.valueInit = value;
		this.hql = "alias."+this.field + " " + operator + " :" + this.paramInit;
	}
	
	public Condition(Operator operator, Collection<?> value) {
		this.setOperator(operator);
		this.field = "";
		this.paramInit = generateParam(memoryAddress() + field );
		this.valueInit = value;
		this.hql = "alias " + operator + "( :" + this.paramInit + ") ";
	}
	
	public Condition(String field, Operator operator, Collection<?> value) {
		this.setOperator(operator);
		this.field = field;
		this.paramInit = generateParam(memoryAddress() + field);
		this.valueInit = value;
		this.hql = "alias."+this.field + " " + operator + "( :" + this.paramInit + ") ";
	}

	public Condition(Function functionField, Operator operator, Object value) {
		this.setOperator(operator);
		this.field = functionField.getField();
		this.paramInit = generateParam(memoryAddress() + field);
		this.valueInit = value;
		this.hql = functionField.toHQL() + " " + operator + " :" + this.paramInit;
	}

	public Condition(Function functionField, Operator operator, Function functionValue) {
		this.setOperator(operator);
		this.field = functionField.getField();
		this.paramInit = generateParam(memoryAddress() + field);
		this.valueInit = functionValue.getValue();
		this.hql = functionField.toHQL() + " " + operator + " " + functionField.toHQL().replace(this.field, ":" + this.paramInit);
	}

	/** somente para operador BETWEEN **/
	public Condition(String field, Operator operator, Object valueInit, Object valueEnd) {
		this.setOperator(operator);
		this.field = field;
		this.paramInit = generateParam(memoryAddress() + field + "1");
		this.paramEnd = generateParam(memoryAddress() + field + "2");
		this.valueInit = valueInit;
		this.valueEnd = valueEnd;
		this.hql = "alias."+this.field + " " + operator + " :" + this.paramInit + " AND :" + this.paramEnd;
	}

	public Condition(Function functionField, Operator operator, Object valueInit, Object valueEnd) {
		this.setOperator(operator);
		this.field = functionField.getField();
		this.paramInit = generateParam(memoryAddress() + field + "1");
		this.paramEnd = generateParam(memoryAddress() + field + "2");
		this.valueInit = valueInit;
		this.valueEnd = valueEnd;
		this.hql = functionField.toHQL() + " " + operator + " :" + this.paramInit + " AND :" + this.paramEnd;
	}

	public Condition(Function functionField, Operator operator, Function functionValueInit, Function functionValueEnd) {
		this.setOperator(operator);
		this.field = functionField.getField();
		this.paramInit = generateParam(memoryAddress() + field + "1");
		this.paramEnd = generateParam(memoryAddress() + field + "2");
		this.valueInit = functionValueInit.getValue();
		this.valueEnd = functionValueEnd.getValue();
		this.hql = functionField.toHQL() + " " + operator + " :" + functionField.toHQL().replace(this.field, ":" + this.paramInit) + " AND :" + functionField.toHQL().replace(this.field, ":" + this.paramEnd);
	}

	public Condition(String field, Operator operator) {
		this.setOperator(operator);
		this.field = field;
		this.hql = "alias."+this.field + " " + operator;
		 
	}

	public String getHQL() {
		return " " + this.hql + " ";
	}

	@Override
	public String toString() {
		return this.getHQL();
	}

	public String getParam() {
		return paramInit;
	}

	public String getParamEnd() {
		return paramEnd;
	}

	public Object getValue() {
//		if(valueInit instanceof DateTime)
//			return ((DateTime) valueInit).toDate();
		
		return valueInit;
	}

	public Object getValueEnd() {
//		if(valueEnd instanceof DateTime)
//			return ((DateTime) valueEnd).toDate();
		
		return valueEnd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hql == null) ? 0 : hql.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Condition other = (Condition) obj;
		if (hql == null) {
			if (other.hql != null)
				return false;
		} else if (!hql.equals(other.hql))
			return false;
		return true;
	}

	private String memoryAddress() {
		return getClass().getName() + '@' + Integer.toHexString(hashCode());
	}

	private String generateParam(String param) {
		return field.replace(".", "_") + "_" + Integer.toHexString(param.hashCode());
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public Operator getOperator() {
		return operator;
	}

}
