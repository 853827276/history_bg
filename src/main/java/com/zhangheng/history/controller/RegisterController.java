package com.zhangheng.history.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhangheng.history.domain.User;
import com.zhangheng.history.service.UserService;
import com.zhangheng.history.util.ResultEnum;
import com.zhangheng.history.util.ResultInfo;
import com.zhangheng.history.util.ResultUtil;

/**
 * 注册管理类
 * @author zhangh
 * @date 2018年7月6日下午3:15:22
 */
public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 注册接口
	 * @author zhangh
	 * @date 2018年7月6日下午4:12:09
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/register",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResultInfo<Object> register(User user){
		userService.save(user);
		return ResultUtil.success(ResultEnum.SUCCESS);
	}
}
