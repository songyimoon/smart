<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
</head>
<body>
<h1 align="center">장바구니 페이지</h1>
<c:forEach items="${lists }" var="dto">
<table border=1 width="600" align="center">

<tr><th colspan="4" align = "center">${dto.productDTO.prodSupplier }</th>
	<th>배송비</th><th>총 적용금액</th></tr>
<tr><td>
	<img src="goods/upload/${dto.productDTO.prodImage.split(',')[0] }" width="100" height="100"></td>
	<td>${dto.productDTO.prodName }</td>
	<td>- &nbsp; ${dto.cartDTO.cartQty }&nbsp;&nbsp;+
	</td>
	<td><fmt:formatNumber value="${dto.cartDTO.cartPrice }" type="currency"/>원
	</td>
	<td><fmt:formatNumber value="${dto.productDTO.prodDelFee }" type="currency"/>원
	</td>	
	<td><fmt:formatNumber value="${dto.cartDTO.cartPrice + dto.productDTO.prodDelFee}" type="currency"/>원</td>
	
	</tr>
</table>
</c:forEach>
</body>
</html>