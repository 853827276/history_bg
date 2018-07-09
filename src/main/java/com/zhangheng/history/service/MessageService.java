package com.zhangheng.history.service;

import java.util.List;

import com.zhangheng.history.domain.Message;

public interface MessageService {

	public List<Message> querylist(Integer limit);
	
	/**
	 * 保存数据
	 * @author zhangh
	 * @date 2018年7月9日上午8:10:29
	 * @param message
	 */
	public void save(Message message);
}
