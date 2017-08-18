package com.node;

import java.io.Serializable;

public class ReqMsg implements Serializable{
	private static final long serialVersionUID = -3792708544415432751L;
	private int code;
	private String desc;
	private Object info;
	public ReqMsg() {
		super();
	}
	public ReqMsg(int code, String desc, Object info) {
		super();
		this.code = code;
		this.desc = desc;
		this.info = info;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Object getInfo() {
		return info;
	}
	public void setInfo(Object info) {
		this.info = info;
	}

}

