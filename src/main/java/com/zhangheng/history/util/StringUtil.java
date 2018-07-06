package com.zhangheng.history.util;

public class StringUtil {

	/**
	 * 从指定格式中获取小时和分钟 秒
	 * @author zhangh
	 * @date 2018年5月25日上午11:25:38
	 * @param startTime
	 * @return
	 */
	public static int getHour(String startTime){
		int hour=0;
		if(null!=startTime&&startTime.length()>0){
			hour=Integer.parseInt(startTime.split(":")[0]);
		}
		return hour;
	}
	public static int getMinuter(String startTime){
		int minuter=0;
		if(null!=startTime&&startTime.length()>0){
			minuter=Integer.parseInt(startTime.split(":")[1]);
		}
		return minuter;
	}
	public static int getSecond(String startTime){
		int second=0;
		if(null!=startTime&&startTime.length()>0){
			second=Integer.parseInt(startTime.split(":")[2]);
		}
		return second;
	}
	public static boolean isNotEmpty(String str){
		return str!=null&&str.length()>0?true:false;
	}
	/*public static void main(String[] args) {
		System.out.println(getHour("00：00：00")+"-"+getMinuter("00：00：00")+"-"+getSecond("00：00：00"));
	}*/
}
