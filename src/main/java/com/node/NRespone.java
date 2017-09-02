package com.node;

import java.io.Serializable;

import com.util.JsonUtil;

public class NRespone implements Serializable{
	
	public static <T> String toStr() {
		return toStr(0,"",null);
	}
	
	public static <T> String toStr(T t) {
		return toStr(0,"",t);
	}
	
	public static <T> String toStr(int code, String desc, T t) {
		return JsonUtil.toString(new NRespone(code,desc,t));
	}
	
	
	private static final long serialVersionUID = -3792708544415432751L;
	private int code;
	private String desc;
	private Object info;
	public NRespone() {
		super();
	}
	public NRespone(int code, String desc, Object info) {
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

