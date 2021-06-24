package controller.venta;


import javax.servlet.http.HttpServletRequest;

import model.DAO.SalesDAO;
import model.DTO.DeliveryDTO;

public class DeliveryOkPage {
	public void execute(HttpServletRequest request) {
		DeliveryDTO dto = new DeliveryDTO();
		dto.setArrivalExpDate(request.getParameter("arrivalExpDate"));
		dto.setDeliveryCom(request.getParameter("deliveryCom"));
		dto.setDeliveryDelFee(request.getParameter("deliveryDelFee"));
		dto.setDeliveryExpDate(request.getParameter("deliveryExpDate"));
		dto.setDeliveryNum(request.getParameter("deliveryNum"));
		dto.setPurchaseNum(request.getParameter("purchaseNum"));
		System.out.println(request.getParameter("purchaseNum"));
		SalesDAO dao= new SalesDAO();
		dao.deliverySelect(dto);
		
//		PrintWriter out = response.getWriter();
//		out.println("<script>");
//		out.println();
//		out.println();
		
	}
}
