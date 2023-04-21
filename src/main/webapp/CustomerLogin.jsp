<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css " href="css/cusLogin.css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%if(request.getAttribute("validate") != null){
	boolean value = (Boolean)request.getAttribute("validate"); 
	if(value == false){%>
		<script type="text/javascript">
			alert("Username or password is incorrect");
		</script>
	<%} }%>
	<div class="container">
		<form method="POST" action="Login" class="form">
			<center><img src="images/logo.png"></center>
			<h2>Login</h2>
			<input type="text" name="username" required placeholder="Username">
			<input type="password" name="pw" required placeholder="Password"><br><br>
			<input type="submit" value="Login">
		</form>
		<div class="register">Not a member?<a href="cusRegister.jsp"> Register</a></div><br><br>
		<center><div><a href="adminLogin.jsp"><button class="admin-button" style="	width: 30%;background-color: #005A01;color: white;padding: 14px 20px;margin: 8px 0;border: none;border-radius: 4px;cursor: pointer;">Switch toAdmin</button></a></div></center>
	</div>
</body>
</html>