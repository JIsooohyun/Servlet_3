package com.sh.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.sh.page.SearchRow;
import com.sh.util.DBConnector;

public class NoticeDAO {
	
	public int getTotalCount(SearchRow searchRow)throws Exception{
		int result=0;
		Connection conn = DBConnector.getConnect();
		String sql = "select count(num) from notice where "+searchRow.getSearch().getKind()+" like ?";
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setString(1, "%"+searchRow.getSearch().getSearch()+"%");
		ResultSet rs = st.executeQuery();
		
		rs.next();
		
		result = rs.getInt("count(num)");
		
		DBConnector.disConnect(conn, st, rs);
		
		return result;
	}
	
	public int delete(int num)throws Exception{
		Connection conn =  DBConnector.getConnect();
		
		String sql = "delete from notice where num=?";
		
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setInt(1, num);
		
		int result = st.executeUpdate();
		DBConnector.disConnect(conn, st);
		return result;
	}
	public int update(NoticeDTO noticeDTO)throws Exception{
		Connection conn = DBConnector.getConnect();
		
		String sql = "update notice set title=?, contents=?, reg_date=sysdate where num=?";
		
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setString(1, noticeDTO.getTitle());
		st.setString(2, noticeDTO.getContents());
		st.setInt(3, noticeDTO.getNum());
		
		int result = st.executeUpdate();
		
		DBConnector.disConnect(conn, st);
		
		return result;
	}

	public NoticeDTO selectOne(int num) throws Exception{
		Connection conn= DBConnector.getConnect();
		NoticeDTO noticeDTO = null;
		String sql = "select * from notice where num=?";
		
		PreparedStatement st =conn.prepareStatement(sql);
		
		st.setInt(1, num);
		
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			noticeDTO = new NoticeDTO();
			noticeDTO.setNum(rs.getInt("num"));
			noticeDTO.setTitle(rs.getString("title"));
			noticeDTO.setContents(rs.getString("contents"));
			noticeDTO.setWriter(rs.getString("writer"));
			noticeDTO.setReg_date(rs.getString("reg_date"));
			noticeDTO.setHit(rs.getInt("hit"));
		}
		DBConnector.disConnect(conn, st, rs);
		return noticeDTO;
	}
	
	//sequence
	public int getNum()throws Exception{
		int result=0;
		Connection conn = DBConnector.getConnect();
		String sql = "select notice_seq.nextval from dual";//시퀀스 번호 가져오기 dual은 가상의 테이블명
		PreparedStatement st = conn.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		rs.next();
		result = rs.getInt(1);
		DBConnector.disConnect(conn, st, rs);
		return result;
	}
	
	public int insert(NoticeDTO noticeDTO, Connection conn) throws Exception{
		String sql = "insert into notice(num, title, contents, writer, reg_date) values(?, ?, ?, ?, sysdate)";
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setInt(1, noticeDTO.getNum());
		st.setString(2, noticeDTO.getTitle());
		st.setString(3, noticeDTO.getContents());
		st.setString(4, noticeDTO.getWriter());
		
		int result = st.executeUpdate();
		st.close();
		return result;
	}
	public ArrayList<NoticeDTO> selectList(SearchRow searchRow) throws Exception{
		Connection conn = DBConnector.getConnect();
		ArrayList<NoticeDTO> ar = new ArrayList<NoticeDTO>();
		NoticeDTO noticeDTO = null;
		String sql = "select * from "
					+"(select rownum R, p.* from "
					+"(select * from notice where "+searchRow.getSearch().getKind()+" like ? order by num desc) p) "
					+"where R between ? and ?";
		
		PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, "%"+searchRow.getSearch().getSearch()+"%");
		st.setInt(2, searchRow.getStartRow());
		st.setInt(3, searchRow.getLastRow());
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			noticeDTO = new NoticeDTO();
			noticeDTO.setNum(rs.getInt("num"));
			noticeDTO.setTitle(rs.getString("title"));
			noticeDTO.setContents(rs.getString("contents"));
			noticeDTO.setWriter(rs.getString("writer"));
			noticeDTO.setReg_date(rs.getString("reg_date"));
			noticeDTO.setHit(rs.getInt("hit"));
			ar.add(noticeDTO);
		}
		
		DBConnector.disConnect(conn, st, rs);
		
		return ar;
	}
	
}
