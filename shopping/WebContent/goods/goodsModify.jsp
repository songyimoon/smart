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
<form action="goodsModify.gd" method="post" name="frm">


<input type="hidden" name="prodNum" value="${dto.prodNum }">
<table border=1 align=center>

	<tr><th>상품번호</th>
		<td>${dto.ctgr }-${dto.prodNum }</td></tr>
	<tr><th>상품명</th>
		<td><input type="text" name="prodName" value="${dto.prodName }"/></td></tr>
	<tr><th>가격</th>
		<td><input type="text" name="prodPrice" value="${dto.prodPrice }"/></td></tr>
	<tr><th>용량</th>
		<td><input type="text" name="prodCapacity" value="${dto.prodCapacity }"></td></tr>
	<tr><th>공급처</th>
		<td><input type="text" name="prodSupplier" value="${dto.prodSupplier }"/></td></tr>
	<tr><th>배송비</th>
		<td><input type="number" name="prodDelFee" min="0" step="1" value="${dto.prodDelFee }"/></td></tr>
	<tr><th>추천상품</th>
		<td><input type="radio" name="recommend" value="Y" <c:if test="${dto.recommend.trim() == 'Y' }">checked</c:if>/>추천
			<input type="radio" name="recommend" value="N" <c:if test="${dto.recommend.trim() == 'N' }">checked</c:if>/>비추천
		</td></tr>
	<tr><th>내용</th>
		<td><textarea rows="6" cols="50" name="prodDetail">${dto.prodDetail }</textarea></td></tr>	
	<tr><th colspan="2">
			<input type="submit" value="상품 수정"/>
			<input type="button" value="취소" onclick="javascript:history.back();"/>
			<input type="button" value="삭제" onclick="javascript:location.href='prodDel.gd?prodNum=${dto.prodNum }'"/>
			<input type="button" value="홈으로" onclick="javascript:location.href='main.sm'"/>
	</th></tr>
</table>

</form>
</body>
</html>