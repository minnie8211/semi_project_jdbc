package com.management.vo;

import java.sql.Date;

// 회원 정보를 담아 두기 위한 객체

public class ManagementMemberVO {
	
	private int memberid;		// 회원 번호(자동생성 구분번호)
	private String membername;	// 회원 이름
	private String gender;		// 성별('남', '여')
	private int age;			// 나이
	private String phonenumber;	// 핸드폰번호
	private String sport;		// 운동 종목
	private Date createdate;	// 등록일자
	
	public int getMemberid() {
		return memberid;
	}
	
	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}
	
	public String getMembername() {
		return membername;
	}
	
	public void setMembername(String membername) {
		this.membername = membername;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setAge(String age) {
		this.age = Integer.parseInt(age);
	}
	
	public String getPhonenumber() {
		return phonenumber;
	}
	
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	
	public String getSport() {
		return sport;
	}
	
	public void setSport(String sport) {
		this.sport = sport;
	}
	
	public Date getCreatedate() {
		return createdate;
	}
	
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

}
