package com.example.demo.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.config.filter.CustomAuthenticationFilter;
import com.example.demo.handler.CustomLoginSuccessHandler;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	//���� ���ҽ��� ���ؼ��� Security ������ �������� ����
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable(); 
		
		http.authorizeRequests()
		// "/"�� ��� ���� ��� ���� ����
			.antMatchers("/", "/login").permitAll()
		//��ū�� Ȱ���ϴ� ��� ��� ��û�� ���� ������ �����ϵ��� ��
			.anyRequest().authenticated()
			.and()
		//��ū�� Ȱ���ϸ� ������ �ʿ� �����Ƿ� STATELESS�� �����Ͽ� Session�� ������� �ʴ´�.
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
		//�� ��� �α��� ��Ȱ��ȭ
			.formLogin()
				.disable()
				.addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		
	}
	
	//���� �ؽ� ������� �ʱ� ���� ��ť��Ƽ�� BCryptPasswordEncoder ���.
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public CustomAuthenticationFilter customAuthenticationFilter() throws Exception{
		CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager());
		customAuthenticationFilter.setFilterProcessesUrl("/user/login");
		customAuthenticationFilter.setAuthenticationSuccessHandler(customLoginSuccessHandler());
		customAuthenticationFilter.afterPropertiesSet();
		return customAuthenticationFilter;
		
	}
	
	@Bean
	public CustomLoginSuccessHandler customLoginSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}
	
	@Bean
	public CustomAuthenticationProvider customAuthenticationProvider() {
		return new CustomAuthenticationProvider(bCryptPasswordEncoder());
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) {
		authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider());
	}
	

	
	

}
