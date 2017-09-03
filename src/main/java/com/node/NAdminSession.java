package com.node;

import java.io.Serializable;

import com.util.DateUtil;

import lake.entity.NAdmin;

public class NAdminSession implements Serializable{
	private static final long serialVersionUID = 8592771728608049846L;
	
	private String userName;
	private NAdmin nAdmin;
	private long timestamp;

	public NAdminSession() {
		super();
		this.userName = null;
		this.nAdmin = null;
		this.timestamp = DateUtil.getCurrentTime();
	}
	public NAdminSession(NAdmin nAdmin, long timestamp) {
		super();
		this.userName = nAdmin.getUserName();
		this.nAdmin = nAdmin;
		this.timestamp = timestamp;
	}

	public String getUserName() {
		return userName;
	}
	public NAdmin getnAdmin() {
		return nAdmin;
	}
	public void setnAdmin(NAdmin nAdmin) {
		this.nAdmin = nAdmin;
		this.userName = nAdmin.getUserName();
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
