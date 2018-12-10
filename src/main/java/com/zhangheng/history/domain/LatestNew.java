package com.zhangheng.history.domain;

import java.io.Serializable;
import java.util.Date;

public class LatestNew implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2006966692347111195L;
	private String id;
	private String content;
	private int sort;
	private Date addTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
}
