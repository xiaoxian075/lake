package com.util;

import com.google.gson.Gson;
import com.node.ReqMsg;

public class JsonUtil {

	public static Gson gson = new Gson();
	
	public static <T> String toSucc(T t) {
		return gson.toJson(new ReqMsg(0,"",t));
	}
	
	public static <T> String toString(int code, String desc, T t) {
		return gson.toJson(new ReqMsg(code,desc,t));
	}
	
	public static <T> String toString(T t) {
		return gson.toJson(t);
	}
	
	public static <T> T toJson(String data,Class<T> c) {
		return gson.fromJson(data, c);
	}
	
	
}