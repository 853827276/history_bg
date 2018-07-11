package com.zhangheng.history.service;

import com.zhangheng.history.domain.User;
import com.zhangheng.history.util.LayerPage;

public interface UserService {

	public void save(User user);
	
	public User findById(String id);
	
	public User login(String userName,String password);
	
	public void updateStatus(String id,String status);
	
	public void delete(String id);
	
	public LayerPage<User> findPage(Integer pageNum,Integer pageSize,User u);
	
	public Integer updatePwd(String id,String oldPwd, String newPwd);
}
