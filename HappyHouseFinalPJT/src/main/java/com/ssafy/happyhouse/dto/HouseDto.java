package com.ssafy.happyhouse.dto;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

@Component
@Alias("House")
public class HouseDto {
	String aptName;				// 아파트 명
	int wholePrice;				// 거래금액
	int monthPrice;				// 전/월세 금액
	int buildDate;				// 건축년도
	double area;				// 전용 면적
	Date dealDate;				// 거래 년/월/일  
	String dong;				// 법정 동
	String jibun;				// 지번 43242-54
	String dealType;			// 매매, 전제
	String homeType;			// apt/house
	String locationCode;		// 지역코드
	public String getAptName() {
		return aptName;
	}
	public void setAptName(String aptName) {
		this.aptName = aptName;
	}
	public int getWholePrice() {
		return wholePrice;
	}
	public void setWholePrice(int wholePrice) {
		this.wholePrice = wholePrice;
	}
	public int getMonthPrice() {
		return monthPrice;
	}
	public void setMonthPrice(int monthPrice) {
		this.monthPrice = monthPrice;
	}
	public int getBuildDate() {
		return buildDate;
	}
	public void setBuildDate(int buildDate) {
		this.buildDate = buildDate;
	}
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}
	public Date getDealDate() {
		return dealDate;
	}
	public void setDealDate(Date dealDate) {
		this.dealDate = dealDate;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	public String getJibun() {
		return jibun;
	}
	public void setJibun(String jibun) {
		this.jibun = jibun;
	}
	public String getDealType() {
		return dealType;
	}
	public void setDealType(String dealType) {
		this.dealType = dealType;
	}
	public String getHomeType() {
		return homeType;
	}
	public void setHomeType(String homeType) {
		this.homeType = homeType;
	}
	public String getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	
	public HouseDto(String aptName, int wholePrice, int monthPrice, int buildDate, double area, Date dealDate,
			String dong, String jibun, String dealType, String homeType, String locationCode) {
		super();
		this.aptName = aptName;
		this.wholePrice = wholePrice;
		this.monthPrice = monthPrice;
		this.buildDate = buildDate;
		this.area = area;
		this.dealDate = dealDate;
		this.dong = dong;
		this.jibun = jibun;
		this.dealType = dealType;
		this.homeType = homeType;
		this.locationCode = locationCode;
	}
	
	public HouseDto() {}
}
