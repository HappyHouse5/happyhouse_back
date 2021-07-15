package com.ssafy.happyhouse.dto;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

@Component
@Alias("Profile")
public class ProfileDto {
	private int profileId;
	private String fileName;
	private double fileSize;
	private String fileURL;
	private String fileContentType;
	
	public int getProfileId() {
		return profileId;
	}
	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public double getFileSize() {
		return fileSize;
	}
	public void setFileSize(double fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileURL() {
		return fileURL;
	}
	public void setFileURL(String fileURL) {
		this.fileURL = fileURL;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	
	public ProfileDto(int profileId, String fileName, double fileSize, String fileURL, String fileContentType) {
		super();
		this.profileId = profileId;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.fileURL = fileURL;
		this.fileContentType = fileContentType;
	}
	
	public ProfileDto() {}
	
}
