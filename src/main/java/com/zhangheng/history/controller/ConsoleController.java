package com.zhangheng.history.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhangheng.history.service.MeunService;
import com.zhangheng.history.service.UserService;
import com.zhangheng.history.util.RequestContextHolderUtil;
import com.zhangheng.history.util.ResultEnum;

/**
 * 控制台管理类
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/console")
public class ConsoleController {
	@Autowired
	private UserService userService;
	@Autowired
	private MeunService meunService;
	
	@RequestMapping("/index")
	public String index(Model model){
		String uid = RequestContextHolderUtil.getCookieValue(ResultEnum.USERCOOKIEKEY.getMsg());
		model.addAttribute("user", userService.findById(uid));
		model.addAttribute("leftMenu", meunService.foreachLeftMenu());
		return "console";
	}
}
