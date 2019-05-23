package com.sh.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sh.util.DBConnector;

public class MemberDAO {
	public int idCheck(String id)throws Exception{
		int result = 0; //0이면 사용가능한 ID
		Connection conn = DBConnector.getConnect();
		String sql = "select * from member where id= ?";
		PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, id);
		ResultSet rs= st.executeQuery();
		if(rs.next()) {
			result=1;
		}
		DBConnector.disConnect(conn, st, rs);
		return result;
	}
	
	public int memberDelete(String id)throws Exception{
		int result=0;
		Connection conn=DBConnector.getConnect();
		String sql = "delete member where id=?";
		PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, id);
		result = st.executeUpdate();
		DBConnector.disConnect(conn, st);
		return result;
	}
	//update()
	public int memberUpdate(MemberDTO memberDTO)throws Exception{
		int result=0;
		Connection conn = DBConnector.getConnect();
		String sql = "update member set pw=?, name=?, phone=?, email=?, age=? where id=?";
		PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, memberDTO.getPw());
		st.setString(2, memberDTO.getName());
		st.setString(3, memberDTO.getPhone());
		st.setString(4, memberDTO.getEmail());
		st.setInt(5, memberDTO.getAge());
		st.setString(6, memberDTO.getId());
		
		result = st.executeUpdate();
		DBConnector.disConnect(conn, st);
		return result;
	}
	//login메서드
	public MemberDTO memberLogin(MemberDTO memberDTO)throws Exception{
		MemberDTO m = null; //로그인 성공 실패를 가르기 위해서 새로 만듬
		Connection conn = DBConnector.getConnect();
		String sql = "select * from member where id=? and pw=?";
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setString(1, memberDTO.getId());
		st.setString(2, memberDTO.getPw());
		
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			m = new MemberDTO();
			m.setId(rs.getString("id"));
			m.setPw(rs.getString("pw"));
			m.setName(rs.getString("name"));
			m.setPhone(rs.getString("phone"));
			m.setEmail(rs.getString("email"));
			m.setAge(rs.getInt("age"));
		}
		DBConnector.disConnect(conn, st, rs);
		
		return m;
	}
	
	
	//join메서드
	public int memberJoin(MemberDTO memberDTO) throws Exception{
		int result=0;
		Connection conn = DBConnector.getConnect();
		String sql = "insert into member values(?, ?, ?, ?, ?, ?)";
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setString(1, memberDTO.getId());
		st.setString(2, memberDTO.getPw());
		st.setString(3, memberDTO.getName());
		st.setString(4, memberDTO.getPhone());
		st.setString(5,memberDTO.getEmail());
		st.setInt(6, memberDTO.getAge());
		
		result = st.executeUpdate();
		
		DBConnector.disConnect(conn, st);
		
		return result;
	}
}
