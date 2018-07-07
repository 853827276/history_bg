package com.zhangheng.history.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.ListUtils;

import com.alibaba.fastjson.JSON;
import com.zhangheng.history.dao.MenuDAO;
import com.zhangheng.history.domain.Menu;
import com.zhangheng.history.service.MeunService;
import com.zhangheng.history.util.ResultEnum;

@Service("meunService")
public class MeunServiceImpl implements MeunService {

	@Autowired
	private MenuDAO menuDAO;

	@Override
	public List<Menu> foreachLeftMenu() {
		List<Menu> topMenus = menuDAO.findByPid(ResultEnum.MENUPID.getMsg());
		String json = JSON.toJSONString(lookup(topMenus));
		System.out.println(json);
		return lookup(topMenus);
	}
	private List<Menu> lookup(List<Menu> topMenus) {
		if (!ListUtils.isEmpty(topMenus)) {
			topMenus.forEach(item -> queryPList(item));
		}
		return topMenus;
	}
	
	private List<Menu> queryPList(Menu menu){
		List<Menu> menus =menuDAO.findByPid(menu.getId()); 
		if (!ListUtils.isEmpty(menus)) {
			menu.setPlist(menus);
			for (Menu m : menus) {
				queryPList(m);
			}			
		}
		return menus;
	}

	@Override
	public List<Menu> findByPid(String pid) {
		return menuDAO.findByPid(pid);
	}

}
