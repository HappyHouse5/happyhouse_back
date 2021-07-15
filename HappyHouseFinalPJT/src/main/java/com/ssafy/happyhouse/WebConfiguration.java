package com.ssafy.happyhouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.happyhouse.common.LoginInterceptor;

@Configuration
public class WebConfiguration implements WebMvcConfigurer{
	
	@Autowired
	LoginInterceptor loginInterceptor;
	
	public void addInterceptors(InterceptorRegistry registry) {
		// addInterceptor에 겹치는 구문이 들어가 있어도 CORS 오류 발생 (Ex) /houses/**,  /houses/interest/**)
		// 인터셉터를 거칠 때, Delete의 경우 session이 제대로 받아와지지 않아 오류가 난다. (.excludePathPatterns에 등록되어 Interceptor를 거치지 않는 delete REST는 사용가능)
		registry.addInterceptor(loginInterceptor) 		
		.addPathPatterns("/members/**", "/houses/**", "/boards/**")     
		.excludePathPatterns(
				"/members/login",
				"/members/logout",
				"/members/member",
				"/members/checkId",
				"/houses/houseInfo",
				"/houses/location"
				);
		
//		"/boards/delete/**",			// 개발 단계 sts - vsCode 에서 delete시 CORS 에러 임시로 막기 위해 excludePathPatterns에 등록
//		"/houses/interestDelete"		// npm run build 후 제거
	}
}
