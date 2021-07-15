package com.ssafy.happyhouse.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.HouseDao;
import com.ssafy.happyhouse.dto.DongDto;
import com.ssafy.happyhouse.dto.HouseDealDto;
import com.ssafy.happyhouse.dto.LocationDto;
import com.ssafy.happyhouse.dto.MemberDto;

@Service
public class HouseServiceImpl implements HouseService{
	
	@Autowired
	private HouseDao houseDao;
	
	// Singleton Design : Spring support singleton default
//	private HouseServiceImpl() {}
	
//	private static HouseServiceImpl houseService;
//	
//	public static HouseServiceImpl getInstance() {
//		if(houseService == null) {
//			houseService = new HouseServiceImpl();
//		}
//		return houseService;
//	}

	@Override
	public List<HouseDealDto> boardList(int limit, int offset, Map<String, Object> mapOption) {
		Map<String, Object> map = new HashMap<>();
		map.put("minSize", (int) mapOption.get("minSize"));
		map.put("maxSize", (int) mapOption.get("maxSize"));
		map.put("minAmount", (int) mapOption.get("minAmount"));
		map.put("maxAmount", (int) mapOption.get("maxAmount"));
		map.put("ordering", (String) mapOption.get("ordering"));
		map.put("orderBy", (String) mapOption.get("orderBy"));
		
		map.put("limit", (int) limit);   map.put("offset", (int) offset);  
		List<HouseDealDto> houseDealDto = houseDao.boardList(map);
		
		return houseDealDto;
	}

	@Override
	public int boardListTotalCnt(Map<String, Object> map) {
		
		return houseDao.boardListTotalCnt(map);
	}

	public Map<String, Object> searchType(String searchId, String searchType){	// DB에 맞게 데이터 변환
		Map<String, Object> map = new HashMap<>();
		String searchColumn = null;
		
		switch(searchType){
		case "dong":
			map.put("searchWord", searchId);
			searchColumn = "dong.dong_name";
			map.put("searchColumn", searchColumn);
			break;
		case "apt":
			map.put("searchWord", searchId);
			searchColumn = "apart.name";
			map.put("searchColumn", searchColumn);
			break;
		}
		
		return map;
	}
	
	@Override
	public List<HouseDealDto> boardListSearchWord(String searchId, String searchType, int offset, int limit, Map<String, Object> mapOption) {
		
		Map<String, Object> map = searchType(searchId, searchType);
		map.put("minSize", (int) mapOption.get("minSize"));
		map.put("maxSize", (int) mapOption.get("maxSize"));
		map.put("minAmount", (int) mapOption.get("minAmount"));
		map.put("maxAmount", (int) mapOption.get("maxAmount"));
		map.put("ordering", (String) mapOption.get("ordering"));
		map.put("orderBy", (String) mapOption.get("orderBy"));
		
		map.put("limit", (int) limit);   map.put("offset", (int) offset);  
		
		List<HouseDealDto> ret = houseDao.boardListSearchWord(map);
		System.out.println("검색 개수 : " + ret.size());
		return ret;
	}

	@Override
	public int boardListSearchWordTotalCnt(String searchWord, String searhType, Map<String, Object> mapOption) {
		Map<String, Object> map = searchType(searchWord, searhType);
		map.put("minSize", (int) mapOption.get("minSize"));
		map.put("maxSize", (int) mapOption.get("maxSize"));
		map.put("minAmount", (int) mapOption.get("minAmount"));
		map.put("maxAmount", (int) mapOption.get("maxAmount"));
		
		return houseDao.boardListSearchWordTotalCnt(map);
	}

	@Override
	public LocationDto getLocation(String aptName, int code) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("aptName", aptName);
		map.put("code", code);
		return houseDao.getLocation(map);
	}

	@Override
	public List<DongDto> getInterests(int keyId) {
		
		return houseDao.getInterests(keyId);
	}
	
	@Override
	public List<HouseDealDto> getInterestList(int keyId, int offset, int limit, int dongCode) {
		Map<String, Integer> map = new HashMap<>();
		map.put("keyId", keyId);
		map.put("offset", offset);
		map.put("limit", limit);
		map.put("dongCode", dongCode);
		
		return houseDao.getInterestList(map);
	}
	
	
	
	@Override
	public int getInterestCount(int dongCode) {
		

		return houseDao.getInterestCount(dongCode);
	}

	@Override
	public int interestRegister(Map<String, Integer> code, MemberDto member) {
		Map<String, Object> map = new HashMap<>();
		map.put("guCode", code.get("guCode"));
		map.put("dongCode", code.get("dongCode"));
		map.put("memberCode", member.getKeyId());
		
		return houseDao.interestRegister(map);
	}
	
	@Override
	public int deleteInterest(int keyId, List<Integer> dongCode) {
		Map<String, Object> map = new HashMap<>();
		map.put("keyId", keyId);
		
		// 동 코드를 받아 한번에 여러개 지우기
		for(int i=0; i<dongCode.size(); i++) {
			map.put("dongCode", dongCode.get(i));	// 리스트로 받은 동 코드 map에 담기
			houseDao.deleteInterest(map);			// DB에서 삭제
			map.remove("dongCode");					// map에서 삭제
		}
		
		return dongCode.size();
	}
	
	@Override
	public List<DongDto> dongSelect(int guCode) {
		
		return houseDao.dongSelect(guCode);
	}
	
}
