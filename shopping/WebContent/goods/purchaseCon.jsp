<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문확인</title>

<style type="text/css">
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap');

body {
  font-family: 'Noto Sans KR', sans-serif;
}
table{
  font-size: 15px/1;
  width: 1200px;
  border: 1px solid;
  border-spacing: 8px;
}

</style>
</head>
<body>
<table align="center">
<tr bgcolor=#e0e0eb>
	<th>주문일(결제번호)</th>
	<th>상품명/주문번호</th>
	<th>판매자</th> 
	<th>주문상태</th></tr>	
<c:forEach items = "${list }" var="dto">
<tr><td>
	${dto.purchaseDate } / ${dto.paymentApprNum} </td>
	<td rowspan="2"><img width="50" src="goods/upload/${dto.prodImage.split(',')[0]}"/>	${dto.prodName }/${dto.purchaseNum }</td>	
	<td rowspan="2">${dto.prodSupplier }</td>
	<td rowspan="2">	

   <c:if test="${dto.paymentApprNum == null }">
      <a href="paymentOk.gd?purchaseNum=${dto.purchaseNum }&purchaseTotPrice=${dto.purchaseTotPrice}">결제하기</a><br /></c:if>
   <c:if test="${dto.paymentApprNum != null }">
      결제완료<br /> 
      
      <c:if test="${dto.reviewContent == null }">
         <a href="goodsReview.gd?purchaseNum=${dto.purchaseNum }&prodNum=${dto.prodNum }">리뷰작성</a>
      </c:if>      
      <c:if test="${dto.reviewContent != null }"> 
         <a href="goodsReviewUpdate.gd?purchaseNum=${dto.purchaseNum }&prodNum=${dto.prodNum }">리뷰수정</a>
      </c:if>
   </c:if>
	
	</td></tr>
	<tr><td>결제금액:
	<fmt:formatNumber value="${dto.purchaseTotPrice }" type="number" />원
	 </td></tr>
	
	
</c:forEach>	 
	<tr><td colspan="4" align="center"><input type="button" value="홈으로" onclick="javascript:location.href='main.sm'"/></td></tr>
</table>
</body>
</html>

 
