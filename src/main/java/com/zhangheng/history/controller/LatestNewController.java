package com.zhangheng.history.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhangheng.history.service.LatestNewService;

@Controller
@RequestMapping("/latestNew")
public class LatestNewController {

	@Autowired LatestNewService LatestNewService;
	
}
