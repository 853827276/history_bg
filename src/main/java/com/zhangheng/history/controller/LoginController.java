package com.zhangheng.history.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhangheng.history.domain.User;
import com.zhangheng.history.service.UserService;
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
			 //写入session
			 RequestContextHolderUtil.getSession().setAttribute(ResultEnum.USERSESSIONKEY.getMsg()+u.getId(), u);
			 RequestContextHolderUtil.setCookieValue(ResultEnum.USERCOOKIEKEY.getMsg(), u.getId(), null);
			 return ResultUtil.success(ResultEnum.SUCCESS);		 
		 }
		 return ResultUtil.success(ResultEnum.LOGINERROR);
	}
}
