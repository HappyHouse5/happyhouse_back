package com.ssafy.happyhouse.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.DongDto;
import com.ssafy.happyhouse.dto.HouseDealDto;
import com.ssafy.happyhouse.dto.LocationDto;
import com.ssafy.happyhouse.dto.MemberDto;
import com.ssafy.happyhouse.service.HouseService;

import io.swagger.annotations.Api;

@RestController
@Api("House Controller API V1")
@RequestMapping(value="/houses")
public class HouseController {
	
	@Autowired
	private HouseService houseService;
	
	@GetMapping(value="/search")
	public List<HouseDealDto> search(String searchWord, String searchType, int offset, int limit, Map<String, Object> map) {
		
		List<HouseDealDto> houseDealDtoList = houseService.boardListSearchWord(searchWord, searchType, offset, limit, map);
		System.out.println("search house Deal Length : " + houseDealDtoList.size());
		
		return houseDealDtoList;
	}
	
//	@GetMapping(value="/boardList")
//	public ResponseEntity<Map<String, Object>> boardList(
//			@RequestParam int limit, 
//			@RequestParam int offset, 
//			@RequestParam String searchWord, 
//			@RequestParam String searchType
//			){
//		
//		List<HouseDealDto> houseDealDtoList = null;
//		int totalCnt = 0;
//		
//		if( "".equals(searchWord) ) {
//			houseDealDtoList = houseService.boardList(limit, offset);
//			totalCnt = houseService.boardListTotalCnt();
//		}else {
//			System.out.println("search boardList");
//			houseDealDtoList = search(searchWord, searchType, offset, limit);
//			totalCnt = houseService.boardListSearchWordTotalCnt(searchWord, searchType);
//		}
//		
//		System.out.println(houseDealDtoList.size());
//		System.out.println(totalCnt);
//		
//		Map<String, Object> map = new HashMap<>();
//		map.put("list", houseDealDtoList);
//		map.put("count", totalCnt);
//		
//		System.out.println(houseDealDtoList.size());
//		if(houseDealDtoList != null) {			
//			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
//		}
//		else {
//			return new ResponseEntity(HttpStatus.NOT_FOUND);
//		}
//	}
//	
//	@GetMapping(value="/boardListTotalCnt")
//	public ResponseEntity<Integer> boardListTotalCnt(
//			@RequestParam String searchWord, 
//			@RequestParam String searchType
//			){
//		
//		System.out.println("boardListToTalCnt");
//		int totalCnt = 0;
//		if( "".equals(searchWord) ) {
//			totalCnt = houseService.boardListTotalCnt();
//		}else {
//			totalCnt = houseService.boardListSearchWordTotalCnt(searchWord, searchType);
//		}
//		
//		System.out.println(totalCnt);
//		if(totalCnt != -1) {			
//			return new ResponseEntity<Integer>(totalCnt, HttpStatus.OK);
//		}
//		else {
//			return new ResponseEntity(HttpStatus.NOT_FOUND);
//		}
//	}
	
