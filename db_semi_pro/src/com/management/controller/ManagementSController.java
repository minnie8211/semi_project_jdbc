package com.management.controller;


import com.management.dao.ManagementSDAO;
import com.management.vo.ManagementSportVO;

public class ManagementSController {
	
	private ManagementSDAO sdao = new ManagementSDAO();
	
	public boolean register(ManagementSportVO data) {
		ManagementSportVO sport_type = sdao.get(data.getSport());
		if(sport_type == null) {
			boolean result = sdao.add(data);
			if(result) {
				return true;
			}
		}
		return false;
	}
	
	public ManagementSportVO searchSport(String sport) {
		ManagementSportVO sport_type = sdao.get(sport);
		
		if(sport_type != null) {
			return sport_type;
		}
		return null;
	}
	
	public boolean modify(ManagementSportVO data) {
		return sdao.modify(data);
	}
	
}
