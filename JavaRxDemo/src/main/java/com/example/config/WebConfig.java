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
	@Override
	public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
		returnValueHandlers.add(new ObservableReturnValueHandler());
	}
}
