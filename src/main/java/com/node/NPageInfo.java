package com.node;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页封装（分页返回对象）
 * @author Administrator
 *
 * @param <T>
 */
public class NPageInfo<T> implements Serializable {
	private static final long serialVersionUID = 6909588484690723686L;
	
	public static <T> NPageInfo<T> createNew(int pageNum, int pageSize, List<T> listData) {
		int total = listData.size();
		int tmp = total%pageSize;
		int pages = 0;
		if (tmp==0)
			pages = total/pageSize;
		else 
			pages = total/pageSize + 1;
		if (total==0) {
			return new NPageInfo<T>(0,0,pageNum,pageSize,0,null);
		}

		int size = pageSize;
		int startIndex = (pageNum-1)*pageSize;
		int endIndex = pageNum*pageSize;
		if (startIndex>total) {
			pageNum = pages;
			startIndex = (pageNum-1)*pageSize;
			endIndex = total;
		}
		if (endIndex>total) {
			endIndex = total;
			size = endIndex - startIndex;
		}
		
		List<T> arrData = new ArrayList<T>();
		for (int i=startIndex; i<endIndex; i++) {
			arrData.add(listData.get(i));
		}
		
		return new NPageInfo<T>(total,pages,pageNum,pageSize,size,arrData);
	}
	
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
	@Override
	public String toString() {
		return "NPageInfo [total=" + total + ", pages=" + pages + ", pageNum=" + pageNum + ", pageSize=" + pageSize
				+ ", size=" + size + ", listT=" + listT + "]";
	}
}
