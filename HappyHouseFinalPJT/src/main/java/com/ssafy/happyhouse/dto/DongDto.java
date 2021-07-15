package com.ssafy.happyhouse.dto;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

@Component
@Alias("Dong")
public class DongDto {
	private String dongName;
	private int dongCode;
	private String guName;
	
	public String getDongName() {
		return dongName;
	}
	public void setDongName(String dongName) {
		this.dongName = dongName;
	}
	public int getDongCode() {
		return dongCode;
	}
	public void setDongCode(int dongCode) {
		this.dongCode = dongCode;
	}
	
	
	
	public String getGuName() {
		return guName;
	}
	public void setGuName(String guName) {
		this.guName = guName;
	}
	public DongDto(String dongName, int dongCode) {
		super();
		this.dongName = dongName;
		this.dongCode = dongCode;
	}
	
	
	
	public DongDto() {}
}
