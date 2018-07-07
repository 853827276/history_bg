package com.zhangheng.history.dao;

import java.util.List;

import com.zhangheng.history.domain.Message;

public interface MessageDAO {

	public List<Message> querylist(String limit);
}
