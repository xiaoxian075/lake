package com.test;

import java.util.HashMap;
import java.util.Map;

public class Test {

//	private BigInteger id;
//	private String loginName;
//	private String password;
//	private String userName;
//	private String nickName;
//	private String identityID;
//	private String identityName;
//	private Date birth;
//	private Timestamp createTime;
	
	public static void main(String[] args) {
//		RedisUtil.setObject(
//							"test", 
//							new NAccount(new BigInteger("1"),
//									"xiaoxian",
//									"123456",
//									"tianfeng",
//									"xiaoxian",
//									"344566",
//									"吧建吕",
//									new Date(),
//									new Timestamp(new Date().getTime())),
//							30);
//		NAccount nAccount = RedisUtil.getObject("test");
//		System.out.println(nAccount);
		
		
//		List<String> list = new ArrayList<String>();
//		list.add("test001");
//		list.add("test002");
//		list.add("test003");
//		list.add("test004");
//		list.add("test005");
//		RedisUtil.listInit("test", list, 300);
//		
//		RedisUtil.listAdd("test", "testAdd");
//		
//		RedisUtil.listDel("test", "testAdd");
//		
//		RedisUtil.listReset("test", 2, "testReset");
//		
//		List<String> _list = RedisUtil.listGet("test");
//		System.out.println(_list);
//		
//		String str = RedisUtil.listGetOne("test", 3);
//		System.out.println(str);
		
		Map<Integer,String> map = new HashMap<Integer,String>();
		map.put(1, "test001");
		map.put(2, "test002");
		map.put(3, "test003");
		map.put(4, "test004");
		map.put(5, "test005");
		
		//RedisUtil.hashInit("test", map, 300);

//		RedisUtil.hashSet("test", 2, "test022");
		
//		boolean b3 = RedisUtil.hashDel("test", 3);
//		boolean b1 = RedisUtil.hashExists("test", 4);
//		boolean b2 = RedisUtil.hashExists("test", 9);
//		
//		Map<Integer,String> _map = RedisUtil.hashGet("test");
//		for (Entry<Integer,String> entry : _map.entrySet()) {
//			System.out.println(entry.getKey()+":"+entry.getValue());
//		}	
		
//		long l = RedisUtil.hashSize("test");
//		System.out.println(l);
//		
//		String str = RedisUtil.hashGetOne("test", 3);
//		System.out.println(str);
		
		
	}
}
