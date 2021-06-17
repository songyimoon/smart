package controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberController extends HttpServlet
							  implements Servlet{

	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String requestURI=request.getRequestURI();	
		String contextPath=request.getContextPath();	
		String command=requestURI.substring(contextPath.length());	
		
		if(command.equals("/memAgree.mem")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/agree.jsp");
			dispatcher.forward(request, response);
		}else if (command.equals("/memRegist.mem")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/memberForm.jsp");
			dispatcher.forward(request, response);
		}else if (command.equals("/memJoin.mem")) {
			MemberJoinPage action = new MemberJoinPage();
			action.memInsert(request);
			response.sendRedirect("main.sm");
		}else if (command.equals("/memList.mem")){
			MemberListPage action = new MemberListPage();
			action.memList(request);
			response.setCharacterEncoding("utf-8");
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/memberList.jsp");
			dispatcher.include(request, response);
		}else if (command.equals("/memInfo.mem")) {
			MemberInfoPage action = new MemberInfoPage();
			action.memInfo(request);
			response.setCharacterEncoding("utf-8"); // 웹브라우저에서 깨지면 response, 이클립스 내에서 깨지면 request에 set~
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/memberInfo.jsp");
			dispatcher.forward(request, response);
		}else if (command.equals("/memMod.mem")) {
			MemberInfoPage action = new MemberInfoPage();
			action.memInfo(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/memberModify.jsp");
			dispatcher.forward(request, response);
		}else if (command.equals("/memModifyOk.mem")) {
			MemberModifyPage action = new MemberModifyPage();
			action.memUpdate(request);
			response.sendRedirect("memList.mem");
		}else if (command.equals("/memDel.mem")) {
			MemberDeletePage action = new MemberDeletePage();
			action.memDel(request);
			response.sendRedirect("memList.mem");	
		}else if (command.equals("/myPage.mem")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/memMyPage.jsp");
			dispatcher.forward(request, response);								
		}else if (command.equals("/memDetail.mem")) {
			MemberDetailPage action = new MemberDetailPage();
			action.memberDetail(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/memDetail.jsp");
			dispatcher.forward(request, response);			
		}else if (command.equals("/memSujung.mem")) {
			MemberDetailPage action = new MemberDetailPage();
			action.memberDetail(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/memSujung.jsp");
			dispatcher.forward(request, response);			
		}else if (command.equals("/memSujungOk.mem")) {
			MemberUpdatePage action = new MemberUpdatePage();
			int i = action.memberUpdate(request);
			if(i==1) { 
				response.sendRedirect("memDetail.mem");
			}else { 
				response.sendRedirect("memSujung.mem");
			}	
		}else if (command.equals("/memOut.mem")) {		
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/outPw.jsp");
			dispatcher.forward(request, response);	
		}else if (command.equals("/memOutOk.mem")) {
			MemberOutPage action = new MemberOutPage();		
			int i = action.memOut(request);
			if(i == 1) {
				response.sendRedirect("main.sm"); // 비밀번호가 틀리면			
			}else {
				response.sendRedirect("memOut.mem"); // 비밀번호가 맞으면
			}
		}else if (command.equals("/memPwChange.mem")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/pwChange.jsp");
			dispatcher.forward(request, response);	
			
			
		}else if(command.equals("/pwChangeOk.mem")) {
			MemberPwConfirmPage action = new MemberPwConfirmPage();
			String path = action.pwConfirm(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher(path);
			dispatcher.forward(request, response);	
			
			
			
		}else if (command.equals("/ChangePw.mem")) {	
			MemberPwChangePage action = new MemberPwChangePage();
			int i = action.pwChange(request);
			if ( i == 1 ) {
				response.sendRedirect("main.sm");
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("member/pwChange.jsp");
				dispatcher.forward(request, response);		
			}
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
