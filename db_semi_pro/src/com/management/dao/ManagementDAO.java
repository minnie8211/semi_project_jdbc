package com.management.dao;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.conn.db.DBConn;
import com.management.vo.ManagementMemberVO;

public class ManagementDAO {
	private DBConn db;
	
	public ManagementDAO() {
		try {
			db = new DBConn(new File(System.getProperty("user.home") + "/oracle_db.conf"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean add(ManagementMemberVO data) {
		String query = "INSERT INTO gym_member VALUES(SEQ_GYM_MEMBER.NEXTVAL, ?, ?, ?, ?, ?, SYSDATE)";

		try {
			PreparedStatement pstat = db.getPstat(query);
			pstat.setString(1, data.getMembername());
			pstat.setString(2, data.getGender());
			pstat.setInt(3, data.getAge());
			pstat.setString(4, data.getPhonenumber());
			pstat.setString(5, data.getSport());
			
			int result = db.sendInsertQuery();
			
			if(result == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}
	
	public boolean modify(ManagementMemberVO data) {
		String query = ""
				+ "UPDATE gym_member"
				+ "   SET GENDER = ?"
				+ "     , AGE = ?"
				+ "     , PHONENUMBER = ?"
				+ "     , SPORT = ?"
				+ " WHERE MEMBERNAME = ?";
		
		try {
			PreparedStatement pstat = db.getPstat(query);
			pstat.setString(1, data.getGender());
			pstat.setInt(2, data.getAge());
			pstat.setString(3, data.getPhonenumber());
			pstat.setString(4, data.getSport());
			pstat.setString(5, data.getMembername());
			
			int rs = db.sendUpdateQuery();
			
			if(rs == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	public boolean remove(ManagementMemberVO data) {
		String query = "DELETE FROM gym_member WHERE membername = ?";
		
		PreparedStatement pstat;
		try {
			pstat = db.getPstat(query);
			pstat.setString(1, data.getMembername());
			
			int rs = db.sendDeleteQuery();
			
			if(rs == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public ManagementMemberVO get(String membername) {
		String query = "SELECT * FROM gym_member WHERE membername = ?";
		
		try {
			PreparedStatement pstat = db.getPstat(query);
			pstat.setString(1, membername);
			
			ResultSet rs = db.sendSelectQuery();
			
			if(rs.next()) {
				ManagementMemberVO data = new ManagementMemberVO();
				data.setMemberid(rs.getInt("MEMBERID"));
				data.setMembername(rs.getString("MEMBERNAME"));
				data.setGender(rs.getString("GENDER"));
				data.setAge(rs.getInt("AGE"));
				data.setPhonenumber(rs.getString("PHONENUMBER"));
				data.setSport(rs.getString("SPORT"));
				data.setCreatedate(rs.getDate("CREATEDATE"));
				return data;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ManagementMemberVO> searchSport(String sport) {
		String query = "SELECT * FROM gym_member WHERE sport = ? ORDER BY MEMBERID ASC";
		
		try {
			PreparedStatement pstat = db.getPstat(query);
			pstat.setString(1, sport);
			
			ResultSet rs = db.sendSelectQuery();

			List<ManagementMemberVO> datas = new ArrayList<ManagementMemberVO>();
			
			if(rs.isBeforeFirst()) {
				while(rs.next()) {
					ManagementMemberVO data = new ManagementMemberVO();
					data.setMemberid(rs.getInt("MEMBERID"));
					data.setMembername(rs.getString("MEMBERNAME"));
					data.setGender(rs.getString("GENDER"));
					data.setAge(rs.getInt("AGE"));
					data.setPhonenumber(rs.getString("PHONENUMBER"));
					data.setSport(rs.getString("SPORT"));
					data.setCreatedate(rs.getDate("CREATEDATE"));
					
					datas.add(data);
				}
				return datas;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}
