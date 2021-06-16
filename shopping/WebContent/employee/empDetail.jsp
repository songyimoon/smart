<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>직원 상세 정보</title>
</head>
<body>
직원 상세 정보 <br/><br/>

사원번호 : ${emp.employeeId }<br />
사원아이디 :  ${emp.empUserid }<br/>
이름 : ${emp.empName }<br />
입사일 : ${emp.hireDate }<br />
직무 : ${emp.jobId }<br />
연락처 : ${emp.phNumber }<br />
사무실 번호 : ${emp.officeNumber }<br />
이메일 : ${emp.empEmail }<br />
주소 : ${emp.empAddress }<br />

<br/>

<a href="empSujung.em">수정</a>

</body>
</html>