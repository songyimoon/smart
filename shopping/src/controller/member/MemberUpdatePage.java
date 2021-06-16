package controller.member;

import java.util.Date;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.DAO.MemberDAO;
import model.DTO.AuthInfo;
import model.DTO.MemberDTO;

public class MemberUpdatePage {
	public int memberUpdate(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		HttpSession session = request.getSession();
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		MemberDTO dto = new MemberDTO();
		dto.setDetailadd(request.getParameter("detailadd"));
		dto.setMemAccount(request.getParameter("memAccount"));
		dto.setMemAddress(request.getParameter("memAddress"));
		dto.setMemEmail(request.getParameter("memEmail"));
		dto.setMemEmailCk(request.getParameter("memEmailCk"));
		dto.setMemPhone(request.getParameter("memPhone"));
		dto.setPostNumber(request.getParameter("postNumber"));
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sf.parse(request.getParameter("memBirth"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		dto.setMemBirth(date);
		
		dto.setMemPw(request.getParameter("memPw"));
		dto.setMemId(authInfo.getUserId());
		
		if(request.getParameter("memPw").equals(authInfo.getUserPw())) {		
			MemberDAO dao = new MemberDAO();
			dao.memUpdate(dto);
			session.removeAttribute("pwFail"); // 일치하면 fail을 없앤다
			return 1;
		}else {
			session.setAttribute("pwFail", "비밀번호가 일치하지 않습니다.");
			return 2;
		}
	}	
}
