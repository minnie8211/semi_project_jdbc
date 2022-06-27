package com.management.controller;

import java.util.ArrayList;
import java.util.List;

import com.management.dao.ManagementDAO;
import com.management.dao.ManagementSDAO;
import com.management.vo.ManagementMemberVO;
import com.management.vo.ManagementSportVO;

public class ManagementController {
	
	private ManagementDAO dao = new ManagementDAO();
	private ManagementSDAO sdao = new ManagementSDAO();
	
	public boolean register(ManagementMemberVO data) {
		ManagementMemberVO gym_member = dao.get(data.getMembername());
		ManagementSportVO sport_type = sdao.get(data.getSport()); 
		
		if(gym_member == null && sport_type != null) {
			boolean result = dao.add(data);
			if(result) {
				return true;
			} 
		}
		return false;
	}
	
	public boolean checkSport(String sport) {
		ManagementSportVO sport_type = sdao.get(sport); 
		
		if(sport_type != null) {
			return true;
		}
		return false;
	}
	
	public ManagementMemberVO searchName(String name) {
		ManagementMemberVO gym_member = dao.get(name);
		
		if(gym_member != null) {
			return gym_member;
		}
		return null;
	}
	
	public List<ManagementMemberVO> searchSport(String sport) {
		List<ManagementMemberVO> gym_member_list = dao.searchSport(sport);
		
		if(gym_member_list != null) {
			return gym_member_list;
		}
		return null;
	}
	
	
	public boolean modify(ManagementMemberVO data) {
		return dao.modify(data);
	}
	
	public boolean remove(ManagementMemberVO data, String name) {
		if(data.getMembername().equals(name)) {
			return dao.remove(data);
		} else {
			return false;
		}
	}

}
