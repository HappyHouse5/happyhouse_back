package com.ssafy.happyhouse.dto;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

@Component
@Alias("HouseDeal")
public class HouseDealDto {
	int no;
	String dong;				// 법정 동
	String aptName;				// 아파트 명
	String code;				// 지역코드
	int dealAmount;				// 거래금액
	int rentMoney;				// 전월세 금액
	int buildYear;				// 거래 년 월 일
	int dealYear;
	int dealMonth;
	int dealDay;
	double area;				// 전용 면적
	int floor;
	String jibun;				// 지번 43242-54
	int type;					// 1: 매매 2: 
	
	
	
	public int getNo() {
		return no;
	}



	public void setNo(int no) {
		this.no = no;
	}



	public String getDong() {
		return dong;
	}



	public void setDong(String dong) {
		this.dong = dong;
	}



	public String getAptName() {
		return aptName;
	}



	public void setAptName(String aptName) {
		this.aptName = aptName;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public int getDealAmount() {
		return dealAmount;
	}



	public void setDealAmount(int dealAmount) {
		this.dealAmount = dealAmount;
	}



	public int getRentMoney() {
		return rentMoney;
	}



	public void setRentMoney(int rentMoney) {
		this.rentMoney = rentMoney;
	}



	public int getBuildYear() {
		return buildYear;
	}



	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}



	public int getDealYear() {
		return dealYear;
	}



	public void setDealYear(int dealYear) {
		this.dealYear = dealYear;
	}



	public int getDealMonth() {
		return dealMonth;
	}



	public void setDealMonth(int dealMonth) {
		this.dealMonth = dealMonth;
	}



	public int getDealDay() {
		return dealDay;
	}



	public void setDealDay(int dealDay) {
		this.dealDay = dealDay;
	}



	public double getArea() {
		return area;
	}



	public void setArea(double area) {
		this.area = area;
	}



	public int getFloor() {
		return floor;
	}



	public void setFloor(int floor) {
		this.floor = floor;
	}



	public String getJibun() {
		return jibun;
	}



	public void setJibun(String jibun) {
		this.jibun = jibun;
	}



	public int getType() {
		return type;
	}



	public void setType(int type) {
		this.type = type;
	}

	

	public HouseDealDto(String dong, String aptName, String code, int dealAmount, int buildYear,
			int dealYear, int dealMonth, int dealDay, double area, int floor, String jibun, int type) {
		super();
		this.dong = dong;
		this.aptName = aptName;
		this.code = code;
		this.dealAmount = dealAmount;
		this.buildYear = buildYear;
		this.dealYear = dealYear;
		this.dealMonth = dealMonth;
		this.dealDay = dealDay;
		this.area = area;
		this.floor = floor;
		this.jibun = jibun;
		this.type = type;
	}

	public HouseDealDto(int no, String dong, String aptName, String code, int dealAmount, int rentMoney, int buildYear,
			int dealYear, int dealMonth, int dealDay, double area, int floor, String jibun, int type) {
		super();
		this.no = no;
		this.dong = dong;
		this.aptName = aptName;
		this.code = code;
		this.dealAmount = dealAmount;
		this.rentMoney = rentMoney;
		this.buildYear = buildYear;
		this.dealYear = dealYear;
		this.dealMonth = dealMonth;
		this.dealDay = dealDay;
		this.area = area;
		this.floor = floor;
		this.jibun = jibun;
		this.type = type;
	}

	public HouseDealDto() {}
	
}
