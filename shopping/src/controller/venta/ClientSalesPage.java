package controller.venta;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.DAO.SalesDAO;
import model.DTO.ClientSalesDTO;

public class ClientSalesPage {
	public void clientSale(HttpServletRequest request) {
		String memId = request.getParameter("memId");
		if(memId == "") {
			memId=null;
		}
		SalesDAO dao = new SalesDAO();
		List<ClientSalesDTO> list = dao.salesList(memId);
		request.setAttribute("list", list);
	}
}
