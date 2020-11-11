<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Products</title>
<%@include file="css.jsp" %>
</head>
<body>
<%@include file="header.jsp" %>
<div class="main" >
	<fmt:setLocale value = "en_US"/>
	<h2>Product Inventory Page</h2>
	<form action="./product-list" method="GET">
		<select onChange="this.form.submit()">
			<c:forTokens items="5,10,20,50,100" delims="," var="size">
			<option value="<c:out value="${size}"/>"><c:out value="${size}"/></option>
			</c:forTokens>
		</select>
	</form>
	<table>
		<thead>
			<tr>
				<td></td>
				<td>Product Name</td>
				<td>Category</td>
				<td>Condition</td>
				<td>Price</td>
			</tr>
		</thead>
		<tbody>
			<% boolean even=false; %>
			<c:forEach var="product" items="${products}">
			
			<tr class="<%= even?"even":""%>" >
				<td ><img src="/f"/></td>
				<td><a href="http://localhost:8080/SpringWebwithSpringSecurity/temp/product/1">${product.name }</a></td>
				<td>${product.category }</td>
				<td>${product.condition }</td>
				<td><fmt:formatNumber value = "${product.price/100}" type = "currency"/></td>
				<% even=!even; %>
			</tr>
			</a>
			</c:forEach>
		</tbody>
	</table>
</div>
</body>
</html>