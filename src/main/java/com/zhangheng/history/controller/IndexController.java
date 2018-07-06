package com.zhangheng.history.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhangheng.history.domain.User;
import com.zhangheng.history.service.CarouselService;
import com.zhangheng.history.service.UserService;
import com.zhangheng.history.util.RequestContextHolderUtil;
import com.zhangheng.history.util.ResultEnum;
import com.zhangheng.history.util.ResultInfo;
import com.zhangheng.history.util.ResultUtil;

/**
 * 首页请求控制器
 * @author zhangh
 * @date 2018年7月6日下午3:13:19
 */
@RestController
@RequestMapping("/index")
public class IndexController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private CarouselService carouselService;
	
	/**
	 * 轮播列表
	 * @author zhangh
	 * @date 2018年7月6日下午4:40:30
	 * @return
	 */
	@RequestMapping("/carouselList")
	public ResultInfo<Object> carouselList(){
		return ResultUtil.success(ResultEnum.SUCCESS,carouselService.queryList());
	}
	
	/**
	 * 获取用户个人信息
	 * @author zhangh
	 * @date 2018年7月6日下午4:23:21
	 * @return
	 */
	@RequestMapping("/findById")
	public ResultInfo<Object> findById(){
		
		String uid = RequestContextHolderUtil.getCookieValue(ResultEnum.USERCOOKIEKEY.getMsg());
		User user = (User) RequestContextHolderUtil.getSession().getAttribute(ResultEnum.USERSESSIONKEY.getMsg()+uid);
		return ResultUtil.success(ResultEnum.SUCCESS,user);
	}
}
