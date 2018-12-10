package com.zhangheng.history.dao;

import java.util.List;

import com.zhangheng.history.domain.LatestNew;

public interface LatestNewDAO {

	
	public LatestNew findById(String id);
	
	public void save(LatestNew latestNew);
	
	public void update(LatestNew latestNew);

	public int delete(String id);
	
	public List<LatestNew> findList(LatestNew latestNew);
}
