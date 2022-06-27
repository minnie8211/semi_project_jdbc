package com.management.dao;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.conn.db.DBConn;
import com.management.vo.ManagementSportVO;

public class ManagementSDAO {
private DBConn db;
	
	public ManagementSDAO() {
		try {
			db = new DBConn(new File(System.getProperty("user.home") + "/oracle_db.conf"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean add(ManagementSportVO data) {
		String query = "INSERT INTO sport_type VALUES(SEQ_SPORT_TYPE.NEXTVAL, ?, ?, ?)";

		try {
			PreparedStatement pstat = db.getPstat(query);
			pstat.setString(1, data.getSport());
			pstat.setString(2, data.getHmtimes());
			pstat.setInt(3, data.getPrice());
			
			int result = db.sendInsertQuery();
			
			if(result == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}
	
	public boolean modify(ManagementSportVO data) {
		String query = ""
				+ "UPDATE sport_type"
				+ "   SET HMTIMES = ?"
				+ "     , PRICE = ?"
				+ " WHERE SPORT = ?";
		
		try {
			PreparedStatement pstat = db.getPstat(query);
			pstat.setString(1, data.getHmtimes());
			pstat.setInt(2, data.getPrice());
			pstat.setString(3, data.getSport());
			
			int rs = db.sendUpdateQuery();
			
			if(rs == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}	
	
	public ManagementSportVO get(String sport) {
		String query = "SELECT * FROM sport_type WHERE sport = ?";
		
		try {
			PreparedStatement pstat = db.getPstat(query);
			pstat.setString(1, sport);
			
			ResultSet rs = db.sendSelectQuery();
			
			if(rs.next()) {
				ManagementSportVO data = new ManagementSportVO();
				data.setSportno(rs.getInt("SPORTNO"));
				data.setSport(rs.getString("SPORT"));
				data.setHmtimes(rs.getString("HMTIMES"));
				data.setPrice(rs.getInt("PRICE"));
				return data;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
