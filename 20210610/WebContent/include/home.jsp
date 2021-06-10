<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.Calendar" %>
홈
<% 
	Calendar cal = (Calendar)request.getAttribute("cal");
	Integer i = (Integer)request.getAttribute("i");
%><br/>


<%= cal.get(Calendar.HOUR) %> 시 
<%= cal.get(Calendar.MINUTE) %>분   <br/>
<%=i %><br/>
<%= request.getParameter("p2")%>