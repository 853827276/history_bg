package com.zhangheng.history.util;

import java.util.UUID;

/**
 * 随机生成字符串工具类
 */
public class UUIDUtils {
	/**
	 * 随机生成id
	 * 
	 * @return
	 */
	public static String getId() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
