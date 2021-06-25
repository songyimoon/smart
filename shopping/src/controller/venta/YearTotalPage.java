package controller.venta;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.DAO.SalesDAO;
import model.DTO.YearTotalDTO;

public class YearTotalPage {
	public void yearTotal(HttpServletRequest request) {
		SalesDAO dao = new SalesDAO();
		List<YearTotalDTO> list = dao.yearTotal();
		String googleList="[['연도','총구매횟수','총구매금액','평균금액']";
		for(YearTotalDTO dto : list) {
			googleList += ",['" +dto.getYear()+"',"+dto.getCount()+","+dto.getSumPrice()+","+dto.getAvg()+"]";
		}
		googleList += "]";	
		request.setAttribute("googleList", googleList);
		request.setAttribute("list", list);
	}
}
