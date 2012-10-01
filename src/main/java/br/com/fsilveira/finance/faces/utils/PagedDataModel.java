package br.com.fsilveira.finance.faces.utils;

import java.util.List;

import javax.faces.model.DataModel;

public class PagedDataModel<E> extends DataModel<E> {

	private int rowIndex = -1;

	private int totalNumRows;

	private int pageSize;

	private List<E> list;

	public PagedDataModel() {
		super();
	}

	public PagedDataModel(List<E> list, int totalNumRows, int maxRegPage) {
		super();
		setWrappedData(list);
		this.totalNumRows = totalNumRows;
		this.pageSize = (maxRegPage == 0) ? totalNumRows : maxRegPage;

	}
	
	

	@Override
	public int getRowCount() {
		return this.totalNumRows;
	}

	@Override
	public E getRowData() {
		if (list == null)
			return null;
		else if (!isRowAvailable())
			throw new IllegalArgumentException();
		else {
			int dataIndex = getRowIndex();
			return list.get(dataIndex);
		}
	}

	@Override
	public int getRowIndex() {
		if (pageSize == 0) {
			return 0;
		} else {
			return (rowIndex % pageSize);
		}

	}

	@Override
	public Object getWrappedData() {
		return list;

	}

	@Override
	public boolean isRowAvailable() {
		if (list == null)
			return false;

		int rowIndex = getRowIndex();
		if (rowIndex >= 0 && rowIndex < list.size())
			return true;
		else
			return false;

	}

	@Override
	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;

	}

	@SuppressWarnings("unchecked")
	@Override
	public void setWrappedData(Object object) {
		this.list = (List<E>) object;
	}

}
