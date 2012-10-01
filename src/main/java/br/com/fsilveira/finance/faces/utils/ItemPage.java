package br.com.fsilveira.finance.faces.utils;

public class ItemPage {

	private String label;
	private Integer size;

	public ItemPage(String label, Integer size) {
		super();
		this.label = label;
		this.size = size;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

}
