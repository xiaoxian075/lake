package lake.ctr.node;

import java.io.Serializable;

public class NNetPage implements Serializable{
	private static final long serialVersionUID = 5737204158991425620L;
	
	private Integer pageNum;
	private Integer pageSize;
	public NNetPage() {
		super();
	}
	public NNetPage(Integer pageNum, Integer pageSize) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}
	public Integer getPageNum() {
		if (pageNum==null || pageNum<=0) {
			return 1;
		}
		if (pageNum>999999) {
			return 999999;
		}
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		if (pageSize==null || pageSize<=0) {
			return 10;
		}
		if (pageSize>100) {
			return 100;
		}
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
