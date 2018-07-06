package com.zhangheng.history.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangheng.history.dao.UserDAO;
import com.zhangheng.history.domain.User;
import com.zhangheng.history.service.UserService;
import com.zhangheng.history.util.UUIDUtils;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public void save(User user) {
		user.setId(UUIDUtils.getId());
		userDAO.save(user);
	}

	@Override
	public User findById(String id) {
		return userDAO.findByID(id);
	}

	@Override
	public User login(String userName, String password) {
		return userDAO.login(userName, password);
	}

}
