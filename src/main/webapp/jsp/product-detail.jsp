<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${product.name } - Furniture</title>
<%@include file="css.jsp" %>

</head>
<body>
<%@include file="header.jsp" %>
<div class="main" >
	<fmt:setLocale value = "en_US"/>
	<c:if test="${message!=null }">
		<p class="message">${message }</p>
	</c:if>
	<div class="flex-container">
		<div class="flex-child">
			<img src="/f" class="main-img" />
		</div>
		<div class="flex-child">
			<h3>${product.name }</h3>
			<p><span class="bold">Category:</span> ${product.category }</p>
			<p><span class="bold">Condition:</span> ${product.condition }</p>
			<p><span class="bold">Price:</span> <fmt:formatNumber value = "${product.price/100}" type = "currency"/></p>
			<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
			<form method="POST" action="../../temp/product/1">
			<a class="btn" href="/SpringWebwithSpringSecurity/temp/product-list/">Back</a>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<button class="btn btn-primary" >Add to Cart</button>
			</form>
	</div>
</div>
</body>
</html>