package controller.goods;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.DAO.GoodsDAO;
import model.DTO.AuthInfo;
import model.DTO.PurchaseDTO;

public class GoodsOrderPage {
	public String goodsOrder(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpSession session=request.getSession();
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		String memId=authInfo.getUserId();

		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String purchaseNum = df.format(day);
		
		PurchaseDTO dto = new PurchaseDTO();
		dto.setPurchaseTotPrice(request.getParameter("purchaseTotPrice"));
		dto.setMemId(memId);
		dto.setPurchaseAddr(request.getParameter("purchaseAddr"));
		dto.setPurchaseMethod(request.getParameter("purchaseMethod"));
		dto.setPurchaseNum(purchaseNum);
		dto.setPurchaseRequest(request.getParameter("purchaseRequest"));
		dto.setReceiverName(request.getParameter("receiverName"));
		dto.setReceiverPhone(request.getParameter("receiverPhone"));
		GoodsDAO dao = new GoodsDAO();
		dao.purchaseInsert(dto);
		String [] prodNums = request.getParameter("prodNums").split(",");
		//order.jsp 55번째줄 확인
		for(String prodNum : prodNums) { // 내가 선택한 만큼 상품번호를 넣어주어야 해서 for문
			dao.purchaseListInsert(purchaseNum,prodNum,memId);
		}
		for(String prodNum : prodNums) {
			dao.cartDel(prodNum,memId);
		}
		return purchaseNum+","+dto.getPurchaseTotPrice();
		// return값은 1개라서 두개를 붙여서 보낸다. 가서 split쓰자
	}
}
