package com.zhangheng.history.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangheng.history.dao.MessageDAO;
import com.zhangheng.history.domain.Message;
import com.zhangheng.history.service.MessageService;
import com.zhangheng.history.util.LayerPage;
@Service("messageService")
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDAO messageDAO;

	@Override
	public List<Message> queryTop5() {
		return messageDAO.queryTop5();
	}

	@Override
	public void save(Message message) {
		messageDAO.save(message);
	}

	@Override
	public LayerPage<Message> findPage(Integer pageNum, Integer pageSize,
			Message message) {
		LayerPage<Message> layerPage = new LayerPage<Message>();
		Page<Message> pag = PageHelper.startPage(pageNum, pageSize);
		messageDAO.querylist(message);
		PageInfo<Message> pageInfo = new PageInfo<>(pag);
		layerPage.setCode("success");
		layerPage.setMsg("查询成功");
		layerPage.setCount(pageInfo.getTotal());
		layerPage.setData(pageInfo.getList());
		return layerPage;
	}
	
	
}
