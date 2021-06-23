<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
	pageContext.setAttribute("br", "\n");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품정보</title>

<script>
	function onQty(){
		var qty = document.frm.purchaseQty.value;
		document.getElementById("tot").innerHTML = qty * ${dto.prodPrice}
	}
	function goodsCartAdd(prodNum){
		var qty = document.frm.purchaseQty.value;
		location.href="goodsCartAdd.gd?prodNum="+prodNum+"&qty="+qty+"&prodPrice=${dto.prodPrice}";
	}
</script>
</head>
<body>
<p><p>
<form action="#" name="frm" method="post">
<input type="hidden" name="prodNum" value="${dto.prodNum }"/>
<h1 align="center">
${dto.ctgr }의 ${dto.prodName } 상품 설명입니다.</h1> 


<table width="800" align="center">


	<tr><td rowspan="6">
		<img width = "300" height="300" src="goods/upload/${dto.prodImage.split(',')[0] }" /></td>
		<!-- 대표이미지 설정하는 방법: split사용해서 첫번째 값 가져오기 -->
		<td>상품명: ${dto.prodName }</td></tr>
	
		
	<tr><td align="left">가격: <fmt:formatNumber value="${dto.prodPrice }" type="currency"/></td></tr>
	
	
	<tr><td align="left">배송비: 
	
			<c:if test="${dto.prodDelFee == 0 }">무료배송</c:if>
			<c:if test="${dto.prodDelFee != 0 }"><fmt:formatNumber value="${dto.prodDelFee}" type="currency"/></c:if>
			</td></tr>
			
	<tr><td>
		수량: <input type="number" size="3" min="1" name="purchaseQty" value="1" onchange="onQty();"/>
		</td></tr>
	
	<tr><td align="right">총 상품금액: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<span id="tot"><fmt:formatNumber value="${dto.prodPrice}" type="currency"/></span></td></tr>
	
	 
	<tr><td align="right">
			<input type="button" value="장바구니" onclick="goodsCartAdd('${dto.prodNum}');" />
			<input type="submit" value="바로 구매"/></td></tr>
			
	<tr><td colspan="2">
			<h3>상품 상세 내용</h3>
			용량: ${dto.prodCapacity }<br/>
			공급업체: ${dto.prodSupplier }<br/>
			상세정보: ${dto.prodDetail }<br/>
			
			<c:forTokens items="${dto.prodImage }" delims="," var="file">
				<c:if test="${file!=null }">
				<img width = "800" src="goods/upload/${file }"><br/>
				</c:if>
			</c:forTokens>
		</td></tr>
</table>
</form>
리뷰
<hr />
<c:forEach items="${list }" var="dto">
	${dto.memId } / ${dto.reviewDate } <br />
	${fn:replace(dto.reviewContent,br,"<br />")}<br />
	<c:if test="${dto.reviewImg != null }">
		<img src="goods/review/${dto.reviewImg }"/>
	</c:if>
</c:forEach>
</body>
</html>