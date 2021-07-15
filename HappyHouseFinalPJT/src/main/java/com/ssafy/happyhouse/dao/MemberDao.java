package com.ssafy.happyhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.MemberDto;

@Mapper
public interface MemberDao {
	public int register(MemberDto member);
	
	public int setMemberInfo(MemberDto member) throws Exception;

	public MemberDto login(MemberDto member) throws Exception;

	public int deleteMember(MemberDto member) throws Exception;

	public List<MemberDto> idEmailCheck(MemberDto member) throws Exception;
	
	public int checkId(String userId) throws Exception;
	
}
