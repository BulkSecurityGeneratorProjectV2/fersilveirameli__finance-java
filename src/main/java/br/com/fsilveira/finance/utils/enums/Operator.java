package br.com.fsilveira.finance.utils.enums;

public enum Operator {

	/** Operador relacional: = **/
	EQUAL_TO(" = "),
	/** Operador relacional: != **/
	NOT_EQUAL_TO(" != "),

	/** Operador relacional: > **/
	GREATER_THAN(" > "),

	/** Operador relacional: < **/
	LESS_THAN(" < "),

	/** Operador relacional: >= **/
	GREATER_THAN_OR_EQUAL_TO(" >= "),

	/** Operador relacional: <= **/
	LESS_THAN_OR_EQUAL_TO(" <= "),

	LIKE(" LIKE "), BETWEEN(" BETWEEN "), 
	
	IN(" IN "),
	
	NOTIN(" NOT IN "),
	
	ISNULL(" IS NULL"), ISNOTNULL(" IS NOT NULL ");

	private String operator;

	private Operator(String operator) {
		this.operator = operator;
	}

	@Override
	public String toString() {
		return operator;
	}

}
