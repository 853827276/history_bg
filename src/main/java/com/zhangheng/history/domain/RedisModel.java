package com.zhangheng.history.domain;

public class RedisModel {

	private String redisKey;
	private Object object;
	public String getRedisKey() {
		return redisKey;
	}
	public void setRedisKey(String redisKey) {
		this.redisKey = redisKey;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public RedisModel(String redisKey, Object object) {
		super();
		this.redisKey = redisKey;
		this.object = object;
	}
	public RedisModel() {
		super();
	}
	public RedisModel(Object object) {
		super();
		this.object = object;
	}
	
}
