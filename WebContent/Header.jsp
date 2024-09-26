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
		Userbean user = (Userbean) session.getAttribute("user");
		if (user != null) {
	%>

	<h3>
		Hi..<%=user.getFirstName()%>
	</h3>
	<a href="UserCti"><b>Add user</b></a>
	<b>|</b>
	<a href=""><b>User List</b></a>
	<b>|</b>
	<a href="LoginViewCtl?opreation=logout"><b>Logout</b></a>
	
	<%
		} else {
	%>
	<h3>Hi Guest</h3>
	<a href="WelcomeCtl"><b>Welcome</b></a>
	<a href="LoginViewCtl"><b>Login</b></a>
	<%
		}
	%>


</body>
</html>