package com.node;

import java.io.Serializable;
import java.util.List;

public class NPageInfo<T> implements Serializable {
	private static final long serialVersionUID = 6909588484690723686L;
	
	private int total;		//总共有多少项
	private int pages;		//总共有多少页
	private int pageNum;	//当前页
	private int pageSize;	//每页有多少项
	private int size;		//当前页有多少项
	private List<T> listT;
	public NPageInfo() {
		super();
	}
	public NPageInfo(int total, int pages, int pageNum, int pageSize, int size, List<T> listT) {
		super();
		this.total = total;
		this.pages = pages;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.size = size;
		this.listT = listT;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public List<T> getListT() {
		return listT;
	}
	public void setListT(List<T> listT) {
		this.listT = listT;
	}
}
