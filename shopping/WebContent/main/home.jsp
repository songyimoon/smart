<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 로그인 안되었을 때 -->
<table border = 1>
<tr>
	<td colspan="3">아이디저장 | 자동로그인</td>
</tr>

<tr>
	<td>아이디</td>
	<td><input type="text" name="userId"/></td>
	<td rowspan="2">
		<input type="image" src="" alt="login"></td>	
</tr>
<tr>
	<td>비밀번호</td>
	<td><input type="password" name="userPw"/></td>
</tr>

<tr>
	<td colspan="3">
	<a href="#">아이디</a>|
	<a href="#">비밀번호찾기</a>|
	<a href="memAgree.mem">회원가입</a>
	</td>
</tr>

</table>


<!-- 로그인 되었을 때 -->
	<!-- 일반회원 -->
	
	<!-- 직원 -->
	
	<!-- 관리자 -->
	

	<a href="empList.em">직원 리스트</a>
</body>
</html>