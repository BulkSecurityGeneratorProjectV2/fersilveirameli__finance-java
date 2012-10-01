package br.com.fsilveira.finance.utils.enums;

public enum Type {

	DATE("date"), INTEGER("int"), TIME("time");

	private String type;

	private Type(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return this.type;
	}

}
