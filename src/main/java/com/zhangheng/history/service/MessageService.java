package com.zhangheng.history.service;

import java.util.List;

import com.zhangheng.history.domain.Message;
import com.zhangheng.history.util.LayerPage;

public interface MessageService {

	public List<Message> queryTop5();
	
	/**
	 * 保存数据
	 * @author zhangh
	 * @date 2018年7月9日上午8:10:29
	 * @param message
	 */
	public void save(Message message);
	
	public LayerPage<Message> findPage(Integer pageNum,Integer pageSize,Message message);
}
