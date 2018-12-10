package com.zhangheng.history.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.zhangheng.history.domain.Author;
import com.zhangheng.history.domain.LatestNew;
import com.zhangheng.history.domain.Message;
import com.zhangheng.history.domain.User;
import com.zhangheng.history.service.AuthorService;
import com.zhangheng.history.service.CarouselService;
import com.zhangheng.history.service.LatestNewService;
import com.zhangheng.history.service.MessageService;
import com.zhangheng.history.service.MeunService;
import com.zhangheng.history.service.UserService;
import com.zhangheng.history.util.LayerPage;
import com.zhangheng.history.util.RequestContextHolderUtil;
import com.zhangheng.history.util.ResultEnum;
import com.zhangheng.history.util.ResultInfo;
import com.zhangheng.history.util.ResultUtil;
import com.zhangheng.history.util.UUIDUtils;

/**
 * 控制台管理类
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/console")
public class ConsoleController {
	
	@Autowired UserService userService;
	@Autowired MeunService meunService;
	@Autowired AuthorService authorService;
	@Autowired CarouselService carouselService;
	@Autowired MessageService messageService;
	@Autowired LatestNewService latestNewService;
	
	@RequestMapping("/index")
	public String index(Model model) {
		String uid = RequestContextHolderUtil.getCookieValue(ResultEnum.USERCOOKIEKEY.getMsg());
		User u = userService.findById(uid);
		if (StringUtils.isEmpty(uid) || null == u || ResultEnum.USERTYPENOTADMIN.getMsg().equals(u.getIsAdmin())) {
			return "redirect:/index/";
		}
		model.addAttribute("user", userService.findById(uid));
		model.addAttribute("leftMenu", meunService.foreachLeftMenu());
		return "console";
	}

	/**
	 * 后台用户主页
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param u
	 * @param model
	 * @return
	 */
	@RequestMapping("/user/index")
	public String findUserIndex(Integer pageNum, Integer pageSize, User u, Model model) {
		pageNum = pageNum == null ? 1 : pageNum;
		pageSize = pageSize == null ? 20 : pageSize;
		model.addAttribute("userList", userService.findPage(pageNum, pageSize, u));
		model.addAttribute("leftMenu", meunService.foreachLeftMenu());
		return "userList";
	}

	/**
	 * 分页查询用户列表
	 * 
	 * @param page
	 * @param limit
	 * @param u
	 * @return
	 */
	@RequestMapping("/user/list")
	@ResponseBody
	public LayerPage<User> findUserPage(Integer page, Integer limit, User u) {
		page = page == null ? 1 : page;
		limit = limit == null ? 20 : limit;
		return userService.findPage(page, limit, u);
	}
	/**
	 * 轮播图首页
	 * @author zhangh
	 * @date 2018年7月11日上午9:26:18
	 */
	@RequestMapping("/carouse/index")
	public String carouseIndex(Model model){
		String uid = RequestContextHolderUtil.getCookieValue(ResultEnum.USERCOOKIEKEY.getMsg());
		model.addAttribute("user", userService.findById(uid));
		model.addAttribute("leftMenu", meunService.foreachLeftMenu());
		model.addAttribute("carouseList", carouselService.queryList());
		return "carouseList";
	}

	/**
	 * 后台留言首页
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param u
	 * @param model
	 * @return
	 */
	@RequestMapping("/message/index")
	public String findMessageIndex(Integer pageNum, Integer pageSize, Message message, Model model) {
		String uid = RequestContextHolderUtil.getCookieValue(ResultEnum.USERCOOKIEKEY.getMsg());
		pageNum = pageNum == null ? 1 : pageNum;
		pageSize = pageSize == null ? 20 : pageSize;
		model.addAttribute("messageList", messageService.findPage(pageNum, pageSize, message));
		model.addAttribute("user", userService.findById(uid));
		model.addAttribute("leftMenu", meunService.foreachLeftMenu());
		return "messageList";
	}

	/**
	 * 分页查询留言
	 * 
	 * @param page
	 * @param limit
	 * @param u
	 * @return
	 */
	@RequestMapping("/message/list")
	@ResponseBody
	public LayerPage<Message> findMessagePage(Integer page, Integer limit, Message message) {
		page = page == null ? 1 : page;
		limit = limit == null ? 20 : limit;
		return messageService.findPage(page, limit, message);
	}

	/**
	 * 后台作者首页
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param u
	 * @param model
	 * @return
	 */
	@RequestMapping("/author/index")
	public String findAuthorIndex(Integer pageNum, Integer pageSize, Author author, Model model) {
		String uid = RequestContextHolderUtil.getCookieValue(ResultEnum.USERCOOKIEKEY.getMsg());
		pageNum = pageNum == null ? 1 : pageNum;
		pageSize = pageSize == null ? 20 : pageSize;
		model.addAttribute("authorList", authorService.findPage(pageNum, pageSize, author));
		model.addAttribute("user", userService.findById(uid));
		model.addAttribute("leftMenu", meunService.foreachLeftMenu());
		return "authorList";
	}

	/**
	 * 分页查询作者
	 * 
	 * @param page
	 * @param limit
	 * @param u
	 * @return
	 */
	@RequestMapping("/author/list")
	@ResponseBody
	public LayerPage<Message> findAuthorPage(Integer page, Integer limit, Author author) {
		page = page == null ? 1 : page;
		limit = limit == null ? 20 : limit;
		return authorService.findPage(page, limit, author);
	}

	/**
	 * 添加一个作者
	 * 
	 * @param author
	 * @return
	 */
	@RequestMapping("/author/add")
	@ResponseBody
	public ResultInfo<Object> addAuthor(Author author) {
		author.setId(UUIDUtils.getId());
		authorService.save(author);
		return ResultUtil.success(ResultEnum.SUCCESS);
	}

	/**
	 * 更新一个作者
	 * 
	 * @param author
	 * @return
	 */
	@RequestMapping("/author/update")
	public ResultInfo<Object> updateAuthor(Author author) {
		authorService.update(author);
		return ResultUtil.success(ResultEnum.SUCCESS);
	}

	/**
	 * 根据主键查询一个作者信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/author/{id}")
	public ResultInfo<Object> findAuthor(@PathVariable String id) {
		return ResultUtil.success(ResultEnum.SUCCESS, authorService.findById(id));
	}
	
	/**
	 * 添加资料页面
	 * @author zhangh
	 * @date 2018年7月25日上午9:03:37
	 */
	@RequestMapping("/gotoAddMterial")
	public String gotoAddMterial(Model model){
		String uid = RequestContextHolderUtil.getCookieValue(ResultEnum.USERCOOKIEKEY.getMsg());
		model.addAttribute("user", userService.findById(uid));
		model.addAttribute("leftMenu", meunService.foreachLeftMenu());
		return "addMterial";
	}
	
	/**
	 * 新闻动态
	 * @author zhangh
	 * @date 2018年12月10日上午11:07:33
	 * @param pageNum
	 * @param pageSize
	 * @param latestNew
	 * @param model
	 * @return
	 */
	@RequestMapping("/latestNew/index")
	public String findlatestNewIndex(Integer pageNum, Integer pageSize, LatestNew latestNew, Model model) {
		String uid = RequestContextHolderUtil.getCookieValue(ResultEnum.USERCOOKIEKEY.getMsg());
		pageNum = pageNum == null ? 1 : pageNum;
		pageSize = pageSize == null ? 20 : pageSize;
		model.addAttribute("latestNews", latestNewService.findPage(pageNum, pageSize, latestNew));
		model.addAttribute("leftMenu", meunService.foreachLeftMenu());
		model.addAttribute("user", userService.findById(uid));
		return "latestNew";
	}
}
