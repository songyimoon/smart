<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOME</title>

<style type="text/css">
*{text-decoration:none;
  color: black;}
</style>

</head>
<body>
<!-- 로그인 안되었을 때 -->
<c:if test="${empty authInfo }">
<form action="login.sm" method="get" name="frm">
<table border = 1 align = "center">
<tr><td colspan="3" align = "center">
	<input type="checkbox" name="idStore" value="store" <c:if test="${isId != null }">checked</c:if>/>
	
	아이디저장 | 	
	
	
	<input type="checkbox" name="autologin" value="auto"/> 자동로그인</td></tr>
	
	
	
	
<tr>
	<td>아이디</td>
	<td><input type="text" name="userId" value="${isId }"/><span>${userFail }</span></td>
	<td rowspan="2">
		<input type="image" src="images/login64.png" width="60" alt="login"></td>	
</tr>
<tr>
	<td>비밀번호</td>
	<td><input type="password" name="userPw"/><span>${pwFail }</span></td>
</tr>

<tr>
	<td colspan="3" align = "center">
	<a href="idSearch.mem">아이디</a>/
	<a href="#">비밀번호찾기</a> |
	<a href="memAgree.mem">회원가입</a>
	</td>
</tr>

</table>
</form>
</c:if>
<!-- 로그인 되었을 때 -->
<c:if test="${!empty authInfo }">
	
	<c:if test="${authInfo.grade == 1 }">
		<!-- 일반회원 -->
		<a href="myPage.mem">마이페이지</a> 	
		<a href="noticeListMem.nt">공지사항</a>	
		<a href="goodsCartList.gd">장바구니</a>
		<a href="purchaseCon.gd">구매리스트</a>
		
	</c:if>
	
	<c:if test="${authInfo.grade != 1 }">
		<!-- 직원 -->
		<a href="empMyPage.em">마이페이지</a>
		<a href="goodsList.gd">상품등록</a>
		<a href="noticeList.nt">공지사항</a>	
	
		<!-- 관리자 -->
			<a href="empList.em">직원 리스트</a>
			<a href="memList.mem">회원 리스트</a>
			<a href="venta.vnt">판매현황</a>
			
	</c:if>
	<a href="logout.sm">로그아웃</a>
</c:if>
<hr/>
<!-- 상품 리스트 -->
 
<script>
	function goodsBuy(prodNum){
		if(${authInfo == null}){
			alert ("로그인이 되어있지 않습니다.");
			return false;
		} else {
			location.href='prodInfo.gd?prodNum='+prodNum;
		}
	}
</script>
<!-- 로그인이 되든 안되든 상품 리스트를 노출할 것, 대신 로그인이 되지 않았을 때는 상품 정보를 눌렀을 때 alert를 통해 로그인하도록 유도함  -->
<table align="center">
	<tr>
	<!-- c:forEach문은 jstl에서 사용하는 for문. 
	 items: 리스트의 객체(배열) var: 사용할 변수 varStatus: 반복변수  -->
	<c:forEach items="${lists }" var="dto" varStatus="cnt">
	
		<td><a href="javascript:goodsBuy('${dto.prodNum}')">
			
			<img width = "300" height="300" src="goods/upload/${dto.prodImage.split(',')[0] }"><br/>
			${dto.prodName }<br/>
			가격: <fmt:formatNumber value="${dto.prodPrice }" type="currency"/>
			
			</a></td>
		
		<c:if test="${cnt.count % 3 == 0 }">
		<!-- cnt.index는 0부터 출력, count는 1부터 출력한다. 3으로 나눈 나머지가 0인 경우를 출력한다는 말은 한 줄에 3개씩 보이겠다는 말 
		나머지가 0이면 출력하고, 아니면 X -->
		</tr>
		<tr> 
		<!-- tr2개 생긴 이유는 3개 이후에 다음줄로 넘기기위함 -->
		</c:if>
	</c:forEach>
	</tr>
</table>
</body>
</html>