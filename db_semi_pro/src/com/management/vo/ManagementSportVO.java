package com.management.vo;

// 운동종목 정보를 담아 두기 위한 객체

public class ManagementSportVO {
	
	private int sportno;	// 운동 종목 구분번호(자동생성)
	private String sport;	// 운동 종목
	private String hmtimes;	// 운동 횟수
	private int price;		// 등록 가격
	
	public int getSportno() {
		return sportno;
	}
	
	public void setSportno(int sportno) {
		this.sportno = sportno;
	}
	
	public String getSport() {
		return sport;
	}
	
	public void setSport(String sport) {
		this.sport = sport;
	}
	
	public String getHmtimes() {
		return hmtimes;
	}
	
	public void setHmtimes(String hmtimes) {
		this.hmtimes = hmtimes;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public void setPrice(String price) {
		this.price = Integer.parseInt(price);
	}

}
