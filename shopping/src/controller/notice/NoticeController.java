package controller.notice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoticeController extends HttpServlet
							  implements Servlet{

	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI=request.getRequestURI();	
		String contextPath=request.getContextPath();	
		String command=requestURI.substring(contextPath.length());	
	
	if(command.equals("/noticeList.nt")) {
		NoticeListPage action = new NoticeListPage();
		action.noticeList(request);
		RequestDispatcher dispatcher = request.getRequestDispatcher("notice/noticeList.jsp");
		dispatcher.forward(request, response);
	}else if (command.equals("/noticeListMem.nt")) {
		NoticeListPage action = new NoticeListPage();
		action.noticeList(request);
		response.setCharacterEncoding("utf-8");
		RequestDispatcher dispatcher = request.getRequestDispatcher("notice/noticeListMem.jsp");
		dispatcher.forward(request, response);	
	}else if (command.equals("/noticeRegist.nt")) {
		NoticeNumberPage action = new NoticeNumberPage();
		action.seqNum(request);
		response.setCharacterEncoding("utf-8");
		RequestDispatcher dispatcher = request.getRequestDispatcher("notice/noticeRegist.jsp");
		dispatcher.forward(request, response);
	}else if(command.equals("/noticeJoin.nt")) {
		NoticeJoinPage action = new NoticeJoinPage();
		action.noticeJoin(request);
		response.sendRedirect("noticeList.nt");
	}else if (command.equals("/noticeDetail.nt")) {
		NoticeModifyPage action = new NoticeModifyPage();
		action.noticeModify(request);
		RequestDispatcher dispatcher = request.getRequestDispatcher("notice/noticeModify.jsp");
		dispatcher.forward(request, response);
	}else if(command.equals("/noticeView.nt")) {
		NoticeModifyPage action = new NoticeModifyPage();
		action.noticeModify(request);
		RequestDispatcher dispatcher = request.getRequestDispatcher("notice/noticeView.jsp");
		dispatcher.forward(request, response);		
	}else if(command.equals("/noticeModify.nt")) {
		NoticeUpdatePage action = new NoticeUpdatePage();
		action.noticeUpdate(request);
		response.sendRedirect("noticeList.nt");
	}else if(command.equals("/noticeDel.nt")) {
		NoticeDelPage action = new NoticeDelPage();
		action.noticeDel(request);
		response.sendRedirect("noticeList.nt");
	}	
} 
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
}
