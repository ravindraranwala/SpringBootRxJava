package com.example.config;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.handlers.ObservableReturnValueHandler;

@Configuration
@ComponentScan(basePackages = { "com.example.*" })
public class WebConfig extends WebMvcConfigurerAdapter {
	// @Override
	// public void addResourceHandlers(ResourceHandlerRegistry registry) {
	// registry.addResourceHandler("/resources/**").addResourceLocations("static");
	// }

	@Override
	public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
		returnValueHandlers.add(new ObservableReturnValueHandler());
	}

	// public void configureMessageConverters(List<HttpMessageConverter<?>>
	// converters) {
	// Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
	// builder.indentOutput(true).dateFormat(new
	// SimpleDateFormat("yyyy-MM-dd"));
	// converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
	// }
}
