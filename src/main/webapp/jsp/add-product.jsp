<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<%@include file="css.jsp" %>
</head>
<body>
<%@include file="header.jsp" %>
<div class="main" >
<c:choose>
	<c:when test="${empty product.name }">
		<h2>Add product</h2>
	</c:when>
	<c:otherwise>
		<h2>Edit Product</h2>
	</c:otherwise>
</c:choose>
<form:form modelAttribute="product">
	<fieldset>
		<label>Product Name</label>
		<form:input path="name" class="textbox"/>
	</fieldset>
	<fieldset>
		<label>Category</label>
		<form:input path="category" class="textbox"/>
	</fieldset>
	<fieldset>
		<label>Condition</label>
		<form:input path="condition" class="textbox"/>
	</fieldset>
	<fieldset>
		<label>Price</label>
		<form:input path="price" class="textbox"/>
	</fieldset>
	<div style="padding-left:10em;">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<button class="btn btn-primary">Submit</button>
	</div>
</form:form>
</div>
</body>
</html>