<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 작성</title>
<style type="text/css">
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap');

body {
  font-family: 'Noto Sans KR', sans-serif;
}
table{
  font-size: 15px/1;
  width: 1000px;
  border: 1px solid;
  border-spacing: 8px;
}
</style>
</head>
<body>
<form action="reviewWrite.gd" method="post" enctype="multipart/form-data">
<input type="hidden" name="prodNum" value="${prodNum }"/>
<input type="hidden" name="purchaseNum" value="${purchaseNum }"/>
<table>
	<tr><td>리뷰작성</td>
		<td><textarea rows="5" cols="30" name="reviewContent"></textarea></td></tr>
	<tr><td>파일</td>
		<td><input type="file" name="reviewImg" /></td></tr>
	<tr><td colspan="2">
		<input type="submit" value="등록"/></td></tr>
</table>
</form>
</body>
</html>