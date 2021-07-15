package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.happyhouse.dto.MemberDto;
import com.ssafy.happyhouse.dto.ProfileDto;
import com.ssafy.happyhouse.service.MemberService;
import com.ssafy.happyhouse.service.ProfileService;

import io.swagger.annotations.Api;

@RestController
@Api("Member Controller API V1")
@RequestMapping("/members")
public class MemberController {
    private static final int SUCCESS = 1;
    private static final int FAIL = -1;
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private ProfileService profileService;
	
	// 회원가입
	@PostMapping(value="/member")
	@Transactional
	public ResponseEntity<Integer> register(
			MemberDto member,
			MultipartHttpServletRequest request) throws Exception{
		
		int ret = memberService.register(member);	// service 호출
		HttpSession session = request.getSession();
		session.invalidate();
		
		System.out.println("register Member" + ret);
		if(ret == SUCCESS) {
			System.out.println("memberId : " + member.getKeyId());
			profileService.insert(member, request);	// 첨부한 프로필 이미지가 있다면 DB, uploads 폴더에 담기
			
			return new ResponseEntity<Integer>(ret, HttpStatus.OK);
		}
		else{				
			return new ResponseEntity<Integer>(FAIL, HttpStatus.OK);
		}
	}
	
	// 로그인
	@PostMapping(value="/login")
	public ResponseEntity<MemberDto> login(@RequestBody Map<String, String> map, HttpSession session) throws Exception{
		
		MemberDto member = new MemberDto(map.get("userId"), map.get("userPassword"));
		MemberDto memberDto = memberService.login(member);
		
		if(memberDto != null) {
			session.setAttribute("member", memberDto);
			System.out.println("로그인 성공!");
			return new ResponseEntity<MemberDto>(memberDto, HttpStatus.OK);
		}
		else {				
			return new ResponseEntity<MemberDto>(memberDto, HttpStatus.OK);
		}
	}
	
	// 로그인 후 사용자 프로필 사진 가져오기
	@GetMapping(value="/getProfileImage/{code}")
	public ResponseEntity<String> getProfileImage(@PathVariable int code){
		System.out.println("getProfileController");
		String fileURL = profileService.getProfileImage(code);
		System.out.println(fileURL);
		if(fileURL != null) {			
			return new ResponseEntity<String>(fileURL, HttpStatus.OK);
		}
		else {
			fileURL = "";
			return new ResponseEntity<String>(fileURL, HttpStatus.OK);
		}
	}
	
	@GetMapping(value="/getProfile")
	public ResponseEntity<Map<String, Object>> getProfile(@RequestParam int code){
		
		Map<String, Object> map = new HashMap<>();
		System.out.println("member's keyId : " + code);
		ProfileDto profile = profileService.getProfile(code);
//		System.out.println("프로필 사진 정보 :" + profile.getFileName() + profile.getFileURL());
		if(profile == null) {
			map.put("isExist", false);
		}
		else {
			map.put("isExist", true);
			map.put("file", profile);
		}
		
//		System.out.println("file URL 정보 : " + ((ProfileDto) map.get("file")).getFileURL());
		System.out.println(map.get("isExist"));
		if((boolean) map.get("isExist")) {
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}else {
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}		 
	}
	
	
	// 로그아웃
	@GetMapping(value="/logout")
	public ResponseEntity<Integer> logout(HttpSession session) throws Exception{
		session.removeAttribute("member");
		session.invalidate();
		System.out.println("logout");
		
		return new ResponseEntity<Integer>(SUCCESS, HttpStatus.OK);
	}
	
	// 회원 탈퇴
	@DeleteMapping(value="/member")
	@Transactional
	public ResponseEntity<Integer> deleteMember(HttpSession session) throws Exception{

		System.out.println("delete Member!");
//		return new ResponseEntity<Integer>(SUCCESS, HttpStatus.OK);						// axios 테스트용
		MemberDto memberSession = (MemberDto) session.getAttribute("member");
		int keyId = memberSession.getKeyId();
		
		int ret = 0;
		profileService.getDeleteProfile(keyId, session);			// 기존에 연결되어 있는 프로필 URL 정보 가져와서 Service에서 물리 파일 삭제
		ret += profileService.deleteProfile(keyId);					// DB에서도 프로필 사진 정보 삭제
		
		ret += memberService.deleteMember(memberSession);		// db에서 member 정보 지우기 -> profile image FK Cascade이므로  db에서 자동 삭제 (deleteProfile 수행하지 않아도 무관함)
		
		session.removeAttribute("member");
		session.invalidate();
		
		if(ret >= 1) return new ResponseEntity<Integer>(SUCCESS, HttpStatus.OK);
		else return new ResponseEntity<Integer>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	// 회원 정보 수정
	@PostMapping(value="/modifyMember")
	@Transactional
	public ResponseEntity<Integer> modifyMember(
			MemberDto member,
			MultipartHttpServletRequest request,
			HttpSession session) throws Exception{
		
		MemberDto memberSession = (MemberDto) session.getAttribute("member");
		int keyId = memberSession.getKeyId();
		System.out.println(member.getName());
		int retMember = memberService.setMemberInfo(member);					// member 정보 변경
		
		System.out.println("keyID : " + keyId);
		
		int ret = profileService.profileUpdate(keyId, request);
		
		System.out.println("member 정보 변경 : " + retMember + "프로필 정보 변경 : " + ret);
		
		if(retMember == 1) {													// 프로필 사진을 변경했을 때
			if(ret == -1) {														// 변경 시 프로필 사진을 넣지 않았을 때
				// 물리적인 파일 삭제
				profileService.getDeleteProfile(keyId, session);			// 기존에 연결되어 있는 프로필 URL 정보 가져오기(물리 파일 삭제를 위해)
				profileService.deleteProfile(keyId);
				
			}
			session.removeAttribute("member");
			session.invalidate();
		}
		
		if(retMember == SUCCESS) {			
			return new ResponseEntity<Integer>(retMember, HttpStatus.OK);
		}
		else {				
			return new ResponseEntity<Integer>(retMember, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	// 세션 체크
	@GetMapping(value="/sessionCheck")
	public ResponseEntity<String> sessionCheck(HttpSession session) throws Exception{
//		MemberDto member = (MemberDto) session.getAttribute("member");
		System.out.println("sessionCheck");
		
		return new ResponseEntity<String>("true", HttpStatus.OK);
	}
	
	@GetMapping(value="/checkId")
	public ResponseEntity<Boolean> checkId(@RequestParam String userId ) throws Exception{
		int ret = memberService.checkId(userId);
		
		if(ret >= 1) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		
	}
}
