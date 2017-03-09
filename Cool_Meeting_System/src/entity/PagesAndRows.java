package entity;

public class PagesAndRows {
	private int pages;
	private int rows;
	
	public PagesAndRows() {
		// TODO Auto-generated constructor stub
	}
	
	public PagesAndRows(int pages,int rows) {
		// TODO Auto-generated constructor stub
		this.setPages(pages);
		this.setRows(rows);
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
	
	
}
