package controller.venta;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import model.DAO.SalesDAO;
import model.DTO.DeliveryDTO;

public class DeliveryOkPage {
	public void execute(HttpServletRequest request) {
		DeliveryDTO dto = new DeliveryDTO();
		
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date arrivalExpDate = null;
		Date deliveryExpDate = null;
		try {
			arrivalExpDate = sf.parse(request.getParameter("arrivalExpDate"));
			deliveryExpDate = sf.parse(request.getParameter("deliveryExpDate"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		
		dto.setArrivalExpDate(arrivalExpDate);
		dto.setDeliveryExpDate(deliveryExpDate);
		
		
		
		dto.setDeliveryCom(request.getParameter("deliveryCom"));
		dto.setDeliveryDelFee(request.getParameter("deliveryDelFee"));
		dto.setDeliveryNum(request.getParameter("deliveryNum"));
		dto.setPurchaseNum(request.getParameter("purchaseNum"));
		SalesDAO dao= new SalesDAO();
		dao.deliveryCreateModify(dto);
	}
}