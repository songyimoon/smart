<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 리스트</title>
</head>
<body>
상품 리스트 페이지입니다.<br/>
<table border = 1>
<tr><th>상품번호</th>
	<th>카테고리</th>
	<th>상품명</th>
	<th>가격</th>
	<th>배송비</th></tr>
	
	<c:forEach items="${lists }" var="dto">
	<tr><td><a href="prodDetail.gd?prodNum=${dto.prodNum }">${dto.prodNum }</a></td>
		<td>${dto.ctgr }</td>
		<td>${dto.prodName }</td>
		<td>
		<fmt:formatNumber value="${dto.prodPrice }" type="currency"/>
		</td>
		<td>${dto.prodDelFee }</td></tr>
	</c:forEach>	
</table>

<a href="goodsRegist.gd">상품등록</a>
</body>
</html>