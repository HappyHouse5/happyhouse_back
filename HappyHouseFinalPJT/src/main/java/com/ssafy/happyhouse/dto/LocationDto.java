package com.ssafy.happyhouse.dto;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

@Component
@Alias("Location")
public class LocationDto {
	String aptName;
	int code;
	String lat;
	String lng;
	
	public String getAptName() {
		return aptName;
	}
	public void setAptName(String aptName) {
		this.aptName = aptName;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	
	public LocationDto(String aptName, int code, String lat, String lng) {
		super();
		this.aptName = aptName;
		this.code = code;
		this.lat = lat;
		this.lng = lng;
	}
	
	public LocationDto() {}
}