	@GetMapping(value="/dongSelect")
	public ResponseEntity<List<DongDto>> dongSelect(
			@RequestParam int guCode
			){
		
		List<DongDto> dongList = houseService.dongSelect(guCode);

		
		System.out.println(dongList.size());
		
		if(dongList != null) {			
			return new ResponseEntity<List<DongDto>>(dongList, HttpStatus.OK);
		}
		else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/location")
	public ResponseEntity<LocationDto> location(
			@RequestParam String aptName, 
			@RequestParam int code
			){
		
		LocationDto locDto = houseService.getLocation(aptName, code);
		
		locDto.setAptName(aptName);
		if(locDto != null) {			
			return new ResponseEntity<LocationDto>(locDto, HttpStatus.OK);
		}
		else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	// 관심지역 등록 : 구, 동 = (구 코드, 동 코드)
	@PostMapping(value="/interest")
	public ResponseEntity<Integer> interestRegister(
			@RequestBody Map<String, Integer> code,
			HttpSession session){
		
		MemberDto member = (MemberDto) session.getAttribute("member");
		
		int ret = houseService.interestRegister(code, member);
		
		if(ret == 1) {			
			return new ResponseEntity<Integer>(ret, HttpStatus.OK);
		}
		else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
//		return new ResponseEntity(HttpStatus.OK);
	}
	
	// 사용자의 관심 지역 리스트 가져오기
	@GetMapping(value="/interests")
	public ResponseEntity<List<DongDto>> getInterest(
			HttpSession session
			){
		
		MemberDto member = (MemberDto) session.getAttribute("member");
		
		List<DongDto> dongList = houseService.getInterests(member.getKeyId());
		
		return new ResponseEntity<List<DongDto>>(dongList, HttpStatus.OK);
	}
	
	// 관심지역 매물 조회 : 안쓰임
//	@GetMapping(value="/interest")
//	public ResponseEntity<List<HouseDealDto>> getInterest(
//			@RequestParam int offset,
//			@RequestParam int limit,
//			@RequestParam int dongCode,
//			HttpSession session
//			){
//		
//		MemberDto member = (MemberDto) session.getAttribute("member");
//		
//		List<HouseDealDto> houseDealDtoList = houseService.getInterestList(member.getKeyId(), offset, limit, dongCode);
//				
//		return new ResponseEntity<List<HouseDealDto>>(houseDealDtoList, HttpStatus.OK);
//	}
	
	// 관심 지역 매물 개수 (페이징에 사용)
	@GetMapping(value="/interestCount")
	public ResponseEntity<Integer> getInterestCount(
			@RequestParam int dongCode){
		
		int totalCnt = houseService.getInterestCount(dongCode);
				
		return new ResponseEntity<Integer>(totalCnt, HttpStatus.OK);
	}
	
	// 관심지역 삭제
	@PostMapping(value="/interestDelete")
//	@Transactional
	public ResponseEntity<Integer> getInterest(
			@RequestBody List<Map<String, Integer>> code,
			HttpSession session
			){
		List<Integer> dongCode = new ArrayList<>();
		for(Map<String, Integer> x : code) {
			dongCode.add(x.get("dongCode"));
		}
		System.out.println(dongCode);
		
		MemberDto member = (MemberDto) session.getAttribute("member");
		
		int ret = houseService.deleteInterest(member.getKeyId(), dongCode);
				
		if(ret >= 1) {			
			return new ResponseEntity<Integer>(ret, HttpStatus.OK);
		}
		else {
			System.out.println(ret + "개의 row가 삭제되었습니다. (관심지역 삭제)");
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	//----------------------------------------------------
	// Axios로 요청 받아서 house 검색 정보 돌려주기
	@GetMapping(value="/houseInfo")
	public ResponseEntity<Map<String, Object>> houseInfoSearch(
			@RequestParam String searchType,
			@RequestParam String searchWord,
			@RequestParam int offset,
			@RequestParam int limit,
			@RequestParam int minSize,
			@RequestParam int maxSize,
			@RequestParam int minAmount,
			@RequestParam int maxAmount,
			@RequestParam String ordering,
			@RequestParam String orderBy
			) {
		System.out.println(minSize);
		System.out.println(maxSize);
		List<HouseDealDto> houseDealDtoList = null;
		int totalCnt = 0;
		
		Map<String, Object> map2 = new HashMap<>();
		map2.put("minSize", minSize);
		map2.put("maxSize", maxSize);
		map2.put("minAmount", minAmount);
		map2.put("maxAmount", maxAmount);
		map2.put("ordering", ordering);
		map2.put("orderBy", orderBy);
		System.out.println(minSize + " " + maxSize + " " + minAmount + " " + maxAmount + " " + minSize);
		if( "".equals(searchWord) ) {
			houseDealDtoList = houseService.boardList(limit, offset, map2);
			System.out.println("houseDealDtoList:" + houseDealDtoList.size());
			totalCnt = houseService.boardListTotalCnt(map2);
			System.out.println(totalCnt);
		}else {
			System.out.println("search boardList");
			houseDealDtoList = search(searchWord, searchType, offset, limit, map2);
			totalCnt = houseService.boardListSearchWordTotalCnt(searchWord, searchType, map2);
		}
		
		System.out.println("HoustDealList Size : " + houseDealDtoList.size());
		System.out.println("total Count : " + totalCnt);
		
		Map<String, Object> map = new HashMap<>();
		map.put("list", houseDealDtoList);
		map.put("count", totalCnt);
		
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	

	
	
	
	
	
	
	
	
//	// 검색어 가지고 houseInfo로 넘어가기
//	@PostMapping(value="/houseInfo")
//	public ModelAndView houseInfoSearch(@RequestParam Map<String, String> map) {
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("searchData", map);
//		mv.setViewName("/jsp/houseInfo");
//		System.out.println(map.get("searchType"));
//		System.out.println(map.get("searchWord"));
//		return mv;
//	}
	
}
