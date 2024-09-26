<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
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
		List list = (List) request.getAttribute("list");
		int pageNo = (int) request.getAttribute("pageNo");
	%>
	<%@ include file="Header.jsp"%>


	<hr>
	<form action="" method="post">
		<div align="center">
			<h1>User List</h1>
		</div>
		<table>
			<th>First Name</th>
			<td><input type="text" name="firstName"> <input
				type="submit" name="operation" value="search"></td>
		</table>

		<br>
		<table align="center" style="width: 100%" border="1">
			<tr>
				<th>Id</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Login Id</th>
				<th>Password</th>
				<th>Date Of Birth</th>
				<th>Adrees</th>
			</tr>
			<tr>
				<%
					Iterator it = list.iterator();
					while (it.hasNext()) {
						Userbean bean = (Userbean) it.next();
				%>
			
			<tr>
				<td align="center"><%=bean.getId()%></td>
				<td align="center"><%=bean.getFirstName()%></td>
				<td align="center"><%=bean.getLastName()%></td>
				<td align="center"><%=bean.getLoginId()%></td>
				<td align="center"><%=bean.getPassward()%></td>
				<td align="center"><%=bean.getDob()%></td>
				<td align="center"><%=bean.getAddress()%></td>

			</tr>
			<%
				}
			%>
			</tr>
		</table>
		<table style="width: 100%">
			<tr>
				<td style="width: 30%"><input type="submit" name="operation"
					value="previous" <%=(pageNo == 1) ? "disabled" : ""%>></td>
				<td style="text-align: right;"><input type="submit"
					name="operation" value="next" <%=(list.size() == 0) ? "disabled" : ""%>></td>
			</tr>
		</table>
		<input type="hidden" name="pageNo" value="<%=pageNo%>">
	</form>
</body>
</html>