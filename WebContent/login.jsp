<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%@ include file = "header.jsp"%>

<h1>${message}</h1>

	<form method = "post" action = "login">
		<table border="1">
		  <tr>
		    <td>Name:</td>
		    <td>
		      <input type = "text" name = "uname" size = "20">
		    </td>
		  </tr>
		  <tr>
		    <td>Password:</td>
		    <td>
		      <input type = "password" name = "pass" size = "20">
		    </td>
		  </tr>
		</table>
		<p><input type = "submit" value = "submit" name = "b1"></p>
	</form>

<%@ include file = "footer.jsp"%>

</body>
</html>