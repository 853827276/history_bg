package com.zhangheng.history;

import java.util.Properties;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import com.github.pagehelper.PageHelper;

/**
 * 程序启动入口
 * 
 * @author zhangh
 * @date 2018年5月7日上午10:36:58
 */
@SpringBootApplication
@MapperScan("com.zhangheng.history.dao")
@EnableCaching
public class Application {

	/**
	 * 程序入口
	 * 
	 * @author zhangh
	 * @date 2018年5月7日上午10:37:09
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public PageHelper pageHelper() {
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("rowBoundsWithCount", "true");
		properties.setProperty("reasonable", "true");
		properties.setProperty("dialect", "mysql");
		pageHelper.setProperties(properties);
		return pageHelper;
	}
}