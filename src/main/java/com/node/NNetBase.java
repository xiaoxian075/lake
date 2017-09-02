package com.node;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.util.JsonUtil;

public class NNetBase<T> implements Serializable{
	private static final long serialVersionUID = -9156919340844956048L;

	public static final int ERR_PARAM = 1;
	public static final int ERR_VERSION = 2;
	public static final int ERR_ENCRYPT = 3;
	public static final int ERR_RANDOM = 4;
	public static final int ERR_DATA = 5;
	public static final int ERR_T = 6;

	public static <T> NNetBase<T> createNew(HttpServletRequest request, Class<T> c) {
		if (request==null || c==null)
			return new NNetBase<T>(ERR_PARAM);
		
		String version = request.getParameter("version");
		if (version==null)
			return new NNetBase<T>(ERR_VERSION);
		String encrypt = request.getParameter("encrypt");
		if (encrypt==null)
			return new NNetBase<T>(ERR_ENCRYPT);
		String random = request.getParameter("random");
		String data = request.getParameter("data");
		if (StringUtils.isBlank(data))
			return new NNetBase<T>(ERR_DATA);
		
		T t = JsonUtil.toJson(data, c);
		if (t==null)
			return new NNetBase<T>(ERR_T);
		
		return new NNetBase<T>(version,encrypt,random,t);
	}
	
	private int code;
	private String version;
	private String encrypt;
	private String random;
	private T t;
	public NNetBase() {
		super();
	}
	public NNetBase(int code) {
		super();
		this.code = code;
		this.version = null;
		this.encrypt = null;
		this.random = "";
		this.t = null;
	}
	public NNetBase(String version, String encrypt, String random, T t) {
		super();
		this.code = 0;
		this.version = version;
		this.encrypt = encrypt;
		this.random = random;
		this.t = t;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getEncrypt() {
		return encrypt;
	}
	public void setEncrypt(String encrypt) {
		this.encrypt = encrypt;
	}
	public String getRandom() {
		return random;
	}
	public void setRandom(String random) {
		this.random = random;
	}
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
	@Override
	public String toString() {
		return "NNetBase [version=" + version + ", encrypt=" + encrypt + ", random=" + random + ", t=" + t + "]";
	}


}
