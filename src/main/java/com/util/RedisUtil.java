package com.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import lake.entity.NRedis;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis连接池工具类
 * 
 * @author Administrator
 *
 */
public final class RedisUtil{
	
	private static NRedis redisConfig;
	
	public static boolean init(NRedis _redisConfig) {
		redisConfig = _redisConfig;
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(redisConfig.getMaxActive());
            config.setMaxIdle(redisConfig.getMaxIdle());
            config.setMaxWaitMillis(redisConfig.getMaxWait());
            config.setTestOnBorrow(redisConfig.isTestOnBorrow());
            if (redisConfig.getAuth()!=null && redisConfig.getAuth().length()>0)
            	jedisPool = new JedisPool(config, redisConfig.getHost(), redisConfig.getPort(), redisConfig.getTimeOut(), redisConfig.getAuth());
            else 
            	jedisPool = new JedisPool(config, redisConfig.getHost(), redisConfig.getPort(), redisConfig.getTimeOut());
        } catch (Exception e) {
            return false;
        }
		return true;
	}

//    // Redis服务器IP
//    private static String ADDR = "127.0.0.1";
//
//    // Redis的端口号
//    private static int PORT = 6379;
//
//    // 访问密码
//    private static String AUTH = "";
//
//    // 可用连接实例的最大数目，默认值为8；
//    // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
//    private static int MAX_ACTIVE = 1024;
//
//    // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
//    private static int MAX_IDLE = 200;
//
//    // 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
//    private static int MAX_WAIT = 10000;
//
//    private static int TIMEOUT = 10000;
//
//    // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
//    private static boolean TEST_ON_BORROW = true;

    private static JedisPool jedisPool = null;

