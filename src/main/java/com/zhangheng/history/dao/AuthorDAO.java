package com.zhangheng.history.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhangheng.history.domain.Author;

public interface AuthorDAO {

	public void save(@Param("author")Author author);
	public Author findById(String id);
	public void update(@Param("author")Author author);
	public void delete(String id);
	public void updateStatus(@Param("id")String id,@Param("status")String status);
	public List<Author> findList(@Param("author")Author author);
}
