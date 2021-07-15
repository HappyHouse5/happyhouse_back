package com.ssafy.happyhouse.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.happyhouse.dto.MemberDto;
import com.ssafy.happyhouse.dto.ProfileDto;

public interface ProfileService {
	public int insert(MemberDto member, MultipartHttpServletRequest request) throws IOException, ServletException;

	public int profileUpdate(int key_id, MultipartHttpServletRequest request) throws IOException, ServletException;

	public void getDeleteProfile(int keyId, HttpSession request);
	public int deleteProfile(int keyId);
	
	public String getProfileImage(int member_code);
	public ProfileDto getProfile(int code);
}