//    /**
//     * 初始化Redis连接池
//     */
//    static {
//        try {
//            JedisPoolConfig config = new JedisPoolConfig();
//            config.setMaxTotal(MAX_ACTIVE);
//            config.setMaxIdle(MAX_IDLE);
//            config.setMaxWaitMillis(MAX_WAIT);
//            config.setTestOnBorrow(TEST_ON_BORROW);
//            if (AUTH!=null && AUTH.length()>0)
//            	jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
//            else 
//            	jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @SuppressWarnings("deprecation")
	public static boolean exists(String key) {
    	if (key==null || key.length()==0)
    		return false;
    	
    	if (null == jedisPool)
    		return false;
    	Jedis jedis = jedisPool.getResource();
    	if (jedis==null)
    		return false;
    	
    	boolean result = false;
    	try {
    		result = jedis.exists(key.getBytes());
    	} catch (Exception e) {
    		return false;
    	} finally {
    		jedisPool.returnResource(jedis);
    	}
    	return result;
    }
    
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static <T> T getObj(String key) {
    	if (key==null || key.length()==0)
    		return null;
    	if (null == jedisPool)
    		return null;
    	Jedis jedis = jedisPool.getResource();
    	if (jedis==null)
    		return null;
    	
    	T result = null;
    	try {
    		byte[] data = jedis.get(key.getBytes());
    		result = (T)unserialize(data);
    	} catch (Exception e) {
    		return null;
    	} finally {
    		jedisPool.returnResource(jedis);
    	}
    	return result;
    }
    
	public static <T> boolean setObj(String key, T t) {
		return setObj(key,t,-1);
	}
    @SuppressWarnings("deprecation")
	public static <T> boolean setObj(String key, T t, int seconds) {
    	if (key==null || key.length()==0 || t==null)
    		return false;
    	if (null == jedisPool)
    		return false;
    	Jedis jedis = jedisPool.getResource();
    	if (jedis==null)
    		return false;
    	
    	byte[] bKey = key.getBytes();
    	try {
    		if (jedis.exists(bKey)) {
        		jedis.del(bKey);
        	}
    		
    		jedis.setnx(bKey, serialize(t));
    		if (seconds>0) {
    			jedis.expire(bKey, seconds);
    		}
    		
    	} catch (Exception e) {
    		return false;
    	} finally {
    		jedisPool.returnResource(jedis);
    	}
    	return true;
    }
 
    
    
    
    /************** start list*******************/
    
    //获取所有列表
    public static <T> List<T> getAllList(String key) {
    	return getAllList(key,0,-1);
    }
    
    //获取指定列表
    @SuppressWarnings({ "unchecked", "deprecation" })
	public static <T> List<T> getAllList(String key,int start, int end) {
    	if (key==null || key.length()==0)
    		return null;
    	if (null == jedisPool)
    		return null;
    	Jedis jedis = jedisPool.getResource();
    	if (jedis==null)
    		return null;
    	
    	byte[] bKey = key.getBytes();
    	List<T> result = new ArrayList<T>();
    	try {
    		List<byte[]> data = jedis.lrange(bKey, start, end);
    		for (byte[] bt : data) {
    			result.add((T)unserialize(bt));
    		}
    	} catch (Exception e) {
    		return null;
    	} finally {
    		jedisPool.returnResource(jedis);
    	}
    	return result;
    }
    
    //获取元素
    @SuppressWarnings({ "unchecked", "deprecation" })
	public static <T> T getList(String key, long index) {
    	if (key==null || key.length()==0)
    		return null;
    	if (null == jedisPool)
    		return null;
    	Jedis jedis = jedisPool.getResource();
    	if (jedis==null)
    		return null;
    	
    	T result = null;
    	byte[] bKey = key.getBytes();
    	try {
    		byte[] data = jedis.lindex(bKey, index);
    		if (data!=null) {
    			result = (T)unserialize(data);
    		}
    	} catch (Exception e) {
    		return null;
    	} finally {
    		jedisPool.returnResource(jedis);
    	}

    	return result;
    }
    
    //设置列表
    public static <T> boolean initList(String key, List<T> list) {
    	return initList(key,list,-1);
    }
    
    //设置列表
    @SuppressWarnings("deprecation")
	public static <T> boolean initList(String key, List<T> list, int seconds) {
    	if (key==null || key.length()==0 || list==null || list.size()==0)
    		return false;
    	if (null == jedisPool)
    		return false;
    	Jedis jedis = jedisPool.getResource();
    	if (jedis==null)
    		return false;
    	
    	byte[] bKey = key.getBytes();
    	
    	try {
    		if (jedis.exists(bKey)) {
        		jedis.del(bKey);
        	}
    		for (T t : list) {
    			jedis.rpush(bKey, serialize(t));
    		}
    		if (seconds>0) {
    			jedis.expire(bKey, seconds);
    		}
    		
    	} catch (Exception e) {
    		return false;
    	} finally {
    		jedisPool.returnResource(jedis);
    	}
    	return true;
    }
    
    //添加元素
    @SuppressWarnings("deprecation")
	public static <T> boolean addList(String key, T t) {
    	if (key==null || key.length()==0 || t==null)
    		return false;
    	if (null == jedisPool)
    		return false;
    	Jedis jedis = jedisPool.getResource();
    	if (jedis==null)
    		return false;
    	
    	boolean result = false;
    	byte[] bKey = key.getBytes();
    	try {
    		if (jedis.exists(bKey)) {
    			if (jedis.rpush(bKey, serialize(t)) > 0)
    				result = true;
        	}
    	} catch (Exception e) {
    		return false;
    	} finally {
    		jedisPool.returnResource(jedis);
    	}
    	return result;
    }
    
    //重置元素
    @SuppressWarnings("deprecation")
	public static <T> boolean resetList(String key, long index, T t) {
    	if (key==null || key.length()==0 || t==null)
    		return false;
    	if (null == jedisPool)
    		return false;
    	Jedis jedis = jedisPool.getResource();
    	if (jedis==null)
    		return false;
    	
    	boolean result = false;
    	byte[] bKey = key.getBytes();
    	try {
    		if (jedis.exists(bKey)) {
    			jedis.lset(bKey, index, serialize(t));
    			result = true;
        	}
    	} catch (Exception e) {
    		return false;
    	} finally {
    		jedisPool.returnResource(jedis);
    	}
    	return result;
    }
 
    //删除元素
    @SuppressWarnings("deprecation")
	public static <T> boolean delList(String key, T t) {
    	if (key==null || key.length()==0 || t==null)
    		return false;
    	if (null == jedisPool)
    		return false;
    	Jedis jedis = jedisPool.getResource();
    	if (jedis==null)
    		return false;
    	
    	boolean result = false;
    	byte[] bKey = key.getBytes();
    	try {
    		if (jedis.exists(bKey)) {
    			jedis.lrem(bKey, 1, serialize(t));
    			result = true;
        	}
    	} catch (Exception e) {
    		return false;
    	} finally {
    		jedisPool.returnResource(jedis);
    	}
    	return result;
    }
   
    /************** end list*******************/   

    /************** start hash*******************/     
    
    //设置hash
    public static <K,T> boolean initHash(String key, Map<K,T> map) {
    	return initHash(key,map,-1);
    }
    //设置hash
    @SuppressWarnings("deprecation")
	public static <K,T> boolean initHash(String key, Map<K,T> map, int seconds) {
    	if (key==null || key.length()==0 || map==null || map.size()==0)
    		return false;
    	if (null == jedisPool)
    		return false;
    	Jedis jedis = jedisPool.getResource();
    	if (jedis==null)
    		return false;
    	
    	byte[] bKey = key.getBytes();
    	
    	try {
    		if (jedis.exists(bKey)) {
        		jedis.del(bKey);
        	}
    		for (Entry<K,T> entry : map.entrySet()) {
    			K k = entry.getKey();
    			T t = entry.getValue();
    			jedis.hset(bKey, serialize(k), serialize(t));
    		}
    		if (seconds>0) {
    			jedis.expire(bKey, seconds);
    		}
    		
    	} catch (Exception e) {
    		return false;
    	} finally {
    		jedisPool.returnResource(jedis);
    	}
    	return true;
    }
    
    //验证元素是否存在
	@SuppressWarnings("deprecation")
	public static <K> boolean existsHash(String key, K index) {
    	if (key==null || key.length()==0 || index==null)
    		return false;
    	if (null == jedisPool)
    		return false;
    	Jedis jedis = jedisPool.getResource();
    	if (jedis==null)
    		return false;
    	
    	boolean result = false;
    	byte[] bKey = key.getBytes();
    	byte[] bIndex = serialize(index);
    	try {
    		result = jedis.hexists(bKey, bIndex);
    	} catch (Exception e) {
    		return false;
    	} finally {
    		jedisPool.returnResource(jedis);
    	}

    	return result;
    }
    
    //获取个数
	@SuppressWarnings("deprecation")
	public static long sizeHash(String key) {
    	if (key==null || key.length()==0)
    		return 0;
    	if (null == jedisPool)
    		return 0;
    	Jedis jedis = jedisPool.getResource();
    	if (jedis==null)
    		return 0;
    	
    	long result = 0;
    	byte[] bKey = key.getBytes();
    	try {
    		result = jedis.hlen(bKey);
    	} catch (Exception e) {
    		return 0;
    	} finally {
    		jedisPool.returnResource(jedis);
    	}

    	return result;
    }
    
    //获取元素
    @SuppressWarnings({ "unchecked", "deprecation" })
	public static <K,T> T getHash(String key, K index) {
    	if (key==null || key.length()==0 || index==null)
    		return null;
    	if (null == jedisPool)
    		return null;
    	Jedis jedis = jedisPool.getResource();
    	if (jedis==null)
    		return null;
    	
    	T result = null;
    	byte[] bKey = key.getBytes();
    	byte[] bIndex = serialize(index);
    	try {
    		byte[] data = jedis.hget(bKey, bIndex);
    		if (data!=null) {
    			result = (T)unserialize(data);
    		}
    	} catch (Exception e) {
    		return null;
    	} finally {
    		jedisPool.returnResource(jedis);
    	}

    	return result;
    }
    
    //获取全部hash
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static <K,T> Map<K,T> getAllHash(String key) {
    	
    	if (key==null || key.length()==0)
    		return null;
    	if (null == jedisPool)
    		return null;
    	Jedis jedis = jedisPool.getResource();
    	if (jedis==null)
    		return null;
    	
    	byte[] bKey = key.getBytes();
    	Map<K,T> result = new HashMap<K,T>();
    	try {
    		Map<byte[],byte[]> map = jedis.hgetAll(bKey);
    		for (Entry<byte[],byte[]> entry : map.entrySet()) {
    			result.put((K)unserialize(entry.getKey()),(T)unserialize(entry.getValue()));
    		}
    	} catch (Exception e) {
    		return null;
    	} finally {
    		jedisPool.returnResource(jedis);
    	}
    	return result;    	
    }
    //新增元素
	@SuppressWarnings("deprecation")
	public static <K,T> boolean setHash(String key, K k, T t) {
    	if (key==null || key.length()==0 || k==null || t==null)
    		return false;
    	if (null == jedisPool)
    		return false;
    	Jedis jedis = jedisPool.getResource();
    	if (jedis==null)
    		return false;
    	
    	boolean result = false;
    	byte[] bKey = key.getBytes();
    	byte[] bK = serialize(k);
    	byte[] bT = serialize(t);
    	try {
    		if (jedis.hset(bKey, bK, bT)>0)
    			result = true;
    	} catch (Exception e) {
    		return false;
    	} finally {
    		jedisPool.returnResource(jedis);
    	}

    	return result;
    }
	
    //新增元素
	@SuppressWarnings("deprecation")
	public static <K> boolean delHash(String key, K k) {
    	if (key==null || key.length()==0 || k==null)
    		return false;
    	if (null == jedisPool)
    		return false;
    	Jedis jedis = jedisPool.getResource();
    	if (jedis==null)
    		return false;
    	
    	boolean result = false;
    	byte[] bKey = key.getBytes();
    	byte[] bK = serialize(k);
    	try {
    		if (jedis.hdel(bKey, bK)>0)
    			result = true;
    	} catch (Exception e) {
    		return false;
    	} finally {
    		jedisPool.returnResource(jedis);
    	}

    	return result;
    }    
    
    /************** end hash*******************/      
	
	/************** start set*******************/ 

    //设置set
    public static <T> boolean initSet(String key, Set<T> set) {
    	return initSet(key,set,-1);
    }
    //设置set
    @SuppressWarnings("deprecation")
	public static <T> boolean initSet(String key, Set<T> set, int seconds) {
    	if (key==null || key.length()==0 || set==null || set.size()==0)
    		return false;
    	if (null == jedisPool)
    		return false;
    	Jedis jedis = jedisPool.getResource();
    	if (jedis==null)
    		return false;
    	
    	byte[] bKey = key.getBytes();
    	
    	try {
    		if (jedis.exists(bKey)) {
        		jedis.del(bKey);
        	}
    		for (T t : set) {
    			jedis.sadd(bKey, serialize(t));
    		}
    		if (seconds>0) {
    			jedis.expire(bKey, seconds);
    		}
    		
    	} catch (Exception e) {
    		return false;
    	} finally {
    		jedisPool.returnResource(jedis);
    	}
    	return true;
    }
    
    //验证元素是否存在
	@SuppressWarnings("deprecation")
	public static <K> boolean existsSet(String key, K index) {
    	if (key==null || key.length()==0 || index==null)
    		return false;
    	if (null == jedisPool)
    		return false;
    	Jedis jedis = jedisPool.getResource();
    	if (jedis==null)
    		return false;
    	
    	boolean result = false;
    	byte[] bKey = key.getBytes();
    	byte[] bIndex = serialize(index);
    	try {
    		result = jedis.sismember(bKey, bIndex);
    	} catch (Exception e) {
    		return false;
    	} finally {
    		jedisPool.returnResource(jedis);
    	}

    	return result;
    }
    
    //获取个数
	@SuppressWarnings("deprecation")
	public static long sizeSet(String key) {
    	if (key==null || key.length()==0)
    		return 0;
    	if (null == jedisPool)
    		return 0;
    	Jedis jedis = jedisPool.getResource();
    	if (jedis==null)
    		return 0;
    	
    	long result = 0;
    	byte[] bKey = key.getBytes();
    	try {
    		result = jedis.scard(bKey);
    	} catch (Exception e) {
    		return 0;
    	} finally {
    		jedisPool.returnResource(jedis);
    	}

    	return result;
    }
    
    //获取元素(随机返回)
    @SuppressWarnings({ "unchecked", "deprecation" })
	public static <T> T getSet(String key) {
    	if (key==null || key.length()==0)
    		return null;
    	if (null == jedisPool)
    		return null;
    	Jedis jedis = jedisPool.getResource();
    	if (jedis==null)
    		return null;
    	
    	T result = null;
    	byte[] bKey = key.getBytes();
    	try {
    		byte[] data = jedis.srandmember(bKey);
    		if (data!=null) {
    			result = (T)unserialize(data);
    		}
    	} catch (Exception e) {
    		return null;
    	} finally {
    		jedisPool.returnResource(jedis);
    	}

    	return result;
    }
    
    //获取全部hash
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static <T> Set<T> getAllSet(String key) {
    	
    	if (key==null || key.length()==0)
    		return null;
    	if (null == jedisPool)
    		return null;
    	Jedis jedis = jedisPool.getResource();
    	if (jedis==null)
    		return null;
    	
    	byte[] bKey = key.getBytes();
    	Set<T> result = new HashSet<T>();
    	try {
    		Set<byte[]> set = jedis.smembers(bKey);
    		for (byte[] bt : set) {
    			result.add((T)unserialize(bt));
    		}
    	} catch (Exception e) {
    		return null;
    	} finally {
    		jedisPool.returnResource(jedis);
    	}
    	return result;    	
    }
    //新增元素
	@SuppressWarnings("deprecation")
	public static <K,T> boolean addSet(String key, K k, T t) {
    	if (key==null || key.length()==0 || k==null || t==null)
    		return false;
    	if (null == jedisPool)
    		return false;
    	Jedis jedis = jedisPool.getResource();
    	if (jedis==null)
    		return false;
    	
    	boolean result = false;
    	byte[] bKey = key.getBytes();
    	byte[] bK = serialize(k);
    	byte[] bT = serialize(t);
    	try {
    		if (jedis.sadd(bKey, bK, bT)>0)
    			result = true;
    	} catch (Exception e) {
    		return false;
    	} finally {
    		jedisPool.returnResource(jedis);
    	}

    	return result;
    }
	
    //删除元素
	@SuppressWarnings("deprecation")
	public static <K> boolean delSet(String key, K k) {
    	if (key==null || key.length()==0 || k==null)
    		return false;
    	if (null == jedisPool)
    		return false;
    	Jedis jedis = jedisPool.getResource();
    	if (jedis==null)
    		return false;
    	
    	boolean result = false;
    	byte[] bKey = key.getBytes();
    	byte[] bK = serialize(k);
    	try {
    		if (jedis.srem(bKey, bK)>0)
    			result = true;
    	} catch (Exception e) {
    		return false;
    	} finally {
    		jedisPool.returnResource(jedis);
    	}

    	return result;
    } 
	
	/************** end set*******************/ 
	//4.对Set操作的命令
	//
	//     sadd(key, member)：向名称为key的set中添加元素member
	//
	//     srem(key, member) ：删除名称为key的set中的元素member
	//
	//     spop(key) ：随机返回并删除名称为key的set中一个元素
	//
	//     smove(srckey, dstkey, member) ：将member元素从名称为srckey的集合移到名称为dstkey的集合
	//
	//     scard(key) ：返回名称为key的set的基数
	//
	//     sismember(key, member) ：测试member是否是名称为key的set的元素
	//
	//     sinter(key1, key2,…key N) ：求交集
	//
	//     sinterstore(dstkey, key1, key2,…key N) ：求交集并将交集保存到dstkey的集合
	//
	//     sunion(key1, key2,…key N) ：求并集
	//
	//     sunionstore(dstkey, key1, key2,…key N) ：求并集并将并集保存到dstkey的集合
	//
	//     sdiff(key1, key2,…key N) ：求差集
	//
	//     sdiffstore(dstkey, key1, key2,…key N) ：求差集并将差集保存到dstkey的集合
	//
	//     smembers(key) ：返回名称为key的set的所有元素
	//
	//     srandmember(key) ：随机返回名称为key的set的一个元素
 
    /**
     * 序列化
     * 
     * @param object
     * @return
     */
    private static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            // 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 反序列化
     * 
     * @param bytes
     * @return
     */
    private static Object unserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            // 反序列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {

        }
        return null;
    }
}

