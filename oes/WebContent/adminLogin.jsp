<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


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

<form onsubmit="return validation()" action="HomeController" method="post">
		<table>

			<tr>
				<td><b>AdminId : <sup><font color="red"> * </font></sup>
				</b> <input type="text" name="adminId" placeholder="adminId" /></td>

			</tr>
			<tr>
				<td><b>Password : <sup><font color="red"> * </font></sup></b> <input
					type="password" name="password" placeholder="password" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Login"></td></tr>
					<tr>
					<td><input type="hidden" name="action"
						value="adminLogin"></td></tr>
						
	
						
		</table></form>


</body>
</html>