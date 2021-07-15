package com.ssafy.happyhouse.service;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.happyhouse.dao.ProfileDao;
import com.ssafy.happyhouse.dto.MemberDto;
import com.ssafy.happyhouse.dto.ProfileDto;

@Service
public class ProfileServiceImpl implements ProfileService{

	@Autowired
	private ProfileDao profileDao;
	
	String uploadFolder = "upload";
	
	@Override
	public int insert(MemberDto member, MultipartHttpServletRequest request) throws IOException, ServletException {
		String uploadPath = request.getSession().getServletContext().getRealPath("/");
		System.out.println(uploadPath);
		
		Collection<Part> parts = request.getParts();
		
		File uploadDir = new File(uploadPath + uploadFolder);
		if(!uploadDir.exists()) uploadDir.mkdir();
		
		System.out.println("part size : " + parts.size());
		System.out.println("uploadPath : " + uploadPath + uploadFolder);
		
		int ret = -1;
		int isExistProfile = 0;
		for(Part part : parts) {
			++isExistProfile;
			String fileName = getFileName(part);
			if(isExistProfile > 20) break;
			
			if("".equals(fileName)) continue;
			
			UUID uuid = UUID.randomUUID();   	// 	중복되지 않는 파일이름 만들기
			
			String extension = FilenameUtils.getExtension(fileName);
			
			String savingFileName = uuid + "." + extension;
			
			part.write(uploadPath + uploadFolder + File.separator + savingFileName);					// 서버로 빌드 시
//			part.write(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);	// 개발 시
			
			ProfileDto profile = new ProfileDto();
			profile.setProfileId(member.getKeyId());		// member의 code primary 키를 그대로 가져와서 프로필 이미지와 연결하기 위해 같은 값을 담는다.
			profile.setFileName(fileName);
			profile.setFileSize(part.getSize());
			profile.setFileContentType(part.getContentType());
			profile.setFileURL(uploadFolder + "/" + savingFileName);
			
			ret = profileDao.insert(profile);
		}
		
		return ret;
	}
	
	private String getFileName(Part part) {
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename"))
	            return content.substring(content.indexOf("=") + 2, content.length() - 1);
	    }
	    return "";
	}

	@Override
	public int profileUpdate(int keyId, MultipartHttpServletRequest request) throws IOException, ServletException {
		String uploadPath = request.getSession().getServletContext().getRealPath("/");
		System.out.println(uploadPath);
		
		Collection<Part> parts = request.getParts();
		
		System.out.println("part size : " + parts.size());
		System.out.println("uploadPath : " + uploadPath + uploadFolder);
		
		File uploadDir = new File(uploadPath + uploadFolder);
		if(!uploadDir.exists()) uploadDir.mkdir();
		
		boolean isFileDeleted = false;
		
		int ret = -1;
		for(Part part : parts) {
			String fileName = getFileName(part);
			
			if("".equals(fileName)) continue;
			
			if(!isFileDeleted) {
				
				// 물리적인 파일 삭제
				String fileURL = profileDao.getDeleteProfile(keyId);			// 기존에 연결되어 있는 프로필 URL 정보 가져오기(물리 파일 삭제를 위해)
				
				File file = new File(uploadPath + File.separator + fileURL);	// 파일 경로 + URL로 파일 객체 생성
				if(file.exists()) {
					file.delete();								// 해당 파일이 존재한다면 삭제하기
				}
				isFileDeleted = true;
			}
			
			UUID uuid = UUID.randomUUID();   									// 	중복되지 않는 파일이름 만들기
			
			String extension = FilenameUtils.getExtension(fileName);
			
			String savingFileName = uuid + "." + extension;
			
			part.write(uploadPath + uploadFolder + File.separator + savingFileName);					// 서버로 빌드 시
//			part.write(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);	// 개발 시
			
			ProfileDto profile = new ProfileDto();
			profile.setFileName(fileName);
			profile.setFileSize(part.getSize());
			profile.setFileContentType(part.getContentType());
			profile.setFileURL(uploadFolder + "/" + savingFileName);
			// key_id :  member의 primary 키와 연결된 profile_image PK 
			
			profile.setProfileId(keyId);
			if(profileDao.isExist(keyId) == 1) {
				ret = profileDao.profileUpdate(profile);
			}
			else {
				ret = profileDao.insert(profile);
			}
			
		}
		return ret;
	}
	
	
	@Override
	public void getDeleteProfile(int keyId, HttpSession session) {
		String uploadPath = session.getServletContext().getRealPath("/");
		System.out.println(uploadPath);
		
		String fileURL = profileDao.getDeleteProfile(keyId);
		 
		File file = new File(uploadPath + File.separator + fileURL);	// 파일 경로 + URL로 파일 객체 생성
		if(file.exists()) {
			file.delete();												// 해당 파일이 존재한다면 삭제하기
		}
	}
	
	@Override
	public int deleteProfile(int keyId) {
		
		
		return profileDao.deleteProfile(keyId);
	}
	
	@Override
	public String getProfileImage(int member_code) {
		
		return profileDao.getProfileImage(member_code);
	}

	@Override
	public ProfileDto getProfile(int code) {
		ProfileDto profile = profileDao.getProfile(code);
		
		System.out.println(code);
		System.out.println(profile);
		
		return profile;
	}
	
}
