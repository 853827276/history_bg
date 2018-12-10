package com.zhangheng.history.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangheng.history.dao.AuthorDAO;
import com.zhangheng.history.domain.Author;
import com.zhangheng.history.domain.Message;
import com.zhangheng.history.service.AuthorService;
import com.zhangheng.history.util.LayerPage;

@Service("authorService")
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorDAO authorDAO; 
	@Override
	public void save(Author author) {
		authorDAO.save(author);
	}

	@Override
	public Author findById(String id) {
		return authorDAO.findById(id);
	}

	@Override
	public void update(Author author) {
		authorDAO.update(author);
	}

	@Override
	public void delete(String id) {
		authorDAO.delete(id);
	}

	@Override
	public void updateStatus(String id, String status) {
		authorDAO.updateStatus(id, status);
	}

	@Override
	public List<Author> findList(Author author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LayerPage<Message> findPage(Integer pageNum, Integer pageSize,Author author) {
		LayerPage<Message> layerPage = new LayerPage<Message>();
		Page<Message> pag = PageHelper.startPage(pageNum, pageSize);
		authorDAO.findList(author);
		PageInfo<Message> pageInfo = new PageInfo<>(pag);
		layerPage.setCode("success");
		layerPage.setMsg("查询成功");
		layerPage.setCount(pageInfo.getTotal());
		layerPage.setData(pageInfo.getList());
		return layerPage;
	}

}
