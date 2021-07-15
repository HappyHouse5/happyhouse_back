//package com.ssafy.happyhouse.controller;

// 메인 페이지, 단순 페이지 이동 시 Controller사용
//@CrossOrigin(
//		origins = "http://localhost:8080", // allowCredentials = "true" 일 경우, origins="*" 는 X
//		allowCredentials = "true", 
//		allowedHeaders = "*", 
//		methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
//)
//@Controller
//public class MainController {
//	
//	@GetMapping(value= "/")
//	public String index() {
//		System.out.println("Main page");
//		return "/";
//	}
//}
	
//	@GetMapping(value="/registerPage")
//	public String registerPage() {
//		System.out.println("Go to registerPage");
//		return "/jsp/registerMember";
//	}
//	
//	@GetMapping(value="/memberInfo")
//	public String memberInfo() throws Exception{
//		System.out.println("Go to memberInfoPage");
//		return "/jsp/memberInfo";
//	}

	
//	@GetMapping(value="/findPw")
//	public String findPw() {
//		return "/jsp/findPw";
//	}
//	
//	@GetMapping(value="/interest")
//	public String interestPage() {
//		return "/jsp/interest";
//	}
//	
//	@GetMapping(value="/logout")
//	public String logout(HttpSession session) {
//		session.invalidate();
//		
//		return "/index";
//	}
	
//	@GetMapping(value="/error")
//	public String handleError() {
//		return "error";
//	}
//}
