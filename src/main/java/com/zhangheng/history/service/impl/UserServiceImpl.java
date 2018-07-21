package com.zhangheng.history.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangheng.history.dao.UserDAO;
import com.zhangheng.history.domain.User;
import com.zhangheng.history.service.UserService;
import com.zhangheng.history.util.LayerPage;
import com.zhangheng.history.util.UUIDUtils;

@Service("userService")
//@CacheConfig(cacheNames = "users")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public void save(User user) {
		user.setId(UUIDUtils.getId());
		userDAO.save(user);
	}

	@Override
	@Cacheable(value="[user]",key = "#p0")
	public User findById(String id) {
		return userDAO.findByID(id);
	}

	@Override
	public User login(String userName, String password) {
		return userDAO.login(userName, password);
	}

	@Override
//	@CachePut(key = "#p0")
	public void updateStatus(String id, String status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	// 如果指定为 true，则方法调用后将立即清空所有缓存
//	@CacheEvict(key = "#p0", allEntries = true)
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LayerPage<User> findPage(Integer pageNum, Integer pageSize, User u) {
		LayerPage<User> layerPage = new LayerPage<User>();
		Page<User> pag = PageHelper.startPage(pageNum, pageSize);
		userDAO.findlist(u);
		PageInfo<User> pageInfo = new PageInfo<>(pag);
		layerPage.setCode("success");
		layerPage.setMsg("查询成功");
		layerPage.setCount(pageInfo.getTotal());
		layerPage.setData(pageInfo.getList());
		return layerPage;
	}

	@Override
	public Integer updatePwd(String id, String oldPwd, String newPwd) {
		return userDAO.updatePwd(id, oldPwd, newPwd);
	}

}
