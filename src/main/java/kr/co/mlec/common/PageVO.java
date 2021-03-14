package kr.co.mlec.common;

public class PageVO {
	private int pageNo;
	private int totalCount;
	private int lastPage;
	private int tabSize;
	private int currTab;
	private int beginPage;
	private int endPage;
	public PageVO(int pageNo, int totalCount, int lastPage, int tabSize, int currTab, int beginPage, int endPage) {
		super();
		this.pageNo = pageNo;
		this.totalCount = totalCount;
		this.lastPage = lastPage;
		this.tabSize = tabSize;
		this.currTab = currTab;
		this.beginPage = beginPage;
		this.endPage = endPage;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	public int getTabSize() {
		return tabSize;
	}
	public void setTabSize(int tabSize) {
		this.tabSize = tabSize;
	}
	public int getCurrTab() {
		return currTab;
	}
	public void setCurrTab(int currTab) {
		this.currTab = currTab;
	}
	public int getBeginPage() {
		return beginPage;
	}
	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	@Override
	public String toString() {
		return "pageVO [pageNo=" + pageNo + ", totalCount=" + totalCount + ", lastPage=" + lastPage + ", tabSize="
				+ tabSize + ", currTab=" + currTab + ", beginPage=" + beginPage + ", endPage=" + endPage + "]";
	}
}
