package controller.venta;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.DAO.SalesDAO;
import model.DTO.ProdTotalDTO;

public class ProdTotalPage {
	public void prodTotal (HttpServletRequest request) {
		SalesDAO dao = new SalesDAO();
		List<ProdTotalDTO> list = dao.prodTotal();
		String googleList="[['상품번호/상품명','총구매횟수','총구매금액','평균금액']";
		for(ProdTotalDTO dto : list) {
			googleList += ",['" +dto.getProdNum() + "/" +dto.getProdName()+"',"+dto.getCount()+","+dto.getSumPrice()+","+dto.getAvg()+"]";
		}
		googleList += "]";	
		request.setAttribute("googleList", googleList);
		request.setAttribute("list", list);
	}

}
