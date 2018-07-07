package com.zhangheng.history.service;

import java.util.List;

import com.zhangheng.history.domain.Message;

public interface MessageService {

	public List<Message> querylist(String limit);
}
