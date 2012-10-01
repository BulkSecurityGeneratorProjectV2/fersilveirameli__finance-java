package br.com.fsilveira.finance.business;

import java.util.Arrays;
import java.util.List;

public enum Month {
	JAN, FEV, MAR, ABR, MAI, JUN, JUL, AGO, SET, OUT, NOV, DEZ;

	public static List<Month> getList() {
		return Arrays.asList(JAN, FEV, MAR, ABR, MAI, JUN, JUL, AGO, SET, OUT, NOV, DEZ);
	}
}
