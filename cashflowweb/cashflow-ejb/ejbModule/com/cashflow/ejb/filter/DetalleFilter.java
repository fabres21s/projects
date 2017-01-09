package com.cashflow.ejb.filter;

import com.cashflow.ejb.paginator.Paginator;

public class DetalleFilter {

	private int firstRow;
	private int sizePage;
	private String sortField;
	private String orderBy;
	
	public DetalleFilter(Paginator paginator) {
		this.firstRow = paginator.getFirstRow();
		this.sizePage = paginator.getSizePage();
		this.sortField = paginator.getSortField();
		this.orderBy = (paginator.isSortAscending() ? "ASC" : "DESC");
	}

	public int getFirstRow() {
		return firstRow;
	}

	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}

	public int getSizePage() {
		return sizePage;
	}

	public void setSizePage(int sizePage) {
		this.sizePage = sizePage;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}	
}
