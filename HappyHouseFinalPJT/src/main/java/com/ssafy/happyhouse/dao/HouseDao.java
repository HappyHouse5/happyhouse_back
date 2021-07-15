package com.ssafy.happyhouse.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.DongDto;
import com.ssafy.happyhouse.dto.HouseDealDto;
import com.ssafy.happyhouse.dto.LocationDto;

@Mapper
public interface HouseDao {
	public List<HouseDealDto> boardList(Map<String, Object> map);
	public int boardListTotalCnt(Map<String, Object> map);

	public List<HouseDealDto> boardListSearchWord(Map<String, Object> map);

	public int boardListSearchWordTotalCnt(Map<String, Object> map);

	public LocationDto getLocation(Map<String, Object> map);
	
	public List<DongDto> getInterests(int keyId);				// 관심 지역 구, 동 정보 가져오기

	public List<HouseDealDto> getInterestList(Map<String, Integer> map);
	
	public int getInterestCount(int dongCode);

	public int interestRegister(Map<String, Object> map);
	
	public int deleteInterest(Map<String, Object> map);
	
	public List<DongDto> dongSelect(int guCode);
	
	
}
