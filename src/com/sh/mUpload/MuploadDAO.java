package com.sh.mUpload;

import java.sql.Connection;
import java.sql.PreparedStatement;


public class MuploadDAO {
	
	//insert
	public int insert(MuploadDTO muploadDTO, Connection conn) throws Exception {
		int result = 0;
		String sql = "insert into mUpload values(point_seq.nextval, ?, ?, ?)";
		PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, muploadDTO.getId());
		st.setString(2, muploadDTO.getoName());
		st.setString(3, muploadDTO.getFileName());
		result = st.executeUpdate();
		st.close();
		return result;
	}

}
