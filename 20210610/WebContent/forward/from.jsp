<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.Calendar" %>

<%
	String code = request.getParameter("code");
	String viewPage = null; // 이동하기 위한 페이지 값을 저장하기 위한 변수
	
	if(code.equals("1")){
		viewPage = "1.jsp";
	}else if(code.equals("2")){
		viewPage = "2.jsp";
	}else if(code.equals("3")){
		viewPage = "3.jsp";
	}
%>

<%
	request.setCharacterEncoding("utf-8");
	Calendar cal = Calendar.getInstance();
	request.setAttribute("cal", cal); // obj타입으로 저장됨
	request.setAttribute("age", 10);
	request.setAttribute("addr", "서울");
	session.setAttribute("sesval", "sessionValue");
	int i=10;
	// from페이지의 변수는 forward페이지에 가져올 수 없음

%>

from 페이지입니다. 
<jsp:forward page="<%= viewPage %>">
	<jsp:param value="안녕하세요." name="p2"/>
	<jsp:param value="이숭무." name="p3"/>
</jsp:forward>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
</body>
</html>