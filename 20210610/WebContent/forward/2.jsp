<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.Calendar" %>   

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%= request.getParameter("code") %><br/>
<%=	((Calendar)request.getAttribute("cal")).get(Calendar.YEAR) %><br/>
<%=	(Integer)request.getAttribute("age") %><br/>
<%= (String)request.getAttribute("addr") %><br/>

<a href="next.jsp">다음 페이지</a>


</body>
</html>