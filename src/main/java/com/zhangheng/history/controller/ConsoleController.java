package com.zhangheng.history.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageInfo;
import com.zhangheng.history.domain.Message;
import com.zhangheng.history.domain.User;
import com.zhangheng.history.service.MessageService;
import com.zhangheng.history.service.MeunService;
import com.zhangheng.history.service.UserService;
import com.zhangheng.history.util.LayerPage;
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
	
	@Autowired
	private MessageService messageService;
	
	@RequestMapping("/index")
	public String index(Model model){
		String uid = RequestContextHolderUtil.getCookieValue(ResultEnum.USERCOOKIEKEY.getMsg());
		User u = userService.findById(uid);
		if(StringUtils.isEmpty(uid) ||null==u ||ResultEnum.USERTYPENOTADMIN.getMsg().equals(u.getIsAdmin())){
			return "redirect:/";
		}
		model.addAttribute("user", userService.findById(uid));
		model.addAttribute("leftMenu", meunService.foreachLeftMenu());
		return "console";
	}
	/**
	 * 后台用户主页
	 * @param pageNum
	 * @param pageSize
	 * @param u
	 * @param model
	 * @return
	 */
	@RequestMapping("/user/index")
	public String findUserIndex(Integer pageNum,Integer pageSize,User u,Model model){
		pageNum = pageNum==null?1:pageNum;
		pageSize =pageSize==null?20:pageSize;
		model.addAttribute("userList", userService.findPage(pageNum, pageSize, u));
		model.addAttribute("leftMenu", meunService.foreachLeftMenu());
		return "userList";
	}
	
	/**
	 * 分页查询用户列表
	 * @param page
	 * @param limit
	 * @param u
	 * @return
	 */
	@RequestMapping("/user/list")
	@ResponseBody
	public LayerPage<User> findUserPage(Integer page,Integer limit,User u){
		page = page==null?1:page;
		limit =limit==null?20:limit;
		return userService.findPage(page, limit, u);
	}
	
	/**
	 * 后台消息首页
	 * @param pageNum
	 * @param pageSize
	 * @param u
	 * @param model
	 * @return
	 */
	@RequestMapping("/message/index")
	public String findMessageIndex(Integer pageNum,Integer pageSize,Message message,Model model){
		String uid = RequestContextHolderUtil.getCookieValue(ResultEnum.USERCOOKIEKEY.getMsg());
		pageNum = pageNum==null?1:pageNum;
		pageSize =pageSize==null?20:pageSize;
		model.addAttribute("messageList", messageService.findPage(pageNum, pageSize, message));
		model.addAttribute("user", userService.findById(uid));
		model.addAttribute("leftMenu", meunService.foreachLeftMenu());
		return "messageList";
	}
	
	/**
	 * 分页查询留言
	 * @param page
	 * @param limit
	 * @param u
	 * @return
	 */
	@RequestMapping("/message/list")
	@ResponseBody
	public LayerPage<Message> findMessagePage(Integer page,Integer limit,Message message){
		page = page==null?1:page;
		limit =limit==null?20:limit;
		return messageService.findPage(page, limit, message);
	}
}
