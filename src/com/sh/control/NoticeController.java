package com.sh.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.action.ActionForward;
import com.sh.notice.NoticeDAO;
import com.sh.notice.NoticeService;

/**
 * Servlet implementation class NoticeController
 */
@WebServlet("/NoticeController")
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private NoticeService noticeService;
    public NoticeController() {
        super();
        noticeService = new NoticeService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//루트 밑에 /notice/notice***이라고 온다
		String command = request.getPathInfo(); //이걸 통해서 /notice****만 남는다. 
		ActionForward actionForward = null;
		
		if(command.equals("/noticeList")) {
			actionForward=noticeService.list(request, response);
		}else if(command.equals("/noticeSelect")) {
			//System.out.println("dd");왔는지 안왔는지 확인
			actionForward =noticeService.select(request, response);
		}else if(command.equals("/noticeWrite")) {
			actionForward = noticeService.insert(request, response);
		}else if(command.equals("/noticeUpdate")) {
			actionForward = noticeService.update(request, response);
		}else if(command.equals("/noticeDelete")) {
			
			actionForward = noticeService.delete(request, response);
		}else {
			
			actionForward = new ActionForward();
		}
		
		if(actionForward.isCheck()) { //true라면 forward
			RequestDispatcher view = request.getRequestDispatcher(actionForward.getPath());
			view.forward(request, response);
		}else {//아니라면 redirect
			response.sendRedirect(actionForward.getPath());
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
