<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>       
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객별 판매 현황</title>
   <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
   <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawVisualization);
   
      function drawVisualization() { 
    		var data = google.visualization.arrayToDataTable(${googleList});
			var options = {
					title : '고객별 판매 현황 ',
					vAxis: {title: '금액 및 수량'},
					hAxis: {title: '사용자'}, 
					seriesType: 'bars',
					series: {5: {type: 'line'}}
				};        
         var chart = new google.visualization.ComboChart(document.getElementById('chart_div'));
         chart.draw(data, options);
      }
   </script>
</head>
<body>
<div id="chart_div" style="width:900px; height: 500px;"></div>
<h4>고객별 판매 현황</h4><br />
<table border="1" width="900">

<tr><th>이름/아이디</th><th>총 구매금액</th><th>횟수</th><th>평균 금액</th></tr>

<c:forEach items="${list }" var="dto">
<tr>
<td align="center">${dto.memName }/${dto.memId }</td>
<td align="center"> <fmt:formatNumber value="${dto.sumPrice }" type="currency"/></td>
<td align="center">${dto.count }</td>
<td align="center"><fmt:formatNumber value="${dto.avg }" type="currency"/></td>
</tr>
</c:forEach>
</table>
</body>
</html>