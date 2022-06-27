package com.management.menu;

public class ManagementMenu {
	
	public String getMain() {
		String s = ""
				+ "<<<<< 회원 관리 프로그램 >>>>\n"
				+ "┌────────────────────────────┐\n"
				+ "│     1. 회원 등록           │\n"
				+ "│     2. 회원 정보 조회      │\n"
				+ "│     3. 회원 정보 수정      │\n"
				+ "│     4. 회원 정보 삭제      │\n"
				+ "│     5. 운동 종목 관리      │\n"
				+ "│     6. 프로그램 종료       │\n"
				+ "└────────────────────────────┘\n";
	    return s;
	}
	
	public String getAfterSearch() {
		String s = ""
				+ "<<<<<<< 회원 정보 조회 >>>>>>\n"
				+ "┌────────────────────────────┐\n"
				+ "│    1. 이름으로 찾기        │\n"
				+ "│    2. 종목으로 찾기        │\n"
				+ "│    3. 메인으로 돌아가기    │\n"
				+ "└────────────────────────────┘\n";
	    return s;
	}
	
	public String getAfterSport() {
		String s = ""
				+ "<<<<<<< 운동 종목 관리 >>>>>>\n"
				+ "┌────────────────────────────┐\n"
				+ "│    1. 종목 등록            │\n"
				+ "│    2. 종목 조회            │\n"
				+ "│    3. 종목 내용 수정       │\n"
				+ "│    4. 메인으로 돌아가기    │\n"
				+ "└────────────────────────────┘\n";
	    return s;
		
	}
	
}