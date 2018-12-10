package com.zhangheng.history.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zhangheng.history.service.UserService;
import com.zhangheng.history.util.ResultEnum;
import com.zhangheng.history.util.ResultInfo;
import com.zhangheng.history.util.ResultUtil;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	/**
	 * 修改密码
	 * @author zhangh
	 * @return 
	 * @date 2018年7月11日上午8:37:27
	 */
	@ResponseBody
	@RequestMapping("/updatePwd/{uid}")
	public ResultInfo<Object> updatePwd(@PathVariable String uid,String oldPwd,String newPwd){
		return ResultUtil.success(ResultEnum.SUCCESS, userService.updatePwd(uid, oldPwd, newPwd));
	}
	
}
