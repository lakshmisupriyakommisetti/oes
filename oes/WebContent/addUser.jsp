<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add User</title>
</head>
<body>

<h3>${successMessage}${errorMessage}</h3>
	<form action="HomeController" method="POST">
		<center>

			<table cellpadding="4" cellspacing="3" border="0">
				<tr>
					<td><b> Name : </b> <input type="text" name="name"
						placeholder="name" /></td>
				</tr>
				<tr>
					<td><b> UserId : </b> <input type="text" name="userId"
						placeholder="userId" /></td>
				</tr>
				<tr>
					<td><b>Password : <sup><font color="red"> * </font></sup></b>
						<input type="password" name="password" placeholder="password" /></td>
				</tr>
				<tr>
					<td><b>MobileNumber : </b> <input type="text" name="mobileNo"
						placeholder="mobileNumber" /></td>
				</tr>
				<tr>
					<td><b>EmailId : </b> <input type="text" name="emailId"
						placeholder="emailId" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Add" /></td>

				</tr>
				<tr>
					<td><input type="hidden" name="action" value="user"></td>
				</tr>
			</table>
		</center>
	</form>


</body>
</html>