package com.example.security.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.security.config.interceptor.JwtTokenInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {"classpath:/static",
			"classpath:/public", "classpath:/", "classpath:/resources", "classpath:/META-INF/resources",
			"classpath:/META-INF/resources/webjars"};
/*	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// /�� �ش��ϴ� url mapping�� /common/test�� forward�Ѵ�.
		registry.addViewController("/").setViewName("forward:/index");
		// �켱������ ���� ���� ��´�.
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}
*/	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtTokenInterceptor())
		.addPathPatterns("/user/findAll");
	}
	
	@Bean
	public FilterRegistrationBean<HeaderFilter> getFilterRegistrationBean(){
		FilterRegistrationBean<HeaderFilter> registrationBean = new FilterRegistrationBean<>(createHeaderFilter());
		registrationBean.setOrder(Integer.MIN_VALUE);
		registrationBean.addUrlPatterns("/*");
		return registrationBean;
	}
	
	@Bean
	public HeaderFilter createHeaderFilter() {
		return new HeaderFilter();
	}
	
	@Bean
	public JwtTokenInterceptor jwtTokenInterceptor() {
		return new JwtTokenInterceptor();
	}
	
	
	
}
