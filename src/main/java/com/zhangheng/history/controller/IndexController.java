package com.zhangheng.history.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhangheng.history.domain.Message;
import com.zhangheng.history.redis.RedisUtil;
import com.zhangheng.history.service.CarouselService;
import com.zhangheng.history.service.LatestNewService;
import com.zhangheng.history.service.MessageService;
import com.zhangheng.history.service.UserService;
import com.zhangheng.history.util.RequestContextHolderUtil;
import com.zhangheng.history.util.ResultEnum;
import com.zhangheng.history.util.ResultInfo;
import com.zhangheng.history.util.ResultUtil;
import com.zhangheng.history.util.UUIDUtils;

/**
 * 首页请求控制器
 * @author zhangh
 * @date 2018年7月6日下午3:13:19
 */
@Controller
@RequestMapping("/index")
public class IndexController {
	@Autowired UserService userService;
	@Autowired RedisUtil redisUtil;
	@Autowired CarouselService carouselService;
	
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
	/**
	 * 退出
	 * @return
	 */
	@RequestMapping("/quit")
	public String quit(){
		String uid = RequestContextHolderUtil.getCookieValue(ResultEnum.USERCOOKIEKEY.getMsg());
		redisUtil.remove(ResultEnum.USERREDISKEY.getMsg()+uid);
		RequestContextHolderUtil.setCookieValue(ResultEnum.USERCOOKIEKEY.getMsg(), null, 0);
		return "redirect:/index/";
	}
	
	/**
	 * 轮播列表
	 * @author zhangh
	 * @date 2018年7月6日下午4:40:30
	 * @return
	 */
	@ResponseBody
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
	@ResponseBody
	@RequestMapping("/findById")
	public ResultInfo<Object> findById(){
		String uid = RequestContextHolderUtil.getCookieValue(ResultEnum.USERCOOKIEKEY.getMsg());
		Object redis_user = redisUtil.get(ResultEnum.USERREDISKEY.getMsg()+uid) ;
		if(uid!=null && redis_user != null){
			return ResultUtil.success(ResultEnum.SUCCESS,userService.findById(uid));			
		}else{
			return ResultUtil.error(ResultEnum.USEREXPIRE);		
		}
	}
	
	/**
	 * 添加留言
	 * @author zhangh
	 * @date 2018年7月9日上午8:17:43
	 * @param message
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addMessage")
	public ResultInfo<Object> addMessage(Message message){
		message = message==null?new Message():message;
		message.setId(UUIDUtils.getId());
		messageService.save(message);
		return ResultUtil.success(ResultEnum.SUCCESS);	
	}
}
