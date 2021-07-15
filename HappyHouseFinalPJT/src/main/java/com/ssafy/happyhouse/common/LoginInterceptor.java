package com.ssafy.happyhouse.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ssafy.happyhouse.dto.MemberDto;

//인터셉터는 컨트롤러로 가기 전에 수행된다.  (WAS가 실행 된 후 동작)  , 필터는 WAS가 실행되기 전 URL 조건에 따라 필터링
//인터셉터 등록과 사용을 위해서는 등록을 위한 Configuration파일과 인터셉터를 구현한 HandlerInterceptor를 구현한 객체가 있어야 한다.
//https://hhseong.tistory.com/177
//preHandle : request 요청 직후 동작
//postHandle : response 직전 동작
//AsyncHandlerInterceptor : 비동기 핸들러, HandlerInterceptor : 동기 핸들러 , Adaptor는 Deprecated
//Annotation 빼먹지 않기!!

// 프론트 build 후 페이지 새로고침 오류 -> 404 에러 페이지(adviceController)로 넘기기 -> responseEntity<>에 에러 정보 담아 front로 넘기기 -> vue에서 에러 처리 로직 구현하여 새로고침 만들기

@Component
public class LoginInterceptor implements HandlerInterceptor{
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("요청 URL ==>> " + request.getRequestURI());
        HttpSession session = request.getSession();
        MemberDto member = (MemberDto) session.getAttribute("member");
        System.out.println(member);
        if( member == null ) {
        	
			Gson gson = new Gson();

			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("result", "login");
			
			String jsonStr = gson.toJson(jsonObject);
			response.getWriter().write(jsonStr);
			
        	return false;
        }

        return true;
    }
}
