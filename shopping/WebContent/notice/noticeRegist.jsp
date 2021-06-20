<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지 등록 페이지</title>
</head>
<body>
<form action="noticeJoin.nt" method="post" name="frm" enctype="multipart/form-data">
<table border = 1>
	<tr><th>번호</th>
	<td><input type="text" name="noticeNo" value="${noticeNo }"/></td></tr>
	<tr><th>제목</th>
	<td><input type="text" name="noticeSub"/></td></tr>
	<tr><th>내용</th>
	<td><textarea rows="10" cols="50" name="noticeCon"></textarea></td></tr>
	<tr><th>날짜</th>
	<td><input type="date" name="noticeDate" /></td></tr>
	
	
	<tr><th>공지종류</th>
	<td>
	<select name="noticeKind">
		<option value="not">공지</option>
		<option value="deliv">배송</option>	
	</select></td></tr>
	
	
	
	<tr><th>첨부파일</th>
	<td><input type="file" name="noticeFile"/></td></tr>
	<tr><th>조회수</th>
	<td><input type="text" name="noticeHits"/></td></tr>
	<tr><th>글쓴이(사원)</th>
	<td><input type="text" name="employeeId" value="${empId }"/></td></tr>
	<tr><th colspan="2">
		<input type="submit" value="등록"/>
		<input type="reset" value="취소" onclick="javascript:history.back();"/>
		<input type="button" value="홈으로" onclick="javascript:location.href='main.sm'"/>
</table>
</form>
</body>
</html>