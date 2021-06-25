package controller.member;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;


import model.DAO.MemberDAO;
import model.DTO.MemberDTO;

public class IdSearchPage {
	public void idSearch(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		MemberDTO dto = new MemberDTO();
		dto.setMemAddress(request.getParameter("memAddress"));
		dto.setMemPhone(request.getParameter("memPhone"));
		dto.setMemEmail(request.getParameter("memEmail"));
		MemberDAO dao=new MemberDAO();
		dao.idFind(dto);
		if(dto.getMemId()==null) {
			request.setAttribute("idFail","정보가 일치하지 않습니다." );			
		}else {
			request.setAttribute("dto", dto);
			
		}
	}
}
