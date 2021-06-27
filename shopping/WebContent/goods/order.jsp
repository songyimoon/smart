<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문결제</title>
<style type="text/css">
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap');
*{text-decoration:none;
  color: black;
}
body {
  font-family: 'Noto Sans KR', sans-serif;
}
table{
  font-size: 15px/1;
  width: 850px;
  border: 1px solid;
  border-spacing: 8px;
}
</style>
</head>
<body>
<h1>주문서</h1>
<hr />
<h3>1. 주문 상품 </h3><br />
<form action="goodsOrder.gd" method="post">
<table>
	<tr><th colspan="2">상품정보</th>
		<th>적용금액</th>
		<th>판매자</th>
		<th>배송비</th></tr>
		
<c:set var="cartPrice" value="0"/>	
<c:set var="prodDelFee" value="0"/>		
<c:set var="prodNums" value=""/>
	
<c:forEach items="${list }" var="dto">	
	<tr><td>
		<img src="goods/upload/${dto.productDTO.prodImage.split(',')[0] }" width="70"/>
		</td>
		<td>상품명<br/>
		
				${dto.productDTO.prodName }&nbsp;&nbsp;&nbsp;
				${dto.cartDTO.cartQty }개 /
				${dto.productDTO.prodPrice }원</td>
		
		<td>${dto.cartDTO.cartPrice}</td>
		<td>${dto.productDTO.prodSupplier}</td>
		<td>${dto.productDTO.prodDelFee}</td></tr>
		
<c:set var="cartPrice" value="${cartPrice+dto.cartDTO.cartPrice}"/>	
<c:set var="prodDelFee" value="${prodDelFee+dto.productDTO.prodDelFee}"/>
<c:set var="prodNums" value="${prodNums += dto.productDTO.prodNum += ',' }"/>		
</c:forEach>
</table>



<table>
	<tr><td>상품금액<br />${cartPrice }</td>
		<td>+</td>
		<td>배송비<br />${prodDelFee }</td>
		<td>=</td>
		<td>최종결제금액<br />${cartPrice + prodDelFee }
		<input type="hidden" name="purchaseTotPrice" value="${cartPrice + prodDelFee }"/>
		<input type="hidden" name="prodNums" value="${prodNums }"/>
		
		</td></tr>
</table>

<hr />
<h3>2. 배송 정보 </h3>

<table>
	<tr><td>이름</td><td><input type="text" name="receiverName"></td></tr>
	<tr><td>주소</td><td><input type="text" name="purchaseAddr"></td></tr>
	<tr><td>연락처</td><td><input type="text" name="receiverPhone"></td></tr>
	<tr><td>주문요청사항</td><td><input type="text" name="purchaseRequest"></td></tr>
	<tr><td>결제방법</td>
		<td>
			<select name="purchaseMethod">
				<option>카드</option>
				<option>카카오페이</option>
			</select>
		</td></tr>
</table>
<table>
<tr><td align = "center"><input type="submit" value="결제하기" name=""/>
		<input type="button" value="홈으로" onclick="javascript:location.href='main.sm'"/></td></tr>
</table>
</form>
</body>
</html>