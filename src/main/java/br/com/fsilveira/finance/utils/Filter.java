package br.com.fsilveira.finance.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.fsilveira.finance.utils.enums.Operator;
import br.com.fsilveira.finance.utils.functions.Function;

public class Filter {

	private List<Condition> conditions = new ArrayList<Condition>();
	private OrderBy orderBy;
	private String alias;

	public Filter() {
		this.alias = "o";
	}

	public Filter(OrderBy orderBy) {
		this();
		this.orderBy = orderBy;
	}

	public void clearAll() {
		clearConditions();
		clearOrder();
	}

	public void clearConditions() {
		conditions = new ArrayList<Condition>();
	}

	public void clearOrder() {
		orderBy = null;
	}

	public String getConditionsStr() {

		StringBuilder builder = new StringBuilder();
		for (Condition condition : conditions) {

			if (builder.length() == 0)
				builder.append(" WHERE ");
			else
				builder.append(" AND ");

			String cond = condition.toString().replace("alias.:", ":");
			builder.append(cond.replace("alias", getAlias()));

		}

		if (builder.toString().contains(" AND ( ") || builder.toString().contains(" WHERE ("))
			builder.append(" ) ");

		return builder.toString();
	}

	public OrderBy getOrderBy() {
		return orderBy;
	}

	public String getOrderByStr() {
		return (orderBy != null) ? orderBy.toString().replace("alias", getAlias()) : "";
	}

	public Filter orderBy(OrderBy orderBy) {
		this.orderBy = orderBy;
		return this;
	}

	public Filter setOrderBy(OrderBy orderBy) {
		return orderBy(orderBy);
	}

	@Override
	public String toString() {
		return getConditions() + getOrderByStr();
	}

	/** Operador relacional: = **/
	public Filter addEqualTo(String field, Object value) {
		this.conditions.add(new Condition(field, Operator.EQUAL_TO, value));
		return this;
	}

	/** Operador relacional: = **/
	public Filter addEqualTo(Function functionField, Object value) {
		this.conditions.add(new Condition(functionField, Operator.EQUAL_TO, value));
		return this;
	}

	/** Operador relacional: = **/
	public Filter addEqualTo(Function functionField, Function functionValue) {
		this.conditions.add(new Condition(functionField, Operator.EQUAL_TO, functionValue));
		return this;
	}

	/** Operador relacional: != **/
	public Filter addNotEqualTo(String field, Object value) {
		this.conditions.add(new Condition(field, Operator.NOT_EQUAL_TO, value));
		return this;
	}

	/** Operador relacional: != **/
	public Filter addNotEqualTo(Function functionField, Object value) {
		this.conditions.add(new Condition(functionField, Operator.NOT_EQUAL_TO, value));
		return this;
	}

	/** Operador relacional: != **/
	public Filter addNotEqualTo(Function functionField, Function functionValue) {
		this.conditions.add(new Condition(functionField, Operator.NOT_EQUAL_TO, functionValue));
		return this;
	}

	/** Operador relacional: > **/
	public Filter addGreaterThan(String field, Object value) {
		this.conditions.add(new Condition(field, Operator.GREATER_THAN, value));
		return this;
	}

	/** Operador relacional: > **/
	public Filter addGreaterThan(Function functionField, Object value) {
		this.conditions.add(new Condition(functionField, Operator.GREATER_THAN, value));
		return this;
	}

	/** Operador relacional: > **/
	public Filter addGreaterThan(Function functionField, Function functionValue) {
		this.conditions.add(new Condition(functionField, Operator.GREATER_THAN, functionValue));
		return this;
	}

	/** Operador relacional: < **/
	public Filter addLessThan(String field, Object value) {
		this.conditions.add(new Condition(field, Operator.LESS_THAN, value));
		return this;
	}

	/** Operador relacional: < **/
	public Filter addLessThan(Function functionField, Object value) {
		this.conditions.add(new Condition(functionField, Operator.LESS_THAN, value));
		return this;
	}

	/** Operador relacional: < **/
	public Filter addLessThan(Function functionField, Function functionValue) {
		this.conditions.add(new Condition(functionField, Operator.LESS_THAN, functionValue));
		return this;
	}

	/** Operador relacional: >= **/
	public Filter addGreaterThanOrlessThan(String field, Object value) {
		this.conditions.add(new Condition(field, Operator.GREATER_THAN_OR_EQUAL_TO, value));
		return this;
	}

	/** Operador relacional: >= **/
	public Filter addGreaterThanOrlessThan(Function functionField, Object value) {
		this.conditions.add(new Condition(functionField, Operator.GREATER_THAN_OR_EQUAL_TO, value));
		return this;
	}

	/** Operador relacional: >= **/
	public Filter addGreaterThanOrlessThan(Function functionField, Function functionValue) {
		this.conditions.add(new Condition(functionField, Operator.GREATER_THAN_OR_EQUAL_TO, functionValue));
		return this;
	}

	/** Operador relacional: <= **/
	public Filter addLessThanOrEqualTo(String field, Object value) {
		this.conditions.add(new Condition(field, Operator.LESS_THAN_OR_EQUAL_TO, value));
		return this;
	}

	/** Operador relacional: <= **/
	public Filter addLessThanOrEqualTo(Function functionField, Object value) {
		this.conditions.add(new Condition(functionField, Operator.LESS_THAN_OR_EQUAL_TO, value));
		return this;
	}

	/** Operador relacional: <= **/
	public Filter addLessThanOrEqualTo(Function functionField, Function functionValue) {
		this.conditions.add(new Condition(functionField, Operator.LESS_THAN_OR_EQUAL_TO, functionValue));
		return this;
	}

	public Filter addLike(String field, Object value) {
		this.conditions.add(new Condition(field, Operator.LIKE, value));
		return this;
	}

	public Filter addLike(Function functionField, Object value) {
		this.conditions.add(new Condition(functionField, Operator.LIKE, value));
		return this;
	}

	public Filter addLike(Function functionField, Function functionValue) {
		this.conditions.add(new Condition(functionField, Operator.LIKE, functionValue));
		return this;
	}

	public Filter addBetween(String field, Object valueInit, Object valueEnd) {
		this.conditions.add(new Condition(field, Operator.BETWEEN, valueInit, valueEnd));
		return this;
	}

	public Filter addBetween(Function functionField, Object valueInit, Object valueEnd) {
		this.conditions.add(new Condition(functionField, Operator.BETWEEN, valueInit, valueEnd));
		return this;
	}

	public Filter addBetween(Function functionField, Function functionValueInit, Function functionValueEnd) {
		this.conditions.add(new Condition(functionField, Operator.BETWEEN, functionValueInit, functionValueEnd));
		return this;
	}

	public Filter addIn(String field, Collection<?> collection) {
		this.conditions.add(new Condition(field, Operator.IN, collection));
		return this;
	}

	public Filter addIn(Collection<?> collection) {
		this.conditions.add(new Condition(Operator.IN, collection));
		return this;
	}
	
	public Filter addNotIn(Collection<?> collection) {
		this.conditions.add(new Condition(Operator.NOTIN, collection));
		return this;
	}

	public Filter addIsNull(String field) {
		this.conditions.add(new Condition(field, Operator.ISNULL));
		return this;
	}

	public Filter addIsNotNull(String field) {
		this.conditions.add(new Condition(field, Operator.ISNOTNULL));
		return this;
	}

	public List<Condition> getConditions() {
		return conditions;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

}
