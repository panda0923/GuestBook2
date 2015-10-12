<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String no = request.getParameter("no");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GUEST BOOK</title>
</head>
<body>
	<form method="post" action="/GuestBook2/gb">
	<input type='hidden' name="a" value="delete">
	<input type='hidden' name="no" value="<%=no%>">
	<table>
		<tr>
			<td>비밀번호</td>
			
			<td><input type="password" name="password"></td>
			<td><input type="submit" value="확인"></td>
			<td><a href="/GuestBook2/gb"></a>메인으로 돌아가기</td>
		</tr>
	</table>
	</form>
</body>
</html>