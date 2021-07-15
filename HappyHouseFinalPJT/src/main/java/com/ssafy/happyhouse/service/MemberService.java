package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.dto.MemberDto;

public interface MemberService{
	public int register(MemberDto member) throws Exception;
	
	public int setMemberInfo(MemberDto member) throws Exception;

	public MemberDto login(MemberDto member) throws Exception;

	public int deleteMember(MemberDto member) throws Exception;

	public int checkId(String userId) throws Exception;
}
