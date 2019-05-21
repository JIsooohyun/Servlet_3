package com.sh.point;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

import com.sh.util.DBConnector;

public class PointDAO {
	
	
	//메서드명 getTotalCount
	public int getTotalCount(String kind, String search)throws Exception{
		int result=0;
		Connection conn = DBConnector.getConnect();
		String sql = "select count(num) from point where "+kind+" like ?";
		PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, "%"+search+"%");
		ResultSet rs = st.executeQuery();
		rs.next();
		result = rs.getInt("count(num)");
		DBConnector.disConnect(conn, st, rs);
		return result;
	}
	
	public ArrayList<PointDTO> selectList(String kind, String search, int startRow, int lastRow) throws Exception{
		Connection conn = DBConnector.getConnect();
		ArrayList<PointDTO> ar = new ArrayList<PointDTO>();
		PointDTO pointDTO = null;
		String sql = "select * from " + 
				"(select rownum R, p.* from  " + 
				"(select * from point where "+kind+" like ? order by num desc) p) " + 
				"where R between ? and ?"; //컬럼명은 ?로 처리하면 안된다. 
		
		PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, "%"+search+"%");
		st.setInt(2, startRow);
		st.setInt(3, lastRow);
		
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			pointDTO = new PointDTO();
			pointDTO.setNum(rs.getInt("num"));
			pointDTO.setName(rs.getString("name"));
			pointDTO.setKor(rs.getInt("kor"));
			pointDTO.setEng(rs.getInt("eng"));
			pointDTO.setMath(rs.getInt("math"));
			pointDTO.setTotal(rs.getInt("total"));
			pointDTO.setAvg(rs.getDouble("avg"));
			ar.add(pointDTO);
		}
		
		DBConnector.disConnect(conn, st, rs);
		
		return ar;
	}
	
	public PointDTO selectOne(int num)throws Exception{
		PointDTO pointDTO =null;
		Connection conn = DBConnector.getConnect();
		
		String sql = "select * from point where num=?";
		
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setInt(1, num);
		
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			pointDTO = new PointDTO();
			pointDTO.setNum(rs.getInt(1));
			pointDTO.setName(rs.getString(2));
			pointDTO.setKor(rs.getInt(3));
			pointDTO.setEng(rs.getInt(4));
			pointDTO.setMath(rs.getInt(5));
			pointDTO.setTotal(rs.getInt(6));
			pointDTO.setAvg(rs.getDouble(7));
		}
		
		DBConnector.disConnect(conn, st, rs);
		
		return pointDTO;
	}
	
	public int delete(int num)throws Exception{
		Connection conn = DBConnector.getConnect();
		
		String sql="delete point where num=?";
		
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setInt(1, num);
		
		int result = st.executeUpdate();
		
		DBConnector.disConnect(conn, st);
		
		return result;
	}
	public int update(PointDTO pointDTO) throws Exception{
		Connection conn = DBConnector.getConnect();
		
		String sql = "update point set name=?, kor=?, eng=?, math=?, total=?, avg=? where num=?";
		
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setString(1, pointDTO.getName());
		st.setInt(2, pointDTO.getKor());
		st.setInt(3, pointDTO.getEng());
		st.setInt(4, pointDTO.getMath());
		st.setInt(5, pointDTO.getTotal());
		st.setDouble(6, pointDTO.getAvg());
		st.setInt(7, pointDTO.getNum());
		
		int result = st.executeUpdate();
		
		DBConnector.disConnect(conn, st);
		
		return result;
	}
	public int insert(PointDTO pointDTO) throws Exception{
		Connection conn = DBConnector.getConnect();
		
		String sql = "insert into point values(point_seq.nextval, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setString(1,pointDTO.getName());
		st.setInt(2, pointDTO.getKor());
		st.setInt(3, pointDTO.getEng());
		st.setInt(4, pointDTO.getMath());
		st.setInt(5, pointDTO.getTotal());
		st.setDouble(6, pointDTO.getAvg());
		
		int result = st.executeUpdate();
		
		DBConnector.disConnect(conn, st);
		
		return result;
	}
}
