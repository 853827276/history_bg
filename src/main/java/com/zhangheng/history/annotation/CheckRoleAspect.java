package com.zhangheng.history.annotation;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.zhangheng.history.domain.User;
import com.zhangheng.history.exception.MyException;
import com.zhangheng.history.util.RedisUtil;
import com.zhangheng.history.util.RequestContextHolderUtil;
import com.zhangheng.history.util.ResultEnum;

/**
 * 自定义校验ADMIN角色切面
 * 
 * @author zhangh
 * @date 2018年6月26日上午10:03:32
 */
@Aspect
@Component
@Order(2)
public class CheckRoleAspect {
	@Autowired
	private RedisUtil redisUtil;
	
	private static Logger logger = Logger.getLogger(CheckRoleAspect.class);

	@Pointcut("@annotation(com.zhangheng.history.annotation.CheckRole)")
	public void pointCut() {

	}

	/**
	 * 切面回环处理 这个方法里不允许出现try catch 异常捕获 必须要往外抛
	 * 
	 * @param proceedingJoinPoint
	 * @return
	 * @throws Throwable
	 * @author zhangh
	 */
	@Around("pointCut()")
	public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		String uid = RequestContextHolderUtil.getCookieValue(ResultEnum.USERCOOKIEKEY.getMsg());
		User user =(User)redisUtil.get(ResultEnum.USERREDISKEY.getMsg()+uid);
		if (null!=user&&ResultEnum.USERTYPEADMIN.getMsg().equals(user.getIsAdmin())) {// 校验角色
			return proceedingJoinPoint.proceed();
		} else {
			logger.info("请先登陆");
			throw new MyException(ResultEnum.AUTHORIZATION_ERROR);
		}
	}
}