<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="UserRegistrationCti" method="Post">
		<div align="center">
			<%
				String mesg = (String) request.getAttribute("mesg");
			%>
			<h1>User Registration</h1>
			<%
				if (mesg != null) {
			%>
			<font color="green"><%=mesg%></font>
			<%
				}
			%>
			<table>
				<tr>
					<th>First Name :</th>
					<td><input type="text" name="firstName"></td>
				</tr>
				<tr>
					<th>Last Name :</th>
					<td><input type="text" name="lastName"></td>
				</tr>
				<tr>
					<th>Login Id :</th>
					<td><input type="text" name="loginId"></td>
				</tr>
				<tr>
					<th>Passward :</th>
					<td><input type="text" name="passward"></td>
				</tr>
				<tr>
					<th>Dob :</th>
					<td><input type="date" name="dob" style="width:98%"></td>
				</tr>
				<tr>
					<th>Address :</th>
					<td><input type="text" name="address"></td>
				</tr>
				<tr>
					<th></th>
					<td><input type="submit"></td>
				</tr>

			</table>
		</div>
	</form>
</body>
</html>