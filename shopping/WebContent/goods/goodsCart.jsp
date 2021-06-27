<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>

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

<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script>
	function checkQty(prodNum, prodPrice, cartQty){
		if(cartQty > 1){
			location.href="goodsCartQtyDown.gd?prodNum="+prodNum+"&prodPrice="+prodPrice;
		}else{
			alert("최소 수량이 1이어야 합니다.")
			return false;
		}
	}
	function prodChk(){
		var prodTot = 0;
		var chk = document.getElementsByName("prodCk"); // check를 배열로
		var hddchk = document.getElementsByName("cartPrice");
		var cnt=0;
		for(var i = 0; i < chk.length; i++ ){
			if(chk[i].checked == true){
				prodTot += Number(hddchk[i].value);	
				cnt ++;
			}
		}
		document.getElementById("totalPrice").innerHTML=prodTot;
		document.getElementById("prodCnt").innerHTML=cnt;
	}
</script>
</head>
<body>
<h1 align="center">장바구니 페이지</h1>

<table align="center">
<form action="goodsBuy.gd" method="post">
<c:set var="price" value="0"/>
<c:set var="cnt" value="0"/>
	<!-- c:set을 이용해서 price라는 자바변수생성. 변수 선언이기 때문에  for문 밖에 위치 -->
<c:forEach items="${lists }" var="dto">


	<tr><th colspan="4" bgcolor=#e0e0eb>
		<input type="checkbox" value="${dto.productDTO.prodNum}" name="prodCk" onchange="prodChk();" checked/>
			<!-- 체크박스가 prodNum을 가지고 있어서, prodChk 실행 시 상품 번호를 더해버리는 현상이 나타난다. 그래서 아래 hidden만듦 -->
			<input type="hidden" name="cartPrice" value="${dto.cartDTO.cartPrice + dto.productDTO.prodDelFee}" />
			${dto.productDTO.prodSupplier }</th>
		<th bgcolor=#e0e0eb>적용금액</th>
		<th bgcolor=#e0e0eb>배송비</th>
		<th bgcolor=#e0e0eb>총 적용금액</th>
		<td rowspan="2"><input type="button" value="삭제" onclick="javascript:location.href='cartProdDel.gd?prodNum=${dto.productDTO.prodNum}';"/></td>
	</tr>
		
		
	<tr><td><img src="goods/upload/${dto.productDTO.prodImage.split(',')[0] }" width="100" height="100" ></td>
		<td align="center">${dto.productDTO.prodName }</td>
		<!-- html document상에서의 값을 확인하기 위해서 자바스크립트를 사용한다. -->
		<td align="center"><a href="javascript:checkQty('${dto.productDTO.prodNum}','${dto.productDTO.prodPrice }','${dto.cartDTO.cartQty }')">-</a> &nbsp;&nbsp; 
			${dto.cartDTO.cartQty }&nbsp;&nbsp;  
			<a href="goodsCartAdd.gd?prodNum=${dto.productDTO.prodNum }&qty=1&&prodPrice=${dto.productDTO.prodPrice}">+</a>
		</td>
		<td align="right"><fmt:formatNumber value="${dto.productDTO.prodPrice }" type="number"/>원
		</td>
		<td align="right"><fmt:formatNumber value="${dto.cartDTO.cartPrice }" type="number"/>원
		</td>
		<td align="right"><fmt:formatNumber value="${dto.productDTO.prodDelFee }" type="number"/>원
		</td>	
		<td align="right"><fmt:formatNumber value="${dto.cartDTO.cartPrice + dto.productDTO.prodDelFee}" type="currency"/>원</td>	
		</tr>

	

<c:set var="cnt" value="${cnt = cnt + 1}"/>
<!-- (누적sum개념) price에다가 카트DTO의 카트에 있는 금액인 cartPrice랑 배송비를 더하도록 하겠다. (기존 price값이 있으면 누적으로 더하기) -->
<c:set var="price" value="${dto.cartDTO.cartPrice + dto.productDTO.prodDelFee + price }" />

</c:forEach>

<tr><td colspan="4" align="center">전체 합계</td>
	<td colspan="2" align="left"> 상품 수: <br />
								   총 합계: </td>
	<td colspan="2" align="right"><span id="prodCnt">${cnt }</span>개<br />
								  <span id="totalPrice"> <fmt:formatNumber value= "${price }" type="number"/></span>원</td></tr>
									 

<tr><td colspan="8" align="center">
	<input type="submit" value="구매하기"/>
	<input type="button" value="홈으로" onclick="javascript:location.href='main.sm'"/>
	
	</td></tr>

</form>
</table>

</body>
</html>