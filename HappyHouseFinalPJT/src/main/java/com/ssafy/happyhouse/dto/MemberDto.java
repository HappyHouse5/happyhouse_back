package com.ssafy.happyhouse.dto;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

@Component
@Alias("Member")
public class MemberDto {
	private int keyId;				// 멤버 고유 부여 ID
	private String userId;			// 유저 ID
	private String name;			// 이름
	private String pw;				// 유저 pw
	private String phone;			// 전화번호
	private String email;			// Email
	private Date regDate;			// 가입 일시
	private int locationCode;
	private int isAdmin;			// 관리자 여부
	private String fileURL;			// 프로필 파일 url
	private String prefer;
	
	
	public String getPrefer() {
		return prefer;
	}
	public void setPrefer(String prefer) {
		this.prefer = prefer;
	}
	public int getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getFileURL() {
		return fileURL;
	}
	public void setFileURL(String fileURL) {
		this.fileURL = fileURL;
	}
	public int getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(int locationCode) {
		this.locationCode = locationCode;
	}
	public int getKeyId() {
		return keyId;
	}
	public void setKeyId(int keyId) {
		this.keyId = keyId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	
	public MemberDto(int keyId, String userId, String name, String pw, String phone, String email, Date regDate,
			int locationCode, int isAdmin, String fileURL, String prefer) {
		super();
		this.keyId = keyId;
		this.userId = userId;
		this.name = name;
		this.pw = pw;
		this.phone = phone;
		this.email = email;
		this.regDate = regDate;
		this.locationCode = locationCode;
		this.isAdmin = isAdmin;
		this.fileURL = fileURL;
		this.prefer = prefer;
	}
	public MemberDto(int keyId, String userId, String name, String pw, String phone, String email, Date regDate) {
		super();
		this.keyId = keyId;
		this.userId = userId;
		this.name = name;
		this.pw = pw;
		this.phone = phone;
		this.email = email;
		this.regDate = regDate;
	}
	
	public MemberDto(String userId, String name, String pw, String phone, String email, int locationCode) {
		super();
		this.userId = userId;
		this.name = name;
		this.pw = pw;
		this.phone = phone;
		this.email = email;
		this.locationCode = locationCode;
	}
	
	public MemberDto(String userId, String name, String pw, String phone, String email, int locationCode, String fileURL) {
		super();
		this.userId = userId;
		this.name = name;
		this.pw = pw;
		this.phone = phone;
		this.email = email;
		this.locationCode = locationCode;
		this.fileURL = fileURL;
	}
	
	public MemberDto(int keyId, String userId, String name, String pw, String phone, String email, int locationCode, String fileURL) {
		super();
		this.keyId = keyId;
		this.userId = userId;
		this.name = name;
		this.pw = pw;
		this.phone = phone;
		this.email = email;
		this.locationCode = locationCode;
		this.fileURL = fileURL;
	}
	
	
	public MemberDto(int keyId, String userId, String name, String pw, String phone, String email, Date regDate,
			int locationCode, int isAdmin) {
		super();
		this.keyId = keyId;
		this.userId = userId;
		this.name = name;
		this.pw = pw;
		this.phone = phone;
		this.email = email;
		this.regDate = regDate;
		this.locationCode = locationCode;
		this.isAdmin = isAdmin;
	}
	
	public MemberDto(String id, String pw) {
		super();
		this.userId = id;
		this.pw = pw;
	}
	
	
	public MemberDto() {}
}
