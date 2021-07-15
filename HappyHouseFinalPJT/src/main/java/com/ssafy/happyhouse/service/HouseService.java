package com.ssafy.happyhouse.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.dto.DongDto;
import com.ssafy.happyhouse.dto.HouseDealDto;
import com.ssafy.happyhouse.dto.LocationDto;
import com.ssafy.happyhouse.dto.MemberDto;

public interface HouseService{
	public List<HouseDealDto> boardList(int limit, int offset, Map<String, Object> map);
	public int boardListTotalCnt(Map<String, Object> map);

	public List<HouseDealDto> boardListSearchWord(String searchId, String searchType, int offset, int limit, Map<String, Object> map);
	public int boardListSearchWordTotalCnt(String searchWord, String searhType, Map<String, Object> map);

	public LocationDto getLocation(String aptName, int code);

	public List<HouseDealDto> getInterestList(int keyId, int offset, int limit, int dongCode);
	
	public List<DongDto> getInterests(int keyId);
	
	public int getInterestCount(int dongCode);
	
	public int interestRegister(Map<String, Integer> code, MemberDto member);
	
	public int deleteInterest(int keyId, List<Integer> dong);
	
	public List<DongDto> dongSelect(int guCode);
	
	
}
