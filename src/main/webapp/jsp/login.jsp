<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<%@include file="css.jsp" %>
</head>
<body>
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
		Need an account? <a href="./register">Register</a>
		<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
		<div class="error_message"><font color="red"> 
	      	Your login attempt was not successful due to <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
		</font></div>
		</c:if>
	</div>
</body>
</html>