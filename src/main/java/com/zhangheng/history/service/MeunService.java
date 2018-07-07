package com.zhangheng.history.service;

import java.util.List;

import com.zhangheng.history.domain.Menu;

public interface MeunService {

	/**
	 * 得到左侧导航菜单
	 * @return
	 */
	public List<Menu> foreachLeftMenu();
	
	public List<Menu> findByPid(String pid);
	
}
