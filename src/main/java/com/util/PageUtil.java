package com.util;

import java.util.ArrayList;
import java.util.List;

import com.node.NPageInfo;

public class PageUtil {
	
	public static <T> NPageInfo<T> pageInfo(int pageNum, int pageSize, List<T> listData) {
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
}
