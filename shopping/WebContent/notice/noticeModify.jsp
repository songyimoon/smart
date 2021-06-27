<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 수정</title>
</head>
<body>
<form action="noticeModify.nt" method="post" name="frm" >
<input type="hidden" name="noticeNo" value="${dto.noticeNo }">


<table border = 1>
	<tr><th>번호</th>
	<td>${dto.noticeNo }</td></tr>
	<tr><th>제목</th>
	<td><input type="text" name="noticeSub" value="${dto.noticeSub }"/></td></tr>
	<tr><th>내용</th>
	<td><textarea rows="10" cols="50" name="noticeCon" > ${dto.noticeCon }</textarea></td></tr>
	<tr><th>날짜</th>
	<td>${dto.noticeDate } </td></tr>
	<tr><th>공지종류</th>
	<td>
		<select name="noticeKind">
		<option value="not">공지</option>
		<option value="deliv">배송</option>	
	</select></td></tr>
	
	<tr><th>첨부파일</th>
	<td><input type="file" name="noticeFile" value="${dto.noticeFile }"/></td></tr>
	<tr><th>조회수</th>
	<td><input type="text" name="noticeHits" value="${dto.noticeHits }"/></td></tr>
	<tr><th>글쓴이(사원)</th>
	<td>${dto.employeeId }</td></tr>
	
	<tr><th colspan="2">
		<input type="submit" value="공지수정"/>
		<input type="button" value="취소" onclick="javascript:history.back();"/>
		<input type="button" value="삭제" onclick="javascript:location.href='noticeDel.nt?noticeNo=${dto.noticeNo}'"/>
		<input type="button" value="홈으로" onclick="javascript:location.href='main.sm'"/>

</table>
</form>

</body>
</html>