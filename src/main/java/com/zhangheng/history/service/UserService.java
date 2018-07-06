package com.zhangheng.history.service;

import com.zhangheng.history.domain.User;

public interface UserService {

	public void save(User user);
	
	public User findById(String id);
	
	public User login(String userName,String password);
}
