package com.node;

import java.util.HashMap;
import java.util.Map;

public class ConstDefine {
	
	private static Map<Integer,String> mapErr = new HashMap<Integer,String>();
	static {
		mapErr.put(ConstDefine.OK, "成功");
		mapErr.put(ConstDefine.LOGOUT, "登出");
		mapErr.put(ConstDefine.PARAM, "参数错误");
		mapErr.put(ConstDefine.LOGIN_NOT_EXIST, "登入名不存在");
		mapErr.put(ConstDefine.PASSWORD, "密码错误");
	}
	public static String getDesc(int id) {
		return mapErr.get(id);
	}
	
	public static final int OK = 0;		//成功
	public static final int LOGOUT = 1;		//登出
	public static final int PARAM = 1001;	//参数错误
	public static final int LOGIN_NOT_EXIST = 1002;	//登入名不存在
	public static final int PASSWORD = 1003;	//密码错误
	
	
	public static final String ADMIN_ID = "admin_user";

}
