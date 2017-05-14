<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>

<script>
	function validation() {

		var password = document.getElementById("password");
		var userid = document.getElementById("userId");

		if (password.value == "" || emailid.value == "") {
			msg.innerHTML = "<font color = red>please fill all the details</font>"
			return false;
		}
		return true;
	}
</script>

</head>
<body>
	<form  method="post" onsubmit="return validation()" action="HomeController" >
		<h3>${successMessage}${errorMessage}</h3>
		<table cellpadding="4" cellspacing="3" border="0">
			<tr>
				<td><b>UserId : <sup><font color="red"> * </font></sup>
				</b> <input type="text" name="userId" placeholder="userId" id ="userId"/></td>

			</tr>
			<tr>
				<td><b>Password : <sup><font color="red"> * </font></sup></b> <input
					type="password" name="password" id = "password" placeholder="password" /></td>
			</tr>

			<tr>
				<td><input type="submit" value="Login"></td>
			</tr>
			<tr>
				<td><input type="hidden" name="action" value="userLogin"></td>
			</tr>
		</table>
	</form>
</body>