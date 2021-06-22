<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문확인</title>
</head>
<body>
<table border=1 width="800">
<tr>
	<td>주문일(결제번호)</td>
	<td>상품명/주문번호</td>
	<td>판매자</td>
	<td>주문상태</td></tr>
	
<c:forEach items = "${list }" var="dto">
<tr><td>
	${dto.purchaseDate } / ${dto.paymentApprNum} </td>
	
	<td rowspan="2">
	<img width="50" src="goods/upload/${dto.prodImage.split(',')[0]}"/>
	${dto.prodName }/${dto.purchaseNum }</td>
	
	<td rowspan="2">${dto.prodSupplier }</td>
	
	<td rowspan="2">
	
	<c:if test="${dto.paymentApprNum == null }">
		<a href="paymentOk.gd?purchaseNum=${dto.purchaseNum }&purchaseTotPrice=${dto.purchaseTotPrice}">결제하기</a></c:if>
	<c:if test="${dto.paymentApprNum != null }">결제완료</c:if>
	
	
	</td></tr>
	
	<tr><td>결제금액: ${dto.purchaseTotPrice }</td></tr>
</c:forEach>	
</table>
</body>
</html>

 
