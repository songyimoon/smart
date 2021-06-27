<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원리스트</title>
<style type="text/css">
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap');

*{
  color: black;
}
body {
  font-family: 'Noto Sans KR', sans-serif;
}
table{
  font-size: 15px/1;
  width: 1200px;
  border: 1px solid;
  border-spacing: 8px;

</style>
</head>
<body>
<h1 align="center">회원리스트</h1><br/>


<table align="center">
<tr bgcolor=#e0e0eb>
	<th>아이디</th>
	<th>이름</th>
	<th>연락처</th>
	<th>이메일</th>
	<th>주소</th></tr>
	<c:forEach items="${lists }" var="dto">
<tr><td>
	<a href="memInfo.mem?memId=${dto.memId }">${dto.memId }</a></td>
	<td>${dto.memName }</td>
	<td>${dto.memPhone }</td>
	<td>${dto.memEmail }</td>
	<td>${dto.memAddress } ${dto.detailadd }</td></tr>
	</c:forEach>
</table>
</body>
</html>