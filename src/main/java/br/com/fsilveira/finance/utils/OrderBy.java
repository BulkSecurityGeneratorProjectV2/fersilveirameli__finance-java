package br.com.fsilveira.finance.utils;

import br.com.fsilveira.finance.utils.enums.Order;


public class OrderBy {

	private Order order;
	private String field;

	public OrderBy(Order order, String field) {
		this.order = order;
		this.field = field;
	}

	public String getField() {
		return this.field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return " ORDER BY " + field + " " + order;
	}

}
