<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>직원탈퇴</title>

<script type="text/javascript">
	function outConfirm(){
		if(confirm("정말 탈퇴하시겠습니까?")){
			document.frm.submit();
		}else {
			return false;
		}
	}
</script>
</head>
<body>

<form action="empOutOk.em" method="post" onsubmit="return outConfirm()">
 
비밀번호 : <input type="password" name="empPw" /><br/>
<span>${pwFail2 }</span><br/>
<input type="submit" value="탈퇴" />
</form>
</body>
</html>