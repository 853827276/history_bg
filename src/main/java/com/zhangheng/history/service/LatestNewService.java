package com.zhangheng.history.service;

import java.util.List;

import com.zhangheng.history.domain.LatestNew;
import com.zhangheng.history.util.LayerPage;

public interface LatestNewService {

	public List<LatestNew> findList(LatestNew latestNew);
	
	public List<LatestNew> findTop8();

	public LayerPage<LatestNew> findPage(Integer pageNum,Integer pageSize,LatestNew latestNew);
}
