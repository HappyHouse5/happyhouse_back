package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.MemberDao;
import com.ssafy.happyhouse.dto.MemberDto;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDao memberDao;
	
	String uploadFolder = "upload";	
	
	@Override
	public int register(MemberDto member) throws Exception {
		System.out.println(member.getName());
		List<MemberDto> duplMember = memberDao.idEmailCheck(member);
		
		for(int i=0; i<duplMember.size(); i++) {
			if(duplMember.get(i).getUserId().equals(member.getUserId())) {
				System.out.println("중복 ID");
				return -1;
			}
			else if(duplMember.get(i).getEmail().equals(member.getEmail())) {
				System.out.println("중복 Email");
				return -2;
			}
		}
		
		int ret = memberDao.register(member);
		System.out.println("keyId : " + member.getKeyId());
		return ret;
	}

	@Override
	public int setMemberInfo(MemberDto member) throws Exception {
		System.out.println(member.getLocationCode());
		return memberDao.setMemberInfo(member);
	}

	@Override
	public MemberDto login(MemberDto member) throws Exception {
		member = memberDao.login(member);
		
		return member;
	}

	@Override
	public int deleteMember(MemberDto member) throws Exception {
		
		return memberDao.deleteMember(member);
	}

	@Override
	public int checkId(String userId) throws Exception {
		
		return memberDao.checkId(userId);
	}




	
}
