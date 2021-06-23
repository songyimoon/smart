package controller.goods;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import model.DAO.GoodsDAO;
import model.DTO.ReviewDTO;

public class GoodsReviewWritePage {
	public void reviewUpdate(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ReviewDTO dto = new ReviewDTO();
		dto.setProdNum(request.getParameter("prodNum"));
		dto.setPurchaseNum(request.getParameter("purchaseNum"));
		dto.setReviewContent(request.getParameter("reviewContent"));
		GoodsDAO dao = new GoodsDAO();
		dao.reviewUpdate(dto);	
	}
}
