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
	<h2>Shopping Cart</h2>
	<p>${products!=null }</p>
	<table>
		<thead>
			<tr>
				<td>Product</td>
				<td>Price</td>
			</tr>
		</thead>
		<tbody>
			<% boolean even=false; %>
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
	<form action="./confirm-order" method="POST">
		<fieldset>
			<label>Credit Card</label>
			<input class="textbox" type="text" name="creditcard" style="width:40%;"/>
		</fieldset>
		<fieldset>
			<label>CCV</label>
			<input class="textbox" type="text" name="ccv" style="width:10%;"/>
		</fieldset>
		<fieldset>
			<label>Expiration Date</label>
			<input class="textbox" type="date" name="expiration" style="width:40%;"/>
		</fieldset>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<button class="btn btn-primary">Submit</button>
	</form>
</div>
</body>
</html>