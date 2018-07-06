package com.zhangheng.history.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangheng.history.dao.CarouselDAO;
import com.zhangheng.history.domain.Carousel;
import com.zhangheng.history.service.CarouselService;
@Service("carouselService")
public class CarouselServiceImpl implements CarouselService{

	@Autowired
	private CarouselDAO carouselDAO;

	@Override
	public List<Carousel> queryList() {
		return carouselDAO.queryList();
	}
	
	
}
