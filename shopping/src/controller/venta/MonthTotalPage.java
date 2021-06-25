package controller.venta;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.DAO.SalesDAO;
import model.DTO.MonthTotalDTO;

public class MonthTotalPage {
	public void monthTotal(HttpServletRequest request) {
		SalesDAO dao = new SalesDAO();
		List<MonthTotalDTO> list = dao.MonthTotal();
		String googleList="[['월','총구매횟수','총구매금액','평균금액']";
		for(MonthTotalDTO dto : list) {
			googleList += ",['" +dto.getMonth()+"',"+dto.getCount()+","+dto.getSumPrice()+","+dto.getAvg()+"]";
		}
		googleList += "]";	
		request.setAttribute("googleList", googleList);
		request.setAttribute("list", list);
	}
}
