<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WELCOME</title>
<%@include file="css.jsp" %>
</head>
<body>
<%@include file="header.jsp" %>
<div class="main">
<div>
Welcome! Your login was successful...!
</div>
<a href="<c:url value="/logout" />">Logout</a><br/>

<div>
<h2>Some things to look at</h2>
<c:forEach begin="0" end="13" step="1" var="product" items="${products}">
<div class="card">
	<div class="item-title"><a href="./product-detail/${product.code }">${product.name}</a></div>
	<div class="thumbnail"><img src="f" /></div>
	<div class="item-body">Item description</div>
</div>
</c:forEach>

</div>
</div>
</body>
</html>