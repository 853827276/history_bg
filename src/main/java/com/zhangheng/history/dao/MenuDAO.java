package com.zhangheng.history.dao;

import java.util.List;

import com.zhangheng.history.domain.Menu;

public interface MenuDAO {

	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public Menu findById(String id);
	
	/**
	 * 根据Pid 查数据
	 * @param pid
	 * @return
	 */
	public List<Menu> findByPid(String pid);
}
