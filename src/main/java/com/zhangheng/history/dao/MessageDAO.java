package com.zhangheng.history.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhangheng.history.domain.Message;

public interface MessageDAO {

	public List<Message> querylist(@Param("message")Message message);
	
	/**
	 * 查出最新的五条数据
	 * @return
	 */
	public List<Message> queryTop5();
	
	/**
	 * 保存数据
	 * @author zhangh
	 * @date 2018年7月9日上午8:10:29
	 * @param message
	 */
	public void save(Message message);
}
