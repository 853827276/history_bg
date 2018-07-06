package com.zhangheng.history.exception;

import com.zhangheng.history.util.ResultEnum;

public class MyException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5126369846766704829L;
	private Integer code;

	public MyException(ResultEnum resultEnum) {
		super(resultEnum.getMsg());
		this.code = resultEnum.getCode();
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}
