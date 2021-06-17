<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
</head>
<body>
공지사항 페이지입니다.<br/>
<table border = 1>
<tr><th>번호</th>
	<th>제목</th>
	<th>내용</th>
	<th>날짜</th>
	<th>공지종류</th>
	<th>첨부파일</th>
	<th>조회수</th>
	<th>글쓴이(사원)</th>
<c:forEach items="${lists }" var="dto">
<tr><td>${dto.noticeNo }</td>
	<td>${dto.noticeSub }</td>
	<td>${dto.noticeCon }</td>
	<td>${dto.noticeDate }</td>
	<td>${dto.noticeKind }</td>
	<td>${dto.noticeFile }</td>
	<td>${dto.noticeHits }</td>
	<td>${dto.employeeId }</td></tr>

</c:forEach>	
</table>
<a href="noticeRegist.nt">공지등록</a>
</body>
</html>