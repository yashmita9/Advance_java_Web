<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ include file = "Header.jsp" %>
	<form action="LoginViewCtl" method="Post">
		<div align="center">
			<%
				String mesg = (String) request.getAttribute("mesg");
			%>
			<h1>Login Page</h1>
			<%
				if (mesg != null) {
			%>
			<font color="red"><%=mesg%></font>
			<%
				}
			%>
			<table>

				<tr>
					<th>Login Id :</th>
					<td><input type="text" name="loginId"></td>
				</tr>

				<tr>
					<th>Passward :</th>
					<td><input type="text" name="passward"></td>
					
				</tr>

				<tr>
					<th></th>
					<td><input type="submit" value="signin"></td>
					
				</tr>
			</table>
		</div>
	</form>

</body>
</html>