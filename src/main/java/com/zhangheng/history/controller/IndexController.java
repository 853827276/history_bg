package com.zhangheng.history.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhangheng.history.service.LatestNewService;
import com.zhangheng.history.service.MessageService;

/**
 * 首页请求控制器
 * @author zhangh
 * @date 2018年7月6日下午3:13:19
 */
@Controller
public class IndexController {
	
	@Autowired MessageService messageService;
	@Autowired LatestNewService latestNewService;
	
	/**
	 * 首页
	 * @param model
	 * @return
	 */
	@RequestMapping("/")
	public String index(Model model){
		model.addAttribute("messages", messageService.queryTop5());
		model.addAttribute("latestNews", latestNewService.findTop8());
		return "index";
	}
	
}
