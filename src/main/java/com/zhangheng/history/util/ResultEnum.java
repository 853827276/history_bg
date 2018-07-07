package com.zhangheng.history.util;

/**
 * 封装接口返回状态结果集
 * 
 * @author zhangh
 * @date 2018年4月26日下午2:34:37
 */
public enum ResultEnum {
	
	/**
	 * 菜单的默认PID
	 */
	MENUPID(-1,"-1"),
	
	/**用户session key前缀*/
	USERSESSIONKEY(1,"history_session_"),
	USERCOOKIEKEY(1,"history_cookie_"),
	USERREDISKEY(1,"history_redis_"),
	LOGINERROR(-1,"账号或密码不对"),
	
	USEREXPIRE(-1,"用户已过期，请重新登陆"),
	USERTYPEADMIN(1,"1"),
	USERTYPENOTADMIN(2,"0"),
	JOBSTATUSFINISH(1,"已完成"),
	JOBSTATUSNOBEGIN(0,"未开始"),
	JOBSTATUSPAUSE(2,"已暂停"),
	JOBSTATUSSTOP(3,"已停止"),
	JOBSTATUSERR(4,"发送错误"),
	JOBSTATUSPROGRESSING(5,"进行中"),
	JOBSTATUSWATING(6,"等待着"),
	
	SDJOB(1,"手动启停任务类型"),
	SJDJOB(2,"时间段任务类型"),
	CRONJOB(3,"cron任务类型"),
	
	SUCCESS(1, "成功"), 
	PARAM_ERROR(-1, "参数异常"), 
	AUTHORIZATION_ERROR(-2, "对不起，您的权限不够"), 
	DADABASE_ERROR(-3,"网络超时"), 
	UNKOWN_ERROR(-99, "未知错误");//这里的信息以程序捕获的具体异常信息为主

	private Integer code;
	private String msg;

	ResultEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
