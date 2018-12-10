package com.zhangheng.history;

import java.util.Properties;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
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
	
	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() {
	    FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
	    FastJsonConfig fastJsonConfig = new FastJsonConfig();
	    fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
	    fastConverter.setFastJsonConfig(fastJsonConfig);
	    //必须加否则会报com.alibaba.fastjson.JSONException: autoType is not sup这个错        
	    ParserConfig.getGlobalInstance().addAccept("com.zhangheng.history.domain");
	    HttpMessageConverter<?> converter = fastConverter;
	    return new HttpMessageConverters(converter);
	}

}