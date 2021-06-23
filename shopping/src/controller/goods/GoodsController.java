package controller.goods;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.awt.windows.awtLocalization;

public class GoodsController extends HttpServlet 
							 implements Servlet{

public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String requestURI=request.getRequestURI();	
	String contextPath=request.getContextPath();	
	String command=requestURI.substring(contextPath.length());	
	
	if(command.equals("/goodsList.gd")) {
		GoodsListPage action = new GoodsListPage();
		action.goodsList(request);
		RequestDispatcher dispatcher = request.getRequestDispatcher("goods/goodsList.jsp");
		dispatcher.forward(request, response);
		
	}else if(command.equals("/goodsRegist.gd")) {
		GoodsNumberPage action = new GoodsNumberPage();
		action.seqNum(request);
		response.setCharacterEncoding("utf-8");
		RequestDispatcher dispatcher = request.getRequestDispatcher("goods/goodsJoin.jsp");
		dispatcher.forward(request, response);
		
	}else if (command.equals("/goodsJoin.gd")) {
		GoodsJoinPage action = new GoodsJoinPage();
		action.goodsJoin(request);
		response.sendRedirect("goodsList.gd");
		
	}else if (command.equals("/prodDetail.gd")) {
		GoodsModifyPage action = new GoodsModifyPage();
		action.goodsModify(request);
		RequestDispatcher dispatcher = request.getRequestDispatcher("goods/goodsModify.jsp");
		dispatcher.forward(request, response);		
		
	}else if (command.equals("/goodsModify.gd")) {
		GoodsUpdatePage action = new GoodsUpdatePage();
		action.goodsUpdate(request);
		response.sendRedirect("goodsList.gd");
		
	}else if (command.equals("/prodDel.gd")) {
		GoodsDeletePage action = new GoodsDeletePage();
		action.goodsDel(request);
		response.sendRedirect("goodsList.gd");
		
	}else if (command.equals("/prodInfo.gd")){
		response.setCharacterEncoding("utf-8");
		GoodsModifyPage action = new GoodsModifyPage(); 
		action.goodsModify(request);
		RequestDispatcher dispatcher = request.getRequestDispatcher("goods/goodsDetail.jsp");
		dispatcher.forward(request, response);
		
	}else if(command.equals("/goodsCartAdd.gd")) {
		GoodsCartAddPage action = new GoodsCartAddPage();
		action.cartAdd(request);
		response.sendRedirect("goodsCartList.gd");
		
	}else if(command.equals("/goodsCartList.gd")) {
		GoodsCartListPage cartList = new GoodsCartListPage();
		cartList.cartList(request);
		RequestDispatcher dispatcher = request.getRequestDispatcher("goods/goodsCart.jsp");
		dispatcher.forward(request, response);
		
	}else if(command.equals("/goodsCartQtyDown.gd")) {
		GoodsCartQtyDownPage action = new GoodsCartQtyDownPage();
		action.cartQtyDown(request);
		response.sendRedirect("goodsCartList.gd");
		
	}else if(command.equals("/cartProdDel.gd")) {
		GoodsCartProdDelPage action = new GoodsCartProdDelPage();
		action.cartProdDel(request);
		response.sendRedirect("goodsCartList.gd");
		
	}else if(command.equals("/goodsBuy.gd")) {
		GoodsBuyPage action=new GoodsBuyPage();
		action.goodsBuy(request);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("goods/order.jsp");
		dispatcher.forward(request, response);	
		
	}else if (command.equals("/goodsOrder.gd")) {
		GoodsOrderPage action = new GoodsOrderPage();
		String [] purchaseNum=action.goodsOrder(request).split(",");	
		response.sendRedirect("paymentOk.gd?purchaseNum="+purchaseNum[0]+"&purchaseTotPrice="+purchaseNum[1]);
		//배열 첫번째값은 purchaseNum 두번째값은 totPrice
		
	}else if (command.equals("/purchaseCon.gd")) {
		PurchaseListConPage action = new PurchaseListConPage();
		action.purchaseList(request);
		RequestDispatcher dispatcher=request.getRequestDispatcher("goods/purchaseCon.jsp");
		dispatcher.forward(request, response);		
		
	}else if (command.equals("/paymentOk.gd")) {	 
		request.setAttribute("purchaseNum", request.getParameter("purchaseNum"));
		request.setAttribute("purchaseTotPrice", request.getParameter("purchaseTotPrice"));	
		RequestDispatcher dispatcher=request.getRequestDispatcher("goods/payment.jsp");
		dispatcher.forward(request, response);		
		
	}else if(command.equals("/doPayment.gd")) {
		PaymentPage action = new PaymentPage();
		action.payment(request);
		RequestDispatcher dispatcher = request.getRequestDispatcher("goods/buyFinished.jsp");
		dispatcher.forward(request, response);
	
	}else if(command.equals("/goodsReview.gd")) {
		request.setAttribute("prodNum", request.getParameter("prodNum"));
		request.setAttribute("purchaseNum", request.getParameter("purchaseNum"));
		RequestDispatcher dispatcher=request.getRequestDispatcher("goods/goodsReview.jsp");
		dispatcher.forward(request, response);
	}else if (command.equals("/reviewWrite.gd")) {
		GoodsReviewPage action = new GoodsReviewPage();
		action.review(request);
		response.sendRedirect("purchaseCon.gd");
		
	}else if(command.equals("/goodsReviewUpdate.gd")) {
		GoodsReviewInfoPage action = new GoodsReviewInfoPage();
		action.reviewInfo(request);	
		RequestDispatcher dispatcher = request.getRequestDispatcher("goods/goodsReviewModify.jsp");
		dispatcher.forward(request, response);
		
	}else if (command.equals("/reviewUpdate.gd")) {
		GoodsReviewWritePage action = new GoodsReviewWritePage();
		action.reviewUpdate(request);
		response.sendRedirect("purchaseCon.gd");
	}
	
	
	
}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		doProcess(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		doProcess(req, resp);
	}
}
