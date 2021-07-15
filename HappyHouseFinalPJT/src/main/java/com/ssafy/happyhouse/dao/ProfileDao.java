package com.ssafy.happyhouse.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.ProfileDto;

@Mapper
public interface ProfileDao {
	public int insert(ProfileDto profile);

	public int isExist(int keyId);
	public int profileUpdate(ProfileDto profile);

	public String getDeleteProfile(int keyId);

	public int deleteProfile(int keyId);
	
	public String getProfileImage(int member_code);
	public ProfileDto getProfile(int code);
}
