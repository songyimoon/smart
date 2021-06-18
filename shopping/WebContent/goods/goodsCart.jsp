<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
</head>
<body>
장바구니 페이지
<c:forEach items="${lists }" var="dto">
<table border=1 width="600" align="center">

<tr><td colspan="4">${dto.productDTO.prodSupplier }</td>
	<td>배송비</td><td>총 적용금액</td></tr>
<tr><td>
	<img src="goods/upload/${dto.productDTO.prodImage.split(',')[0] }" width="100" height="100"></td>
	<td>${dto.productDTO.prodName }</td>
	<td>- &nbsp; ${dto.cartDTO.cartQty }&nbsp;&nbsp;+
	</td><td>${dto.cartDTO.cartPrice }</td>
	<td>${dto.productDTO.prodDelFee }</td>
	<td>${dto.cartDTO.cartPrice + dto.productDTO.prodDelFee}</td>
	
	</tr>
</table>
</c:forEach>
</body>
</html>