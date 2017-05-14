<%@ page language="java" contentType="text/html"%>
<%@ page import="com.svecw.oes.dao.*"%>
<%@ page import="com.svecw.oes.dto.*"%>
<%@page import="java.util.*"%>
<%!UserDAO userDAO = new UserDAO();%>
<%!TestDAO testDAO = new TestDAO();%>
<br>
<h3>${successMessage}${errorMessage}</h3>
<form action="HomeController" method="post">

<select id="test" name="test">"${tests}"
<option value="${test.testId}">tests</option>
		<c:forEach var="test" items="${tests}">
			<option value="${test.testId}">${test.testId}</option>
			</c:forEach>
		</select>
	<table>
		<c:foreach var="user" items="${users}">
		<tr>
			<td><input type="checkbox" name="users" id="users"
				value="${user.userId}"></td>
				<td>${user.userId}</td>
				<td>${user.name}</td>
				<td>${user.phoneNumber}</td>
				<td>${user.emailId}</td>
			</tr>
		</c:foreach>
	</table>
	<input type="submit" name="submit" id="submit" value="enroll" /><br>
	<input type="hidden" name="action" value="enroll">
</form>
