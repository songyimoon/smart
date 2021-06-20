package controller.goods;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		GoodsModifyPage action = new GoodsModifyPage(); // 상세보기
		action.goodsModify(request);
		RequestDispatcher dispatcher = request.getRequestDispatcher("goods/goodsDetail.jsp");
		dispatcher.forward(request, response);
		
	}else if(command.equals("/goodsCartAdd.gd")) {
		GoodsCartAddPage action = new GoodsCartAddPage();
		action.cartAdd(request);
		response.sendRedirect("goodsCartList.gd");
		
	}else if(command.equals("/goodsCartList.gd")) {
		GoodsCartList cartList = new GoodsCartList();
		cartList.cartList(request);
		RequestDispatcher dispatcher = request.getRequestDispatcher("goods/goodsCart.jsp");
		dispatcher.forward(request, response);
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
