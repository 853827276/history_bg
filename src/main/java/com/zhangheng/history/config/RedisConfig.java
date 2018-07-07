package com.zhangheng.history.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

	// 缓存管理器
	@Bean
	public CacheManager cacheManager(RedisTemplate<?,?> redisTemplate) {
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
		// 设置缓存过期时间
		//cacheManager.setDefaultExpiration(10000);
		return cacheManager;
	}

	/*@Bean
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory) {
	       RedisTemplate<Object, Object> template = new RedisTemplate<>();
	        template.setConnectionFactory(factory);

	        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
	        @SuppressWarnings({ "rawtypes", "unchecked" })
			Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);

	        ObjectMapper mapper = new ObjectMapper();
	        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
	        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
	        serializer.setObjectMapper(mapper);

	        template.setValueSerializer(serializer);
	        //使用StringRedisSerializer来序列化和反序列化redis的key值
//	        template.setKeySerializer(new StringRedisSerializer());
	        template.afterPropertiesSet();
	        return template;
	}
    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(factory);
        return stringRedisTemplate;
    }*/
    
    
    @Bean(name = "springSessionDefaultRedisSerializer")
    public GenericJackson2JsonRedisSerializer getGenericJackson2JsonRedisSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }
    @Bean
    public RedisTemplate<String, Object> getRedisTemplate(
            JedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        return redisTemplate;
    }
}
