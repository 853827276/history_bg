package com.zhangheng.history.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zhangheng.history.domain.User;
import com.zhangheng.history.service.CarouselService;
import com.zhangheng.history.service.MessageService;
import com.zhangheng.history.service.UserService;
import com.zhangheng.history.util.RedisUtil;
import com.zhangheng.history.util.RequestContextHolderUtil;
import com.zhangheng.history.util.ResultEnum;
import com.zhangheng.history.util.ResultInfo;
import com.zhangheng.history.util.ResultUtil;

/**
 * 首页请求控制器
 * @author zhangh
 * @date 2018年7月6日下午3:13:19
 */
@Controller
public class IndexController {
	@Autowired
	private UserService userService;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private CarouselService carouselService;
	
	@Autowired
	private MessageService messageService;
	
	/**
	 * 首页
	 * @param model
	 * @return
	 */
	@RequestMapping("/")
	public String index(Model model){
		model.addAttribute("messages", messageService.querylist("3"));
		return "index";
	}
	/**
	 * 退出
	 * @return
	 */
	@RequestMapping("/quit")
	public String quit(){
		String uid = RequestContextHolderUtil.getCookieValue(ResultEnum.USERCOOKIEKEY.getMsg());
		redisUtil.remove(ResultEnum.USERREDISKEY.getMsg()+uid);
		RequestContextHolderUtil.setCookieValue(ResultEnum.USERCOOKIEKEY.getMsg(), null, 0);
		return "redirect:/";
	}
	
	/**
	 * 轮播列表
	 * @author zhangh
	 * @date 2018年7月6日下午4:40:30
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/index/carouselList")
	public ResultInfo<Object> carouselList(){
		return ResultUtil.success(ResultEnum.SUCCESS,carouselService.queryList());
	}
	
	/**
	 * 获取用户个人信息
	 * @author zhangh
	 * @date 2018年7月6日下午4:23:21
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/index/findById")
	public ResultInfo<Object> findById(){
		String uid = RequestContextHolderUtil.getCookieValue(ResultEnum.USERCOOKIEKEY.getMsg());
		String redis_uid = (String) redisUtil.get(ResultEnum.USERREDISKEY.getMsg()+uid);
		if(uid.equals(redis_uid)){
			return ResultUtil.success(ResultEnum.SUCCESS,userService.findById(uid));			
		}else{
			return ResultUtil.error(ResultEnum.USEREXPIRE);		
		}
	}
}
