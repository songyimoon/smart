package controller.notice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.DAO.NoticeDAO;
import model.DTO.NoticeDTO;

public class NoticeListPage {
	public void noticeList(HttpServletRequest request) {
		NoticeDAO dao = new NoticeDAO();
		List <NoticeDTO> list = dao.noticeList();
		request.setAttribute("lists", list);
	}
}