//四、jedis操作命令：
//
//1.对value操作的命令
//
//     exists(key)：确认一个key是否存在
//
//     del(key)：删除一个key
//
//     type(key)：返回值的类型
//
//     keys(pattern)：返回满足给定pattern的所有key
//
//     randomkey：随机返回key空间的一个key
//
//     rename(oldname, newname)：将key由oldname重命名为newname，若newname存在则删除newname表示的key
//
//     dbsize：返回当前数据库中key的数目
//
//     expire：设定一个key的活动时间（s）
//
//     ttl：获得一个key的活动时间
//
//     select(index)：按索引查询
//
//     move(key, dbindex)：将当前数据库中的key转移到有dbindex索引的数据库
//
//     flushdb：删除当前选择数据库中的所有key
//
//     flushall：删除所有数据库中的所有key
//
//2.对String操作的命令
//
//     set(key, value)：给数据库中名称为key的string赋予值value
//
//     get(key)：返回数据库中名称为key的string的value
//
//     getset(key, value)：给名称为key的string赋予上一次的value
//
//     mget(key1, key2,…, key N)：返回库中多个string（它们的名称为key1，key2…）的value
//
//     setnx(key, value)：如果不存在名称为key的string，则向库中添加string，名称为key，值为value
//
//     setex(key, time, value)：向库中添加string（名称为key，值为value）同时，设定过期时间time
//
//     mset(key1, value1, key2, value2,…key N, value N)：同时给多个string赋值，名称为key i的string赋值value i
//
//     msetnx(key1, value1, key2, value2,…key N, value N)：如果所有名称为key i的string都不存在，则向库中添加string，名称key i赋值为value i
//
//     incr(key)：名称为key的string增1操作
//
//     incrby(key, integer)：名称为key的string增加integer
//
//     decr(key)：名称为key的string减1操作
//
//     decrby(key, integer)：名称为key的string减少integer
//
//     append(key, value)：名称为key的string的值附加value
//
//     substr(key, start, end)：返回名称为key的string的value的子串
//
//3.对List操作的命令
//
//     rpush(key, value)：在名称为key的list尾添加一个值为value的元素
//
//     lpush(key, value)：在名称为key的list头添加一个值为value的 元素
//
//     llen(key)：返回名称为key的list的长度
//
//     lrange(key, start, end)：返回名称为key的list中start至end之间的元素（下标从0开始，下同）
//
//     ltrim(key, start, end)：截取名称为key的list，保留start至end之间的元素
//
//     lindex(key, index)：返回名称为key的list中index位置的元素
//
//     lset(key, index, value)：给名称为key的list中index位置的元素赋值为value
//
//     lrem(key, count, value)：删除count个名称为key的list中值为value的元素。count为0，删除所有值为value的元素，count>0      从头至尾删除count个值为value的元素，count<0从尾到头删除|count|个值为value的元素。
//
//     lpop(key)：返回并删除名称为key的list中的首元素
//
//     rpop(key)：返回并删除名称为key的list中的尾元素
//
//     blpop(key1, key2,… key N, timeout)：lpop 命令的block版本。即当timeout为0时，若遇到名称为key i的list不存在或该list为空，则命令结束。如果 timeout>0，则遇到上述情况时，等待timeout秒，如果问题没有解决，则对key i+1开始的list执行pop操作。
//
//     brpop(key1, key2,… key N, timeout)：rpop的block版本。参考上一命令。
//
//     rpoplpush(srckey, dstkey)：返回并删除名称为srckey的list的尾元素，并将该元素添加到名称为dstkey的list的头部
//
//4.对Set操作的命令
//
//     sadd(key, member)：向名称为key的set中添加元素member
//
//     srem(key, member) ：删除名称为key的set中的元素member
//
//     spop(key) ：随机返回并删除名称为key的set中一个元素
//
//     smove(srckey, dstkey, member) ：将member元素从名称为srckey的集合移到名称为dstkey的集合
//
//     scard(key) ：返回名称为key的set的基数
//
//     sismember(key, member) ：测试member是否是名称为key的set的元素
//
//     sinter(key1, key2,…key N) ：求交集
//
//     sinterstore(dstkey, key1, key2,…key N) ：求交集并将交集保存到dstkey的集合
//
//     sunion(key1, key2,…key N) ：求并集
//
//     sunionstore(dstkey, key1, key2,…key N) ：求并集并将并集保存到dstkey的集合
//
//     sdiff(key1, key2,…key N) ：求差集
//
//     sdiffstore(dstkey, key1, key2,…key N) ：求差集并将差集保存到dstkey的集合
//
//     smembers(key) ：返回名称为key的set的所有元素
//
//     srandmember(key) ：随机返回名称为key的set的一个元素
//
//5.对zset（sorted set）操作的命令
//
//     zadd(key, score, member)：向名称为key的zset中添加元素member，score用于排序。如果该元素已经存在，则根据score更新该元素的顺序。
//
//     zrem(key, member) ：删除名称为key的zset中的元素member
//
//     zincrby(key, increment, member) ：如果在名称为key的zset中已经存在元素member，则该元素的score增加increment；否则向集合中添加该元素，其score的值为increment
//
//     zrank(key, member) ：返回名称为key的zset（元素已按score从小到大排序）中member元素的rank（即index，从0开始），若没有member元素，返回“nil”
//
//     zrevrank(key, member) ：返回名称为key的zset（元素已按score从大到小排序）中member元素的rank（即index，从0开始），若没有member元素，返回“nil”
//
//     zrange(key, start, end)：返回名称为key的zset（元素已按score从小到大排序）中的index从start到end的所有元素
//
//     zrevrange(key, start, end)：返回名称为key的zset（元素已按score从大到小排序）中的index从start到end的所有元素
//
//     zrangebyscore(key, min, max)：返回名称为key的zset中score >= min且score <= max的所有元素
//
//     zcard(key)：返回名称为key的zset的基数
//
//     zscore(key, element)：返回名称为key的zset中元素element的score
//
//     zremrangebyrank(key, min, max)：删除名称为key的zset中rank >= min且rank <= max的所有元素
//
//     zremrangebyscore(key, min, max) ：删除名称为key的zset中score >= min且score <= max的所有元素
//
//     zunionstore / zinterstore(dstkeyN, key1,…,keyN, WEIGHTS w1,…wN, AGGREGATE SUM|MIN|MAX)：对N个zset求并集和交集，并将最后的集合保存在dstkeyN中。对于集合中每一个元素的score，在进行AGGREGATE运算前，都要乘以对于的WEIGHT参数。如果没有提供WEIGHT，默认为1。默认的AGGREGATE是SUM，即结果集合中元素的score是所有集合对应元素进行 SUM运算的值，而MIN和MAX是指，结果集合中元素的score是所有集合对应元素中最小值和最大值。
//
//6.对Hash操作的命令
//
//     hset(key, field, value)：向名称为key的hash中添加元素field<—>value
//
//     hget(key, field)：返回名称为key的hash中field对应的value
//
//     hmget(key, field1, …,field N)：返回名称为key的hash中field i对应的value
//
//     hmset(key, field1, value1,…,field N, value N)：向名称为key的hash中添加元素field i<—>value i
//
//     hincrby(key, field, integer)：将名称为key的hash中field的value增加integer
//
//     hexists(key, field)：名称为key的hash中是否存在键为field的域
//
//     hdel(key, field)：删除名称为key的hash中键为field的域
//
//     hlen(key)：返回名称为key的hash中元素个数
//
//     hkeys(key)：返回名称为key的hash中所有键
//
//     hvals(key)：返回名称为key的hash中所有键对应的value
//
//     hgetall(key)：返回名称为key的hash中所有的键（field）及其对应的value