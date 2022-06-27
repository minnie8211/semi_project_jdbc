package com.management.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.management.controller.ManagementController;
import com.management.controller.ManagementSController;
import com.management.dao.ManagementDAO;
import com.management.menu.ManagementMenu;
import com.management.vo.ManagementMemberVO;
import com.management.vo.ManagementSportVO;

public class ManagementView {
	
	private Scanner sc = new Scanner(System.in);
	private ManagementMenu menu = new ManagementMenu();
	private ManagementController mc = new ManagementController();
	private ManagementSController msc = new ManagementSController();
	
	public void show() {
		// 회원 등록 및 관리, 조회 요청에 맞추어 적절한 메서드를 호출
		while(true) {
			System.out.print(menu.getMain());
			System.out.print(">>> ");
			String input = sc.nextLine();
			
			switch(input) {
				case "1":
					this.registerMenu();	break;
				case "2":
					this.searchMenu();		break;
				case "3":	
					this.modifyMenu();		break;
				case "4":
					this.removeMenu();		break;
				case "5":
					this.sportMenu();		break;
				case "6":
					System.out.println("프로그램을 종료합니다.");
					System.exit(0);
				default:
					System.out.println("잘못된 메뉴 번호입니다. 다시 입력하세요.");
			
			}
		}
	}
	
	
	public void registerMenu() {
		ManagementMemberVO data = new ManagementMemberVO();
		
		System.out.print("회원명 : ");
	    data.setMembername(sc.nextLine());
	    
	    System.out.print("성별(남/여) : ");
	    data.setGender(sc.nextLine());
	    
	    System.out.print("나이 : ");
	    data.setAge(sc.nextLine());
	    
	    System.out.print("핸드폰번호 : ");
	    data.setPhonenumber(sc.nextLine());
	    
	    System.out.print("운동 종목: ");
	    data.setSport(sc.nextLine());
	    
	    boolean result = mc.register(data);
	    boolean sport = mc.checkSport(data.getSport());
	    
	    if(result) {
	    	System.out.println(data.getMembername() + "님 회원 등록이 완료되었습니다.");
	    } else if(!sport) {
	    	System.out.println("등록되지 않은 운동 종목입니다. 운동 종목을 등록해주세요.");
	    } else {
	    	System.out.println("회원 가입에 실패하였습니다.(이름 중복)");
	    }
	}
	
	public void searchMenu() {
		while(true) {
			System.out.print(menu.getAfterSearch());
			System.out.print(">>> ");
			String input = sc.nextLine();
			
			switch(input) {
				case "1":
					System.out.print("회원명 : ");
					String name = sc.nextLine();
					
					ManagementMemberVO gym_member = mc.searchName(name);
					
					if(gym_member != null) {
						System.out.print(gym_member.getMemberid() + "\t");
						System.out.print(gym_member.getMembername() + "\t");
						System.out.print(gym_member.getGender() + "\t");
						System.out.print(gym_member.getAge() + "\t");
						System.out.print(gym_member.getPhonenumber() + "\t");
						System.out.print(gym_member.getSport() + "\t");
						
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
						java.util.Date createDate = new java.util.Date(gym_member.getCreatedate().getTime());
						String sDate = dateFormat.format(createDate);
						System.out.println(sDate);
					} else {
						System.out.println("등록된 회원이 아닙니다.");
					}
					
					break;
				case "2":
					System.out.print("운동 종목 : ");
					String sport = sc.nextLine();
					
					List<ManagementMemberVO> gym_member_list = mc.searchSport(sport);
					
					if(gym_member_list != null) {
						for(int i = 0; i < gym_member_list.size(); i++) {
							
							System.out.print(gym_member_list.get(i).getMemberid() + "\t");
							System.out.print(gym_member_list.get(i).getMembername() + "\t");
							System.out.print(gym_member_list.get(i).getGender() + "\t");
							System.out.print(gym_member_list.get(i).getAge() + "\t");
							System.out.print(gym_member_list.get(i).getPhonenumber() + "\t");
							System.out.print(gym_member_list.get(i).getSport() + "\t");
							
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
							java.util.Date createDate = new java.util.Date(gym_member_list.get(i).getCreatedate().getTime());
							String sDate = dateFormat.format(createDate);
							System.out.println(sDate);
						}
					} else {
						System.out.println("해당 종목에 등록한 회원이 없거나, 운동 종목이 잘못 입력 되었습니다.");
					}
					
					break;
				case "3":
					return;
				default:
					System.out.println("잘못된 메뉴 번호입니다. 다시 입력하세요.");
			
			}
		}
	}
	
