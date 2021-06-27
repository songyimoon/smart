<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>

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

</style>
</head>
<body>
<h1 align="center">공지사항</h1><br/>
<p align="center" bgcolor=#e0e0eb><a href="noticeRegist.nt"> > 공지등록 < </a></p>
<p align="center">글 번호를 클릭해서 수정해주시기 바랍니다.</p>
<p align="center">글 내용은 제목을 클릭해서 확인해주세요.</p>
<table align="center">
<tr bgcolor=#e0e0eb><th>번호</th>
	<th>공지종류</th>
	<th>제목</th>
	<th>날짜</th>

	<th>첨부파일</th>
	<th>조회수</th>
	<th>글쓴이(사원)</th>
<c:forEach items="${lists }" var="dto">
<tr><td align="center"><a href="noticeDetail.nt?noticeNo=${dto.noticeNo }">${dto.noticeNo }</a></td>
	<td align="center">${dto.noticeKind }</td>
	<td><a href="noticeView.nt?noticeNo=${dto.noticeNo }">${dto.noticeSub }</a></td>
	<td align="center">${dto.noticeDate }</td>

	<td>${dto.noticeFile }</td>
	<td align="center">${dto.noticeHits }</td>
	<td align="center">${dto.employeeId }</td></tr>
</c:forEach>	
</table>
<br/>
<p align="center">
<input type="button" value="뒤로가기" onclick="javascript:history.back();"/>
</p>
</body>
</html>