package controller.goods;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.DAO.GoodsDAO;
import model.DTO.AuthInfo;
import model.DTO.ProductCartDTO;

public class GoodsBuyPage {
	public void goodsBuy(HttpServletRequest request) {
		HttpSession session=request.getSession();
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		String memId=authInfo.getUserId();
		String [] prodNums = request.getParameterValues("prodCk"); 
		// DB의 in연산자를 자바에서 사용 못해서 다른 방법을 사용
		// select문을 한번 더 실행시키는 방식
		List <ProductCartDTO> list = new ArrayList<ProductCartDTO>();
		GoodsDAO dao = new GoodsDAO();
		for(String prodNum : prodNums ) {
			ProductCartDTO dto = dao.prodCart(prodNum, memId);
			// index없을때는 이렇게 가져올 수 없다 (프라이머리키는 자동으로 인덱스가 만들어지지)
			list.add(dto);
		}
		request.setAttribute("list", list);
	}
}
