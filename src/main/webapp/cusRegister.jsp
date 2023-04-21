<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/cusReg.css">
</head>
<body>
	<div class="container">
	<center><img src="images/logo.png"></center>
	<form method="post" action="CusRegister" onsubmit="validatePw()" class="form">
	<h1>Register Form</h1><br>
		 <input type="text" name="name" required placeholder="Name"><br>
		 <input type="text" name="username" required placeholder="User Name"><br>
		 <input type="password" name="pw" required id="pw" placeholder="Password"><br>
		 <input type="password" name="confirmPw" required id="confirmPw" onkeyup=confirmPW() placeholder="Confirm Password"><br>
		<input type="submit" value="Sign-In" id="signIn">
	</form>
	</div>
	<script type="text/Javascript">
		function confirmPW(){
			var pw = document.getElementById("pw").value;
			var confirmPw = document.getElementById("confirmPw").value;
			if(pw == confirmPw){
				document.getElementById("signIn").disabled = false;
			}else
			{
				document.getElementById("signIn").disabled = true;		
			}
		}
		function validatePw(){
			var pw = document.getElementById("pw").value;
			var confirmPw = document.getElementById("confirmPw").value;
			if(pw == confirmPw){
				//show sumsort of message
			}else
			{
				alert("Passwords doesn't matched")		
			}
		}
	</script>
</body>
</html>