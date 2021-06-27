<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>직원 리스트</title>
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

<h1 align="center">직원 리스트</h1><br/>

	<c:if test="${!empty empList }">
	<table align="center">
		<tr bgcolor=#e0e0eb><th>사원번호</th>
			<th>이름</th>
			<th>직무</th>
			<th>입사일</th>
			<th>사무실번호</th></tr>
	<c:forEach items="${empList }" var="dto">
		<tr><td><a href="empInfo.em?empId=${dto.employeeId }">${dto.employeeId}</a></td>
			<td>${dto.empName}</td>
			<td>${dto.jobId}</td>
			<td>${dto.hireDate}</td>
			<td>${dto.officeNumber}</td></tr>
	</c:forEach>
	</table>
	</c:if>
	<c:if test="${empty empList }">
	등록된 직원이 없습니다.
	</c:if>
	<p align="center">
	<a href="empRegest.em">직원등록</a>
	</p>
</body>
</html>