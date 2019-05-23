package com.sh.member;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sh.action.Action;
import com.sh.action.ActionForward;
import com.sh.mUpload.MuploadDAO;
import com.sh.mUpload.MuploadDTO;
import com.sh.util.DBConnector;

public class MemberService implements Action {
	private MemberDAO memberDAO;
	private MuploadDAO muploadDAO;
	
	public MemberService() {
		memberDAO = new MemberDAO();
		muploadDAO = new MuploadDAO();
	}
	
	public ActionForward logout(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		
		return actionForward;
	}
	public ActionForward check(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		actionForward.setPath("../WEB-INF/views/member/memberCheck.jsp");
		actionForward.setCheck(true);
		return actionForward;
	}
	@Override
	public ActionForward list(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		
		
		return actionForward;
	}

	@Override
	public ActionForward select(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		HttpSession session = null;
		String method= request.getMethod();
		String path = "../WEB-INF/views/member/memberLogin.jsp";
		boolean check = true;
		
		String ckbox = request.getParameter("remember");
		
		if(method.equals("POST")) {
			MemberDTO memberDTO=null;

			try {
				memberDTO = new MemberDTO();
				memberDTO.setId(request.getParameter("id"));
				memberDTO.setPw(request.getParameter("pw"));
				memberDTO = memberDAO.memberLogin(memberDTO);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if(memberDTO!=null) {
				session =  request.getSession();
				session.setAttribute("memberDTO", memberDTO);
				path = "../index.do";
				check = false;
			}else {
				session =  request.getSession();
				session.setAttribute("memberDTO", memberDTO);
				request.setAttribute("msg", "Login Fail");
				request.setAttribute("path", "./memberLogin");
				check=true;
				path="../WEB-INF/views/common/result.jsp";
			}
		}
		if(ckbox !=null) {
			Cookie c = new Cookie("ckbox",request.getParameter("id"));
			c.setMaxAge(60*60);
			response.addCookie(c);
		}else {
			Cookie c = new Cookie("ckbox", "");
			response.addCookie(c);
			
		}
		
		actionForward.setCheck(check);
		actionForward.setPath(path);
		return actionForward;
	}

	@Override
	public ActionForward insert(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		String method = request.getMethod(); //get, post방식 확인
		String path = "../WEB-INF/views/member/memberJoin.jsp";
		boolean check = true;
		
		if(method.equals("POST")) {
			MemberDTO memberDTO = new MemberDTO();
			
			//1. request를 하나로 합치기
			String saveDirectory = request.getServletContext().getRealPath("/upload");
			int maxPostSize = 1024*1024*10;
			String encoding="utf-8";
			
			MultipartRequest multi = null;
			
			try {
				multi = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, new DefaultFileRenamePolicy());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			memberDTO.setId(multi.getParameter("id"));
			memberDTO.setPw(multi.getParameter("pw"));
			memberDTO.setName(multi.getParameter("name"));
			memberDTO.setPhone(multi.getParameter("phone"));
			memberDTO.setEmail(multi.getParameter("email"));
			memberDTO.setAge(Integer.parseInt(multi.getParameter("age")));
			
			String fileName = multi.getFilesystemName("file");
			String oName = multi.getFilesystemName("file");
			
			MuploadDTO muploadDTO = new MuploadDTO();
			muploadDTO.setFileName(fileName);
			muploadDTO.setoName(oName);
			
			int result = 0;
			Connection conn = null;
			
			try {
				conn = DBConnector.getConnect();
				conn.setAutoCommit(false);
				result = memberDAO.memberJoin(memberDTO, conn);
				muploadDTO.setId(multi.getParameter("id"));
				result = muploadDAO.insert(muploadDTO, conn);
				conn.commit();
			} catch (Exception e) {
				result = 0;
				try {
					conn.rollback();
				} catch (Exception e2) {
					e.printStackTrace();
				}
				e.printStackTrace();
			}finally {
				try {
					conn.setAutoCommit(true);
					conn.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(result>0) {
				check=false;
				path = "../index.do";
			}else {
				request.setAttribute("msg", "Join Fail");
				request.setAttribute("path", "./memberJoin");
				check = true;
				path = "../WEB-INF/views/common/result.jsp";
			}
		}
		actionForward.setPath(path);
		actionForward.setCheck(check);
		
		return actionForward;
	}

	@Override
	public ActionForward update(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		return actionForward;
	}

	@Override
	public ActionForward delete(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		return actionForward;
	}
	
}
