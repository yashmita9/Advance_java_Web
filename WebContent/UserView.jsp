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
	<form action="UserCtl.do" method="Post">
		<div align="center">
			<%
				String mesg = (String) request.getAttribute("mesg");
				Userbean bean = (Userbean) request.getAttribute("bean");
			%>
			<%
				if (bean != null && bean.getId() > 0) {
			%>
			<h1>Update User</h1>
			<%
				} else {
			%>
			<h1>Add User</h1>
			<%
				}
			%>

			<%
				if (mesg != null) {
			%>
			<font color="green"><%=mesg%></font>
			<%
				}
			%>
			<table>
				<tr>

					<td><input type="hidden" name="id"
						value="<%=(bean != null && bean.getId() > 0) ? bean.getId() : ""%>"></td>
				</tr>
				<tr>
					<th>First Name :</th>
					<td><input type="text" name="firstName"
						value="<%=(bean != null && bean.getFirstName() != null) ? bean.getFirstName() : ""%>"></td>
				</tr>
				<tr>
					<th>Last Name :</th>
					<td><input type="text" name="lastName"
						value="<%=(bean != null && bean.getLastName() != null) ? bean.getLastName() : ""%>"></td>
				</tr>
				<tr>
					<th>Login Id :</th>
					<td><input type="text" name="loginId"
						value="<%=(bean != null && bean.getLoginId() != null) ? bean.getLoginId() : ""%>"></td>
				</tr>
				<tr>
					<th>Passward :</th>
					<td><input type="text" name="passward"
						value="<%=(bean != null && bean.getPassward() != null) ? bean.getPassward() : ""%>"></td>
				</tr>
				<tr>
					<th>Dob :</th>
					<td><input type="date" name="dob" style="width: 98%"
						value="<%=(bean != null && bean.getDob() != null) ? bean.getDob() : ""%>"></td>
				</tr>
				<tr>
					<th>Address :</th>
					<td><input type="text" name="address"
						value="<%=(bean != null && bean.getAddress() != null) ? bean.getAddress() : ""%>"></td>
				</tr>
				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value="<%=bean != null && bean.getId() > 0 ? "Update" : "Save"%>"></td>

				</tr>

			</table>
		</div>
	</form>
</body>
</html>