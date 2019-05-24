package com.sh.qna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.sh.page.SearchRow;
import com.sh.util.DBConnector;


public class QnaDAO {
	//sequence
	public int getNum()throws Exception{
		int result=0;
		Connection conn = DBConnector.getConnect();
		String sql = "select qna_seq.nextval from dual";//시퀀스 번호 가져오기 dual은 가상의 테이블명
		PreparedStatement st = conn.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		rs.next();
		result = rs.getInt(1);
		DBConnector.disConnect(conn, st, rs);
		return result;
	}
	
	public int insert(QnaDTO qnaDTO, Connection conn)throws Exception{
		int result=0;
		String sql = "insert into qna values(?, ?, ?, ?, sysdate, 0, ?, 0, 0)";//시퀀스번호를 여기다가 쓰면 안된다. 
		PreparedStatement st = conn.prepareStatement(sql);
		st.setInt(1, qnaDTO.getNum());
		st.setString(2, qnaDTO.getTitle());
		st.setString(3, qnaDTO.getContents());
		st.setString(4, qnaDTO.getWriter());
		st.setInt(5, qnaDTO.getNum());
		result = st.executeUpdate();
		st.close();
		return result;
	}
	
	//getTotalCount
	public int getTotalCount(SearchRow searchRow)throws Exception{
		int result=0;
		Connection conn = DBConnector.getConnect();
		String sql = "select count(num) from qna where "+searchRow.getSearch().getKind()+" like ?";
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setString(1, "%"+searchRow.getSearch().getSearch()+"%");
		ResultSet rs = st.executeQuery();
		
		rs.next();
		
		result = rs.getInt("count(num)");
		
		DBConnector.disConnect(conn, st, rs);
		
		return result;
	}
	
	public ArrayList<QnaDTO> list(SearchRow searchRow) throws Exception{
		Connection conn = DBConnector.getConnect();
		ArrayList<QnaDTO> ar = new ArrayList<QnaDTO>();
		QnaDTO qnaDTO = null;
		String sql = "select * from "
				+"(select rownum R, q.* from "
				+"(select * from qna where "+searchRow.getSearch().getKind()+" like ? order by ref desc, step asc) q) "
				+"where R between ? and ?";
		PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, "%"+searchRow.getSearch().getSearch()+"%");
		st.setInt(2, searchRow.getStartRow());
		st.setInt(3, searchRow.getLastRow());
		
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			qnaDTO = new QnaDTO();
			qnaDTO.setNum(rs.getInt("num"));
			qnaDTO.setTitle(rs.getString("title"));
			qnaDTO.setContents(rs.getString("contents"));
			qnaDTO.setWriter(rs.getString("writer"));
			qnaDTO.setReg_date(rs.getString("reg_date"));
			qnaDTO.setHit(rs.getInt("hit"));
			qnaDTO.setRef(rs.getInt("ref"));
			qnaDTO.setStep(rs.getInt("step"));
			qnaDTO.setDepth(rs.getInt("depth"));
			ar.add(qnaDTO);
		}
		DBConnector.disConnect(conn, st, rs);
		return ar;
	}

}
