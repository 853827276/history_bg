package com.zhangheng.history.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangheng.history.dao.LatestNewDAO;
import com.zhangheng.history.domain.LatestNew;
import com.zhangheng.history.service.LatestNewService;
import com.zhangheng.history.util.LayerPage;
@Service
public class LatestNewServiceImpl implements LatestNewService {

	@Autowired LatestNewDAO  latestNewDAO;
	@Override
	public List<LatestNew> findList(LatestNew latestNew) {
		return latestNewDAO.findList(latestNew);
	}
	@Override
	public List<LatestNew> findTop8() {
		PageHelper.startPage(0, 8);
		return latestNewDAO.findList(null);
	}
	@Override
	public LayerPage<LatestNew> findPage(Integer pageNum, Integer pageSize, LatestNew latestNew) {
		LayerPage<LatestNew> layerPage = new LayerPage<LatestNew>();
		Page<LatestNew> pag = PageHelper.startPage(pageNum, pageSize);
		latestNewDAO.findList(latestNew);
		PageInfo<LatestNew> pageInfo = new PageInfo<>(pag);
		layerPage.setCode("success");
		layerPage.setMsg("查询成功");
		layerPage.setCount(pageInfo.getTotal());
		layerPage.setData(pageInfo.getList());
		return layerPage;
	}

}
