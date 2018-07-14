package com.zhangheng.history.service;

import java.util.List;

import com.zhangheng.history.domain.Author;
import com.zhangheng.history.domain.Message;
import com.zhangheng.history.util.LayerPage;

public interface AuthorService {

	public void save(Author author);
	public Author findById(String id);
	public void update(Author author);
	public void delete(String id);
	public void updateStatus(String id,String status);
	public List<Author> findList(Author author);
	public LayerPage<Message> findPage(Integer pageNum,Integer pageSize,Author author);
}
