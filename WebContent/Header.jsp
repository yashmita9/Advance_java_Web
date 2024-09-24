<%@page import="In.com.Bean.Userbean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		Userbean user = (Userbean) request.getAttribute("user");
		if (user != null) {
	%>

	<h3>
		Hi..<%
		user.getFirstName();
	%>
	</h3>
	<%
		} else {
	%>
	<h3>Hi Guest</h3>
	<%
		}
	%>


</body>
</html>