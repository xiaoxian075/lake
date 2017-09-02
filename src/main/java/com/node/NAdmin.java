package com.node;

import java.io.Serializable;

import com.util.DateUtil;

public class NAdmin implements Serializable{
	private static final long serialVersionUID = 8592771728608049846L;
	
	private String userName;
	private long timestamp;

	public NAdmin() {
		super();
		this.userName = null;
		this.timestamp = DateUtil.getCurrentTime();
	}
	public NAdmin(String userName,long timestamp) {
		super();
		this.userName = userName;
		this.timestamp = timestamp;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
