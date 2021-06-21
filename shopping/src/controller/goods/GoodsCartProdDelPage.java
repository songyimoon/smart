package controller.goods;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.DAO.GoodsDAO;
import model.DTO.AuthInfo;
import model.DTO.CartDTO;

public class GoodsCartProdDelPage {
	public void cartProdDel(HttpServletRequest request) {
		HttpSession session = request.getSession();
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		String memId=authInfo.getUserId();
		String prodNum=request.getParameter("prodNum");
		CartDTO dto = new CartDTO();
		dto.setProdNum(prodNum);
		dto.setMemId(memId);
		GoodsDAO dao = new GoodsDAO();
		dao.cartProdDel(dto);	
	}
}
