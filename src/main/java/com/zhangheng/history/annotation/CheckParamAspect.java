package com.zhangheng.history.annotation;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.zhangheng.history.exception.MyException;
import com.zhangheng.history.util.AnnotationUtil;
import com.zhangheng.history.util.RequestContextHolderUtil;
import com.zhangheng.history.util.ResultEnum;

/**
 * 自定义校验参数切面
 * 
 * @author zhangh
 * @date 2018年4月26日上午10:03:32
 */
@Aspect
@Component
@Order(1)
public class CheckParamAspect {

	private static Logger logger = Logger.getLogger(CheckParamAspect.class);

	@Pointcut("@annotation(com.zhangheng.history.annotation.CheckParam)")
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
		Object[] params = proceedingJoinPoint.getArgs();
		boolean hasAuthor = params != null && params.length > 0 ? true : false;
		String uid = RequestContextHolderUtil.getCookieValue(ResultEnum.USERCOOKIEKEY.getMsg());
		if (hasAuthor && uid!=null) {// 模拟校验参数
			return proceedingJoinPoint.proceed();
		} else {
			logger.info("很抱歉  方法" + AnnotationUtil.getClassName(proceedingJoinPoint) + "."
					+ AnnotationUtil.getMethodName(proceedingJoinPoint) + "参数校验失败");
			throw new MyException(ResultEnum.PARAM_ERROR);
		}
	}
}
