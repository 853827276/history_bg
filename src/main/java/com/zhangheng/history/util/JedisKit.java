package com.zhangheng.history.util;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;

//@Component
public class JedisKit {
	public static Logger logger = Logger.getLogger(JedisKit.class);

    private JedisKit() {
    }

    private static JedisKit jedisKit = new JedisKit();
    private static Jedis jedis;
    static {
        jedis = new Jedis("154.8.141.51", 6379);
        jedis.auth("zhsj201314");//密码
        jedis.flushDB();//清空当前数据库
        logger.info("jedis status: " + (jedis.ping().equals("PONG") ? "200" : "error"));
    }

    /**
     * jedis 初始化
     * 
     * @return
     */
    public static Jedis getJedis() {
        return jedis;
    }

    public static JedisKit getInstance() {
        return jedisKit;
    }

}
