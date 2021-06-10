<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     
    
<a href="include.jsp?page=home">홈</a>     
<a href="include.jsp?page=movie">영화</a>    
<a href="include.jsp?page=music">음악</a>  
<a href="include.jsp?page=picture">그림</a>   
<a href="include.jsp?page=exercise">운동</a> 

<%= cal.get(Calendar.HOUR) %> 시 <%= cal.get(Calendar.MINUTE) %>분    
    
<!-- 에러가 나더라도, 불러가는 include에서 import해놨으므로 에러가 안난다.
	저거를 홈에 쓰면 에러! -->