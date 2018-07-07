package com.zhangheng.history.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zhangheng.history.util.RedisUtil;
import com.zhangheng.history.util.RequestContextHolderUtil;
import com.zhangheng.history.util.ResultEnum;

/**
 * 登陆拦截器
 * @author zhangheng
 *
 */
//@Component
@WebFilter(urlPatterns="[/console/*]",filterName="loginFilter")
public class LoginFilter implements Filter {
	@Autowired
	private RedisUtil redisUtil;
	
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request= (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;
		String uid = RequestContextHolderUtil.getCookieValue(ResultEnum.USERCOOKIEKEY.getMsg());
		String redis_uid = (String) redisUtil.get(ResultEnum.USERREDISKEY.getMsg()+uid);
		if(uid!=null && redis_uid!=null &&redis_uid.equals(uid)){			
			arg2.doFilter(request, response);
		}else{
			response.sendRedirect("/");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
