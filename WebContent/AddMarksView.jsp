<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title> 

</head>
<body>
<%@ include file="Header.jsp" %>
<hr>
<br>
	<form action="AddMarksCtl">
		<div align="center">
			<h1>Add Marks</h1>
			<table>
				<tr>
					<th align="left">Roll Number :</th>
					<td><input type="number" name="rollNum"></td>
				</tr>
				<tr>
					<th align="left">Full Name :</th>
					<td><input type="text" name="fullName"></td>
				</tr>
				<tr>
					<th align="left">Physics Marks :</th>
					<td><input type="number" name="physics"></td>
				</tr>
				<tr>
					<th align="left">Chemistry Marks :</th>
					<td><input type="number" name="chemistry"></td>
				</tr>
				<tr>
					<th align="left">Mathematic Marks :</th>
					<td><input type="number" name="chemistry"></td>
				</tr>
				<tr>

					<th><br></th>
					<td><input type="submit" name="operation" value="Save"></td>
				</tr>
			</table>

		</div>
	</form>
</body>
</html>