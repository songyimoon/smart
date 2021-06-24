<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1" witdh="800" align="center">
<c:forEach items="${list }" var="dto">
	<tr><th>구매자</th><th>구매자아이디</th><th>구매자연락처</th></tr>
	<tr><td>${dto.memName }</td><td>${dto.memId }</td><td>${dto.memId }</td></tr>
	<tr><th>구매번호</th><th>상품명</th><th>구매일</th></tr>
	<tr><td>${dto.purchaseNum }</td><td>${dto.prodName }</td><td>${dto.purchaseDate }</td></tr>
	<tr><th>수량</th><th colspan="2">적용금액</th></tr>
	<tr><td>${dto.purchaseQty }</td><td colspan="2">${dto.purchasePrice }</td></tr>
	<tr><th>수취인</th><th>수취주소</th><th>수취인연락처</th></tr>	
	<tr><td>${dto.receiverName }</td><td>${dto.purchaseAddr }</td><td>${dto.receiverPhone }</td></tr>	
	<tr><td colspan="3">&nbsp;</td></tr>	
</c:forEach>	
</table>
</body>
</html>