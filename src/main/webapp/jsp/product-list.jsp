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
<%@include file="header.jsp" %>
<div class="main" >
	<h2>Product Inventory Page</h2>
	<form action="" method="GET">
		<% int[] values = {5,10,20,50,100}; %>
		<select onchange="this.form.submit()">
			<c:forEach var="i" begin="0" end="values.length">
				<option value="<c:out value="values[i]"/>"><c:out value="values[i]"/></option>
			</c:forEach> 
		</select>
	</form>
	<% String[] products = {"Black Lotus", "Superman issue 1", "The Inverted Jenny"}; %>
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
			<C:forEach var="i" begin="0" end="products.length">
			<tr>
				<td class="thumbnail"><img src="/f"/></td>
				<td><c:out value="products[i]"/></td>
				<td>Collector's Item</td>
				<td>More than you can afford</td>
			</tr>
			</C:forEach>
		</tbody>
	</table>
</div>
</body>
</html>