package lake.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class NMenu implements Serializable {
	private static final long serialVersionUID = -2830613608829676610L;
	
	private int id;
	private int parentId;
	private String url;
	private String name;
	private int level;
	private int state;
	private int sort;
	private Timestamp createTime;
	private Timestamp updateTime;
	public NMenu() {
		super();
	}
	public NMenu(int id, int parentId, String url, String name, int level, int state, int sort, Timestamp createTime,
			Timestamp updateTime) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.url = url;
		this.name = name;
		this.level = level;
		this.state = state;
		this.sort = sort;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

}
