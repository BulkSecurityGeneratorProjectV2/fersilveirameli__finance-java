package br.com.fsilveira.finance.utils;

import java.util.List;

import br.com.fsilveira.finance.entity.AbstractEntity;

public class ResultQuery<T extends AbstractEntity> {

	private long size;
	private List<T> list;

	public ResultQuery(long size, List<T> list) {
		if (size == 0 && list != null)
			this.size = list.size();
		else
			this.size = size;
		this.list = list;
	}

	public ResultQuery() {
		this(0l, null);
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}