	public void modifyMenu() {
		System.out.print("회원명 : ");
		String name = sc.nextLine();
		
		ManagementMemberVO gym_member = mc.searchName(name);
		
		if(gym_member != null) {
			System.out.println("아무것도 입력하지 않으면 이전 값을 유지합니다.");
			System.out.print("변경할 성별(남/여) : ");
			String input = sc.nextLine();
			input = input.isEmpty() ? gym_member.getGender() : input;
			gym_member.setGender(input);
			
			System.out.print("변경할 나이 : ");
			input = sc.nextLine();
			input = input.isEmpty() ? Integer.toString(gym_member.getAge()) : input;
			gym_member.setAge(input);
			
			System.out.print("변경할 전화번호 : ");
			input = sc.nextLine();
			input = input.isEmpty() ? gym_member.getPhonenumber() : input;
			gym_member.setPhonenumber(input);
			
			System.out.print("변경할 운동 종목 : ");
			input = sc.nextLine();
			input = input.isEmpty() ? gym_member.getSport() : input;
			gym_member.setSport(input);
		} else {
			System.out.println("등록된 회원이 아닙니다.");
		}
		
		
		if(mc.modify(gym_member)) {
			System.out.println(name + "님 회원 정보 수정이 완료되었습니다.");
		} else {
			System.out.println("회원 정보 수정에 실패했습니다.");
		}
		
		
	}
	
	public void removeMenu() {
		System.out.print("회원명 : ");
		String name = sc.nextLine();
		
		ManagementMemberVO gym_member = mc.searchName(name);
		
		if(mc.remove(gym_member, name)) {
			System.out.println(name + "님 회원 정보가 삭제되었습니다.");
			return;
		} else {
			System.out.println("회원 정보 삭제에 실패했습니다.");
		}
	}
	
	public void sportMenu() {
		while(true) {
			System.out.print(menu.getAfterSport());
			System.out.print(">>> ");
			String input = sc.nextLine();
			
			ManagementSportVO data = new ManagementSportVO();
			
			switch(input) {
				case "1":
					System.out.print("운동 종목 : ");
				    data.setSport(sc.nextLine());
				    
				    System.out.print("운동 횟수 : ");
				    data.setHmtimes(sc.nextLine());
				    
				    System.out.print("등록 가격 : ");
				    data.setPrice(sc.nextLine());

				    boolean result = msc.register(data);
				    
				    if(result) {
				    	System.out.println("운동 종목 등록이 완료되었습니다.");
				    } else {
				    	System.out.println("운동 종목 등록에 실패하였습니다.(종목 중복)");
				    }
				    
				    break;
				case "2":
					System.out.print("운동종목 : ");
					String sport = sc.nextLine();
					
					ManagementSportVO sport_type = msc.searchSport(sport);
					
					if(sport_type != null) {
						System.out.print(sport_type.getSportno() + "\t");
						System.out.print(sport_type.getSport() + "\t");
						System.out.print(sport_type.getHmtimes() + "\t");
						System.out.println(sport_type.getPrice());
					} else {
						System.out.println("등록되지 않은 종목입니다.");
					}
					
					break;
				case "3":
					System.out.print("운동종목 : ");
					sport = sc.nextLine();
					
					sport_type = msc.searchSport(sport);
					
					if(sport_type != null) {
						System.out.println("아무것도 입력하지 않으면 이전 값을 유지합니다.");
						
						System.out.print("변경할 운동 횟수 : ");
						input = sc.nextLine();
						input = input.isEmpty() ? sport_type.getHmtimes() : input;
						sport_type.setHmtimes(input);
						
						System.out.print("변경할 가격 : ");
						input = sc.nextLine();
						input = input.isEmpty() ? Integer.toString(sport_type.getPrice()) : input;
						sport_type.setPrice(input);
					} else {
						System.out.println("운동 종목이 잘못 입력 되었습니다.");
					}
					
					
					if(msc.modify(sport_type)) {
						System.out.println("운동 종목(" +sport + ") 정보 수정이 완료되었습니다.");
					} else {
						System.out.println("운동 종목 정보 수정에 실패했습니다.");
					}

					break;
				case "4":
					return;
				default:
					System.out.println("잘못된 메뉴 번호입니다. 다시 입력하세요.");
			}
		}
	}
	
}
