<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<style>
body{
	background-color:blue;
	font-family:arial;
}
div.main{
	width:80%;
	margin:auto;
	padding: 2em;
	background-color:white;
	border-radius:2em;
}
label{
	margin:1em;
	display:inline-block;
	width:10%;
}
.textbox{
	width:30%;
	padding:0.5em;
	border-radius:.5em;
}

.btn:hover{
  background-color: #1e6e21;
}
.btn{
	padding:.5em 1em .5em;
	border-radius:.5em;
	font-weight:bold;
	color:white;
}
.btn.btn-primary{
	background-color:green;
	color:white;
	border-color:green;
}
fieldset{
	border:0;
}
</style>
</head>
<body>
<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
      <font color="red">
        Your login attempt was not successful due to <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
      </font>
</c:if>
	<div class="main" >
		<h1>Login</h1>
		<form name="loginForm" action="authenticateUser" method="post">
			<fieldset>
				<label for="username">User-name</label><input class="textbox" type="text" name="username" /><br /> 
			</fieldset>
			<fieldset>
				<label for="password">Password</label><input class="textbox" type="password" name="password" /> <br/>
			</fieldset>
			<div style="padding-left:10em;">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<button class="btn btn-primary">Submit</button>
			</div>
		</form>
	</div>
</body>
</html>