package com.zhangheng.history.service.impl;

import org.springframework.stereotype.Service;
import com.zhangheng.history.domain.RedisModel;
import com.zhangheng.history.service.IRedisService;

@Service("iRedisService")
public class RedisServiceImpl extends IRedisService<RedisModel>{
	 private static final String REDIS_KEY = "TEST_REDIS_KEY";

	    @Override
	    protected String getRedisKey() {
	        return REDIS_KEY;
	    }
}
