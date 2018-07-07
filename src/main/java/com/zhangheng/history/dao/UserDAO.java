package com.zhangheng.history.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.zhangheng.history.domain.User;

//@CacheConfig(cacheNames = "users")
public interface UserDAO {

//	@Cacheable(key = "#p0")
	public User findByID(String id);

	public void save(User user);

//	@CachePut(key = "#p0")
	public void updateStatus(@Param("id") String id, @Param("status") String status);

	public User login(@Param("userName") String userName, @Param("password") String password);

	// 如果指定为 true，则方法调用后将立即清空所有缓存
//	@CacheEvict(key = "#p0", allEntries = true)
	public void delete(String id);
}
