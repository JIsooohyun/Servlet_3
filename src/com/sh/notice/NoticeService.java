package com.sh.notice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sh.action.Action;
import com.sh.action.ActionForward;
import com.sh.page.SearchMakePage;
import com.sh.page.SearchPager;
import com.sh.page.SearchRow;
import com.sh.upload.UploadDAO;
import com.sh.upload.UploadDTO;

public class NoticeService implements Action{
	
	private NoticeDAO noticeDAO;
	private UploadDAO uploadDAO;
	
	public NoticeService() {
		noticeDAO = new NoticeDAO();
		uploadDAO = new UploadDAO();
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
		
		String search = request.getParameter("search");
		
		//메서드 호출
		SearchMakePage s = new SearchMakePage(curPage, kind, search);
		
		//1.row
		SearchRow searchRow = s.makeRow();
		ArrayList<NoticeDTO> ar = null;
		try {
			ar = noticeDAO.selectList(searchRow);
			//2. page
			int totalCount =noticeDAO.getTotalCount(searchRow);
			SearchPager searchPager = s.makePage(totalCount);
			
			request.setAttribute("pager", searchPager);
			request.setAttribute("list", ar);
			actionForward.setCheck(true); //포워드로 보내겠다.
			actionForward.setPath("../WEB-INF/views/notice/noticeList.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "Server Error");
			request.setAttribute("path", "../index.do");
			actionForward.setCheck(true);
			actionForward.setPath("../WEB-INF/views/common/resutl.jsp");
		}
		
		return actionForward;
	}

	@Override
	public ActionForward select(HttpServletRequest request, HttpServletResponse response) {
		ActionForward  actionForward = new ActionForward();
		//글이 있으면 출력
		//글이 없으면 삭제되었거나 없는 글입니다. (alert창으로 띄우기)->noticeList로 돌아가기
		NoticeDTO noticeDTO =null;
		UploadDTO uploadDTO = null;
		try {
			int num = Integer.parseInt(request.getParameter("num"));  //숫자가 안넘오고 문자가 넘어올떄 오류방지하기 위해서
			noticeDTO = noticeDAO.selectOne(num);
			uploadDTO = uploadDAO.selectOne(num);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String path = "";
		if(noticeDTO!=null) {
			request.setAttribute("noticeDTO", noticeDTO);
			request.setAttribute("upload", uploadDTO);
			path = "../WEB-INF/views/notice/noticeSelect.jsp";
		}else{
			request.setAttribute("msg", "No Data");
			request.setAttribute("path", "./noticeList");
			path = "../WEB-INF/views/common/result.jsp";
		}
		actionForward.setCheck(true);
		actionForward.setPath(path);
		return actionForward;
	}

	@Override
	public ActionForward insert(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		String method = request.getMethod();//get인지 post인지 확인
		String path="../WEB-INF/views/notice/noticeWite.jsp"; //get일 때 가는 방식
		boolean check=true;
		
		if(method.equals("POST")) {//짤려진 request를 하나로 합쳐주는 작업을 한다. -> cos 라이브러리
			NoticeDTO noticeDTO = new NoticeDTO();
			
			//1. request를 하나로 합치기
			String saveDirectory = request.getServletContext().getRealPath("/upload");//c부터 upload까지 시작하는 실제 경로//파일을 저장할 디스크 경로
			//System.out.println(saveDirectory);
			int maxPostSize =1024*1024*10;//파일크기 지정 byte
			String encoding = "UTF-8";
			MultipartRequest multi=null;
			//같은 이름이 있으면 자동으로 이름을 바꾼다. DefaultFileRenamePolicy
			try {
				multi = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, new DefaultFileRenamePolicy());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}//이 객체가 만들어지는 동시에 파일이 saveDirectory에 저장이 된다. 
			//request는 받아오는 부분
			String fileName = multi.getFilesystemName("f1");//"파라미터이름"//하드디스크(서버)에 저장된 이름
			//System.out.println("filename : "+fileName);
			String oName = multi.getOriginalFileName("f1");//"파라미터이름"//클라이언트가 업로드 할 때 파일 이름
			//System.out.println("oName : "+oName);
			UploadDTO uploadDTO = new UploadDTO();
			uploadDTO.setFileName(fileName);
			uploadDTO.setoName(oName);
			
			noticeDTO.setTitle(multi.getParameter("title"));
			noticeDTO.setWriter(multi.getParameter("writer"));
			noticeDTO.setContents(multi.getParameter("contents"));
			//String f1 =  multi.getParameter("f1");  //파일명이 넘어온다. 파라미터의 이름 받아오는 부분
			//System.out.println(f1);
			int result = 0;
			
			try {
				int num = noticeDAO.getNum();
				noticeDTO.setNum(num);
				result = noticeDAO.insert(noticeDTO);
				
				uploadDTO.setNum(num);
				result = uploadDAO.insert(uploadDTO);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(result>0) {
				check=false;//redirect로 간다. -> list가지고 와서 뿌려줘야 하는데
				path="./noticeList";//이 주소를 클라이언트한테 보내주는 것이다. 
			}else {
				request.setAttribute("msg", "Write Fail");
				request.setAttribute("path", "./noticeList");
				check=true;
				path = "../WEB-INF/views/common/result.jsp";
			}//POST일 때 사용
		}
		actionForward.setCheck(check);
		actionForward.setPath(path);
		return actionForward;
	}

	@Override
	public ActionForward update(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		String method = request.getMethod();
		NoticeDTO noticeDTO =null;
		int num = Integer.parseInt(request.getParameter("num"));
		
		try {
			noticeDTO = noticeDAO.selectOne(num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String path = "";
		boolean check=true;
		if(noticeDTO!=null) {
			request.setAttribute("noticeDTO", noticeDTO);
			path = "../WEB-INF/views/notice/noticeUpdate.jsp";
		}
		actionForward.setCheck(check);
		actionForward.setPath(path);
		return actionForward;
	}

	@Override
	public ActionForward delete(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		return actionForward;
	}
	
}
