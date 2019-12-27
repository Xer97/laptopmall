package com.laptopmall.page;

public class QueryObject {
	private String keyword;
	private Integer brandId;
	private Integer currentPage = 1;
	private Integer pageSize = 4;
	public QueryObject() {
		
	}
	public QueryObject(String keyword, Integer brandId) {
		this.keyword = keyword;
		this.brandId = brandId;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getKeyword() {
		return keyword;
	}
	public Integer getBrandId() {
		return brandId;
	}
}
