package com.sh.notice;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.action.Action;
import com.sh.action.ActionForward;

public class NoticeService implements Action{
	
	private NoticeDAO noticeDAO;
	
	public NoticeService() {
		noticeDAO = new NoticeDAO();
	} 

	@Override
	public ActionForward list(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		
		int curPage = 1;
		try {
			curPage = Integer.parseInt(request.getParameter("curPage"));
		}catch (Exception e) {
		//예외가 발생하면 curPage는 1로 내려온다. 
		}
		String kind = request.getParameter("kind");
		
		if(kind==null) {
			kind="title";
		}else if(kind.equals("c")) {
			kind = "contents";
		}else if(kind.equals("w")) {
			kind = "writer";
		}else {
			kind = "title";
		}
		
		String search = request.getParameter("search");
		
		if(search==null) {
			search = "";
		}
		
		////////////////////////
		//1. StartRow, LastRow계산하기
		int perPage=10; //curPage와 perPage를 계산
		int startRow=(curPage-1)*perPage+1;
		int lastRow = curPage*perPage;
		
		//2. 페이징처리 
		try {
			//2-1. 전체 글의 개수 가져오기
			int totalCount = noticeDAO.getTotalCount(kind, search);
			//2-2. 10개씩 몇개가 나오는 건가 -> 전체페이지의 개수
			int totalPage = totalCount/perPage;
			if(totalCount%perPage!=0) {
				totalPage++;
			}
			//2-3.전체블럭의 개수를 구하기
			int perBlock = 5; //블럭당 숫자의 개수
			int totalBlock = totalPage/perBlock;
			if(totalPage%perBlock !=0) {
				totalBlock++;
			}
			
			//2-4. curPage를 사용해서 현재 블럭 찾기
			int curBlock = curPage/perBlock;
			if(curPage%perBlock!=0) {
				curBlock++;
			}
			//2-5. startNum, lastNum구하기
			int startNum = (curBlock-1)*perBlock+1;
			int lastNum =curBlock*perBlock;
			
			//2-6. curBlock이 마지막 블록일 때 (totalBlock과 같을 떄)
			if(curBlock==totalBlock) {
				lastNum = totalPage;
			}
			
			ArrayList<NoticeDTO> ar = noticeDAO.selectList(kind, search, startRow, lastRow);
			request.setAttribute("list", ar);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		actionForward.setCheck(true); //포워드로 보내겠다.
		actionForward.setPath("../WEB-INF/views/notice/noticeList.jsp");
		return actionForward;
	}

	@Override
	public ActionForward select(HttpServletRequest request, HttpServletResponse response) {
		ActionForward  actionForward = new ActionForward();
		actionForward.setCheck(true);
		actionForward.setPath("../WEB-INF/views/notice/noticeSelect.jsp");
		return actionForward;
	}

	@Override
	public ActionForward insert(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		actionForward.setCheck(true);
		actionForward.setPath("../WEB-INF/views/notice/noticeWite.jsp");
		return actionForward;
	}

	@Override
	public ActionForward update(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		actionForward.setCheck(true);
		actionForward.setPath("../WEB-INF/views/notice/noticeUpdate.jsp");
		return actionForward;
	}

	@Override
	public ActionForward delete(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
