package com.zhangheng.history.domain;

import java.util.Date;

/**
 * 图片轮播类
 * @author zhangh
 * @date 2018年7月6日下午4:26:39
 */
public class Carousel {

	private String id;
	private String url;
	private Integer sort;
	private String status;
	private Date joinTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getJoinTime() {
		return joinTime;
	}
	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}
	
	
	
}
