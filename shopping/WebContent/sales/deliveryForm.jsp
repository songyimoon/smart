<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action = "deliveryOk.vnt" method="post" name="frm">
<table>
	<tr><td>구매번호</td><td>
		<input type="text" name="purchaseNum" value="${purchaseNum }"></td></tr>
		<tr><td>택배사</td><td>
		<input type="text" name="deliveryCom" value="${dto.deliveryCom }"></td></tr>
		<tr><td>송장번호</td><td>
		<input type="text" name="deliveryNum" value="${dto.deliveryNum }"></td></tr>
		<tr><td>배송일</td><td>
		<input type="date" name="deliveryExpDate" value="${dto.deliveryExpDate }"></td></tr>
		<tr><td>도착예정일</td><td>
		<input type="date" name="arrivalExpDate" value="${dto.arrivalExpDate }"></td>
	</tr>
	<tr>
		<th colspan="2"><input type="submit" value="배송등록 및 수정" /></th>
	</tr>
</table>
</form>
</body>
</html>