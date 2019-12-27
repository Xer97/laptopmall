package com.laptopmall.page;

import java.util.List;

public class PageInfo<T> {
	private List<T> list;
	private Integer currentPage;
	private Integer pageSize;
	private Integer totalCount;

	private Integer totalPage;
	private Integer prePage;
	private Integer nextPage;

	public PageInfo() {

	}

	public PageInfo(List<T> list, Integer currentPage, Integer pageSize, Integer totalCount) {
		this.list = list;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		totalPage = (int) Math.ceil(totalCount.doubleValue() / pageSize);
		currentPage = currentPage > totalPage ? 1 : currentPage;
		prePage = currentPage - 1 <= 0 ? 1 : currentPage - 1;
		nextPage = currentPage + 1 > totalPage ? totalPage : currentPage + 1;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public Integer getPrePage() {
		return prePage;
	}

	public Integer getNextPage() {
		return nextPage;
	}

}
