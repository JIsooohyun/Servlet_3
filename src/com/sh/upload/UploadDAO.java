package com.sh.upload;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sh.util.DBConnector;

public class UploadDAO {
	
	//select
	public UploadDTO selectOne(int num)throws Exception{
		UploadDTO uploadDTO = null;
		Connection conn = DBConnector.getConnect();
		String sql = "select * from upload where num=?";
		PreparedStatement st = conn.prepareStatement(sql);
		st.setInt(1, num);
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			uploadDTO = new UploadDTO();
			uploadDTO.setPnum(rs.getInt("pnum"));
			uploadDTO.setNum(rs.getInt("num"));
			uploadDTO.setoName(rs.getString("oName"));
			uploadDTO.setFileName(rs.getString("fileName"));
		}
		DBConnector.disConnect(conn, st, rs);
		
		return uploadDTO;
	}
	
	//insert
	public int insert(UploadDTO uploadDTO, Connection conn) throws Exception{
		int result =0;
		String sql = "insert into upload values (point_seq.nextval, ?, ?, ?)";
		PreparedStatement st = conn.prepareStatement(sql);
		st.setInt(1, uploadDTO.getNum());
		st.setString(2, uploadDTO.getoName());
		st.setString(3, uploadDTO.getFileName());
		result = st.executeUpdate();
		st.close();
		return result;
	}
}
