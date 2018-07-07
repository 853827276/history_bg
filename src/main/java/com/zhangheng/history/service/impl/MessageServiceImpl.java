package com.zhangheng.history.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangheng.history.dao.MessageDAO;
import com.zhangheng.history.domain.Message;
import com.zhangheng.history.service.MessageService;
@Service("messageService")
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDAO messageDAO;

	@Override
	public List<Message> querylist(String limit) {
		return messageDAO.querylist(limit);
	}
	
	
}
