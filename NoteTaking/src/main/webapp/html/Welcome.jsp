<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
<link rel="stylesheet" type="text/css" href="css/mystyle.css">
<style type="text/css">
*{
	box-sizing: border-box;
}

body{
	text-align: center;
	display:flex;
	align-items: center;
	justify-content: center;
	height: 100vh;
}

button{
border: none;

}

label{

color: white;
}

</style>
</head>
<body>
<div id= "welcome">
<form method="post" action = "/AuthenticationSystem/proceed">
<label> Username: </label> <input type = "text" name = "userName">
<br>
<br>
<label> Password: </label> <input type = "password" name = "password">
<br>
<br>
<button type = "submit" name = "action"  value = "login">Login</button>
<br>
<br>
<button type = "submit" name = "action" value = "register">Register</button>
</form>
</div>
</body>
</html>