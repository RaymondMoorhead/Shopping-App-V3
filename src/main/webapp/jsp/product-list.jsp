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
	<h2>Product Inventory Page</h2>
	<form action="" method="GET">
		<select>
			<c:forTokens items="5,10,20,50,100" delims="," var="size">
			<option value="<c:out value="${size}"/>"><c:out value="${size}"/></option>
			</c:forTokens>
		</select>
	</form>
	<p>${fn:length(products) }</p>
	<table modelAttribute="products">
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
			<c:forEach var="product" items="${products}">
			<tr>
				<td ><img src="/f"/></td>
				<td>${product.name }</td>
				<td>${product.category }</td>
				<td>${product.condtion }</td>
				<td>${product.price }</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</body>
</html>