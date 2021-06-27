<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>판매현황</title>

<style type="text/css">
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap');

body {
  font-family: 'Noto Sans KR', sans-serif;
}
table{
  font-size: 15px/1;
  width: 1000px;
  border: 1px solid;
  border-spacing: 8px;
}

  
</style>

</head>
<body align="center">
<h1>판매 현황</h1><br/>

판매 리스트 | <a href="customerTotal.vnt" target="_blank">고객별 판매 현황</a> | 
			<a href="prodTotal.vnt" target="_blank">상품별 판매 현황</a> | 
			<a href="yearTotal.vnt" target="_blank">년별 판매 현황</a> | 
			<a href="monthTotal.vnt" target="_blank">월별 판매 현황</a><hr/>
 

<form action = "venta.vnt" method="post" name="frm">
아이디: <input type="text" name="memId" />
	   <input type="submit" value="검색">
</form>

<hr/>
<table align="center">


	<tr bgcolor=#e0e0eb><th>고객아이디</th><th>고객명</th><th>상품명</th><th>수량</th><th>판매일</th><th>배송상태</th></tr>
	<c:forEach items="${list }" var="dto">
		<tr><td align="center"> 
			<c:if test="${dto.memId == null}">비회원</c:if>
			<c:if test="${dto.memId != null}">
			<a href="userSales.vnt?memId=${dto.memId }" target="_blank">${dto.memId }</a>
			</c:if>
		</td>
		<td align="center">
			<c:if test="${dto.memName == null}">비회원</c:if>
			<c:if test="${dto.memName != null}">${dto.memName }</c:if>	
		</td>
		<td align="center">${dto.prodName }</td><td>${dto.purchaseQty }</td><td align="center">${dto.purchaseDate}</td>
		<td align="center">
			<a href="createDelivery.vnt?purchaseNum=${dto.purchaseNum }">
			<c:if test="${dto.deliveryNum == null }">배송등록</c:if>
			<c:if test="${dto.deliveryNum != null }">배송수정</c:if>
			</a>
		</td></tr>
	</c:forEach>
	
</table>
<p><input type="button" value="홈으로" onclick="javascript:location.href='main.sm'"/></p>
</body>
</html>