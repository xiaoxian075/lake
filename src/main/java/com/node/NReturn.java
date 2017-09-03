package com.node;

import java.io.Serializable;

public class NReturn<T> implements Serializable{

	public static <T> NReturn<T> createNew() {
		return createNew(ConstDefine.OK,null);
	}
	
	public static <T> NReturn<T> createNew(int code) {
		return createNew(code,null);
	}
	
	public static <T> NReturn<T> createNew(T t) {
		return createNew(ConstDefine.OK,t);
	}
	
	public static <T> NReturn<T> createNew(int code, T t) {
		return new NReturn<T>(code,ConstDefine.getDesc(code),t);
	}

	private static final long serialVersionUID = 7816738592153317793L;
	private int code;
	private String desc;
	private T t;
	public boolean isSucc() {
		return code==ConstDefine.OK?true:false;
	}
	public boolean isErr() {
		return code==ConstDefine.OK?false:true;
	}
	public NReturn() {
		super();
	}
	public NReturn(int code, String desc, T t) {
		super();
		this.code = code;
		this.desc = desc;
		this.t = t;
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

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

}

