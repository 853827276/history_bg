package com.zhangheng.history.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhangheng.history.domain.User;

public interface UserDAO {

	public User findByID(String id);

	public void save(User user);

	public void updateStatus(@Param("id") String id, @Param("status") String status);

	public User login(@Param("userName") String userName, @Param("password") String password);
	
	public List<User> findlist(@Param("u") User u);

}
