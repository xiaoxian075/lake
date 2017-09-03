package com.node;

import java.io.Serializable;

import com.util.JsonUtil;

public class NRespone implements Serializable{
	
	public static String toStr() {
		return toStr(ConstDefine.OK,null);
	}
	
	public static String toStr(int code) {
		return toStr(code,null);
	}
	
	public static <T> String toStr(T t) {
		return toStr(ConstDefine.OK,t);
	}
	public static <T> String toStr(int code, T t) {
		return JsonUtil.toString(new NRespone(code,ConstDefine.getDesc(code),t));
	}
	
	public static <T> String toStr(NReturn<T> nReturn) {
		return JsonUtil.toString(new NRespone(nReturn.getCode(),nReturn.getDesc(),nReturn.getT()));
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

