package com.cashflow.bean;

public class Paginator {
	
	private int firstRow;
	//private int endRow;
	private int sizePage;
	private int totalRecords;
	private int currentPage;
	private Integer[] records;
	private int cantidadEnlaces = 5;
	
	
	private CashflowBean bean;
	
	public Paginator(int sizePage, CashflowBean cashflowBean){
		setSizePage(sizePage);
		setFirstRow(0);
		setCurrentPage(1);
		
		bean = cashflowBean;
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

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
		update();
	}
	
	public Integer[] getRecords() {
		return records;
	}

	public void setRecords(Integer[] records) {
		this.records = records;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	
	//accciones
	public void pageFirst() {
		setFirstRow(0);
		update();
	}
	
	public void pagePrevious() {
		setFirstRow( firstRow - sizePage);
		update();
	}
	
	public void pageLast() {
		setFirstRow((totalRecords - ((totalRecords % sizePage != 0) ? totalRecords % sizePage : sizePage)));
		update();
	}
	
	public void pageNext() {
		setFirstRow(firstRow + sizePage);
		update();
	}
	
	public void page(int page) {
		setFirstRow( (page - 1)  * sizePage);
		update();
	}
	
	
	private void update() {

		currentPage = (totalRecords / sizePage) - ((totalRecords - firstRow) / sizePage) + 1;
        
        
        int totalPages = (totalRecords / sizePage) + ((totalRecords % sizePage != 0) ? 1 : 0);
        
        
        int pagesLength = Math.min(Math.min(cantidadEnlaces, totalPages), cantidadEnlaces); //mostraremos máximo 5 enlaces (páginas)
        records = new Integer[pagesLength];

        // firstPage must be greater than 0 and lesser than totalPages-pageLength.
        int firstPage = Math.min(currentPage, totalPages - (totalPages > 5 ? 4 : (totalPages - 1)));
        
        // Create pages (page numbers for page links).
        for (int i = 0; i < pagesLength; i++) {
            records[i] = firstPage++;
        }

		
		bean.updatetable();
	}





}
