package com.zhangheng.history.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhangheng.history.domain.User;
import com.zhangheng.history.service.UserService;
import com.zhangheng.history.util.RedisUtil;
import com.zhangheng.history.util.RequestContextHolderUtil;
import com.zhangheng.history.util.ResultEnum;
import com.zhangheng.history.util.ResultInfo;
import com.zhangheng.history.util.ResultUtil;

/**
 * 登陆控制器
 * @author zhangh
 * @date 2018年7月6日下午3:14:30
 */

@RestController
public class LoginController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private RedisUtil redisUtil;
	/**
	 * 登陆接口	
	 * @author zhangh
	 * @date 2018年7月6日下午4:06:52
	 * @param userName
	 * @param password
	 * @return
	 */
	@RequestMapping("/login/{userName}/{password}")
	public ResultInfo<Object> login(@PathVariable String userName,@PathVariable String password){
		 User u = userService.login(userName, password);
		 if(u!=null){
			 RequestContextHolderUtil.setCookieValue(ResultEnum.USERCOOKIEKEY.getMsg(), u.getId(), -1);
			 //写入redis
			 redisUtil.set(ResultEnum.USERREDISKEY.getMsg()+u.getId(), u.getId(), 2*60*60l);
			 return ResultUtil.success(ResultEnum.SUCCESS);		 
		 }
		 return ResultUtil.success(ResultEnum.LOGINERROR);
	}
}
