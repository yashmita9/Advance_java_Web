<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<hr>
	<form action="LoginViewCtl" method="Post">
		<div align="center">
			<%
				String mesg = (String) request.getAttribute("mesg");
				String uri = (String) request.getAttribute("uri");
			%>
			<h1>Login Page</h1>
			<br>
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
					<td><input type="password" name="passward"></td>

				</tr>

				<tr>
					<th></th>
					<td><input type="submit" value="signin" name="opreation">
						<input type="submit" value="SignUp" name="opreation"></td>

				</tr>
			</table>
		</div>
		<input type="text" name="uri" value="<%=uri%>">
	</form>

</body>
</html>