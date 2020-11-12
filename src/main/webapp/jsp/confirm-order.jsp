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
<title>Cart</title>
<%@include file="css.jsp" %>
</head>
<body>
<%@include file="header.jsp" %>
<div class="main" >
	<h2>Order Submitted</h2>
	<h3>Your order has been processed</h3>
	<table>
		<tbody>
			<tr>
				<td class="bold">Name</td>
				<td>Church of 8 Wheels</td>
			</tr>
			<tr class="even">
				<td class="bold">Billing Address:</td>
				<td>554 Filmore St, San Franciso, CA</td>
			</tr>
			<tr>
				<td class="bold">Product</td>
				<td class="bold">Price</td>
			</tr>
			<% boolean even=true; %>
			<c:forEach var="product" items="${products}">
			
			<tr class="<%= even?"even":""%>" >
				<td><a href="http://localhost:8080/SpringWebwithSpringSecurity/temp/product/1">${product.name }</a></td>
				<td><fmt:formatNumber value="${product.price/100 }" type = "currency"/></td>
				<% even=!even; %>
			</tr>
			</a>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td><span class="bold">Subtotal:</span></td>
				<td><fmt:formatNumber value="${products.stream().map(p->p.price).sum()/100 }" type = "currency"/></td>
			</tr>
			<tr>
				<td><span class="bold">Total:</span></td>
				<td><fmt:formatNumber value="${products.stream().map(p->p.price).sum()*.0108 }" type = "currency"/></td>
			</tr>
		</tfoot>
	</table>
</div>
</body>
</html>