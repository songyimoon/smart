<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${dto.noticeSub } </title>

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
  width: 1200px;
  border: 1px solid;
  border-spacing: 8px;
}

</style>
</head>
<body>
<form action="noticeModify.nt" method="post" name="frm">
<input type="hidden" name="noticeNo" value="${dto.noticeNo }">


<table border = 1 align="center">
	<tr>

	<th bgcolor=#e0e0eb>공지종류</th>
	<td>${dto.noticeKind }</td>
	<th bgcolor=#e0e0eb>조회수</th>
	<td>${dto.noticeHits }</td>
	</tr>
	
	
	<tr>
	<th bgcolor=#e0e0eb>날짜</th>
	<td>${dto.noticeDate } </td>
	<th bgcolor=#e0e0eb>글쓴이(사원)</th>
	<td>${dto.employeeId }</td>
	</tr>
	
	<tr><th bgcolor=#e0e0eb>제목</th>
	<td colspan="4">${dto.noticeSub }</td></tr>
	
	<tr height="600"><th bgcolor=#e0e0eb>내용</th>
	<td colspan="4">${dto.noticeCon }</td></tr>
	<tr></tr>

	
	<tr><th bgcolor=#e0e0eb>첨부파일</th>
	<td colspan="4"> 
	
	<c:if test="${!empty dto.noticeFile }">
	<img width = "250" height="280" src="notice/upload/${dto.noticeFile }">
	</c:if>
	
	</td></tr>

	
	<tr><th colspan="4" bgcolor=#e0e0eb>
		<input type="button" value="홈으로" onclick="javascript:location.href='main.sm'"/>

</table>
</form>

</body>
</html>