<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Management</title>
</head>
<body>
<h1>Students Data</h1>
<!-- 注明绑定对象为student -->
<form:form action="student.do" method="POST" commandName="student">
	<table>
	<!-- 使用form:input 与表单对象的值绑定一起 -->
		<tr>
			<td>Student ID</td>
			<td><form:input path="studentId"/></td>
		</tr>
		<tr>
			<td>First name</td>
			<td><form:input path="firstname"/></td>
		</tr>
		<tr>
			<td>Last name</td>
			<td><form:input path="lastname"/></td>
		</tr>
		<tr>
			<td>Year level</td>
			<td><form:input path="yearLevel"/></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" name="action" value="Add" />
				<input type="submit" name="action" value="Edit" />
				<input type="submit" name="action" value="Delete" />
				<input type="submit" name="action" value="Search" />
			</td>
		</tr>
		
	</table>
</form:form>
<br>
<table border="1">
	<th>Student ID</th>
	<th>First name</th>
	<th>Last name</th>
	<th>Year level</th>
	<c:forEach items="${studentList}" var="student">
		<tr>
			<td>${student.studentId}</td>
			<td>${student.firstname}</td>
			<td>${student.lastname}</td>
			<td>${student.yearLevel}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>