package com.util;

import com.google.gson.Gson;

public class JsonUtil {

	public static Gson gson = new Gson();
	
	public static <T> String toString(T t) {
		return gson.toJson(t);
	}
	
	public static <T> T toJson(String data,Class<T> c) {
		return gson.fromJson(data, c);
	}
}