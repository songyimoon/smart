package controller.venta;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.DAO.SalesDAO;
import model.DTO.CustomerTotalDTO;

public class CustomerTotalPage {
	public void customerTotal(HttpServletRequest request) {
		SalesDAO dao = new SalesDAO();
		List<CustomerTotalDTO> list = dao.customerTotal();
		String googleList="[['아이디/이름','총구매횟수','총구매금액','평균금액']";
		for(CustomerTotalDTO dto : list) {
			googleList += ",['" +dto.getMemId() + "/" +dto.getMemName()+"',"+dto.getCount()+","+dto.getSumPrice()+","+dto.getAvg()+"]";
		}
		googleList += "]";
		
		request.setAttribute("googleList", googleList);
		request.setAttribute("list", list);
	}
}
