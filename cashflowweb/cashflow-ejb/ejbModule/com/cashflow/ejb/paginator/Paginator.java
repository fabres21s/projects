package com.cashflow.ejb.paginator;


public class Paginator {
	
	private int firstRow;
	//private int endRow;
	private int sizePage;
	private int totalRecords;
	private int currentPage;
	private Integer[] records;
	private int cantidadEnlaces = 5;
	
	private int rowsPerPage;
	private int pageRange;
	//private List<SelectItem> rowsPerPageItems = new ArrayList<SelectItem>();
	
	private PaginatorInterface bean;
	
	// Sorting.
    private String sortField;
    private boolean sortAscending;
	
    public Paginator(){
    	sortField = ""; // Default sort field.
        setSortAscending(true); // Default sort direction.
    }
    
	public Paginator(int sizePage, String sortField, boolean sortAscending, PaginatorInterface bean){
		setSizePage(sizePage);
		setFirstRow(0);
		setCurrentPage(1);
		
		this.sortField = sortField; // Default sort field.
        setSortAscending(sortAscending); // Default sort direction.
		
		this.bean = bean;
	}
	
	public Paginator(int rowsPerPage, PaginatorInterface bean, String sortField, boolean sortAscending) {
        this.rowsPerPage = rowsPerPage; // Default rows per page (max amount of rows to be displayed at once).
        this.pageRange = 10; // Default page range (max amount of page links to be displayed at once).
        this.bean = bean;
        this.sortField = sortField; // Default sort field.
        this.setSortAscending(sortAscending); // Default sort direction.

       
    }
	
	// ---
	// Sorting actions ----------------------------------------------------------------------------
    public void sort(String sortFieldAttribute) {

        // If the same field is sorted, then reverse order, else sort the new field ascending.
        if (sortField.equals(sortFieldAttribute)) {
            setSortAscending(!isSortAscending());
        } else {
            sortField = sortFieldAttribute;
            setSortAscending(true);
        }
        pageFirst(); // Go to first page and load requested page.
    }
	
	// ---
	
    public int getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}
	
	public int getPageRange() {
		return pageRange;
	}

	public void setPageRange(int pageRange) {
		this.pageRange = pageRange;
	}
    
	
	
    public String getSortTotal() {
		return sortField + (this.isSortAscending()?" ASC":" DESC");
	}
    
    public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	
    public boolean isSortAscending() {
		return sortAscending;
	}

	public void setSortAscending(boolean sortAscending) {
		this.sortAscending = sortAscending;
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
