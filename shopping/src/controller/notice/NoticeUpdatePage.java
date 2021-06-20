package controller.notice;

import java.io.UnsupportedEncodingException;

//import java.util.Date;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import model.DAO.NoticeDAO;
import model.DTO.NoticeDTO;

public class NoticeUpdatePage {
	public void noticeUpdate(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		NoticeDTO dto = new NoticeDTO();
		dto.setNoticeCon(request.getParameter("noticeCon"));
		dto.setNoticeFile(request.getParameter("noticeFile"));
		dto.setNoticeHits(request.getParameter("noticeHits"));
		dto.setNoticeNo(request.getParameter("noticeNo"));
		dto.setNoticeSub(request.getParameter("noticeSub"));
		dto.setNoticeKind(request.getParameter("noticeKind"));
		NoticeDAO dao = new NoticeDAO();
		dao.noticeUpdate(dto);
		
	}
}
