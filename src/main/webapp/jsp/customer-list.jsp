<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Management</title>
<%@include file="css.jsp" %>
</head>
<body>
<%@include file="header.jsp" %>
<div class="main" >
	<h2>Product Inventory Page</h2>
	<form action="./customer-management" method="GET">
		<label>Page Size</label>
		<select onChange="this.form.submit()" name="pageSize">
			<c:forTokens items="5,10,20,50,100" delims="," var="size">
				<option value="${size}"  ${ size==pageSize?"selected":""} ><c:out value="${size}"/></option>
			</c:forTokens>
		</select>
	</form>
	<table>
		<thead>
			<tr>
				<td>Name</td>
				<td>Email</td>
				<td>Phone</td>
				<td>User-name</td>
				<td>Priviledge</td>
				<td>Enabled</td>
			</tr>
		</thead>
		<tbody>
			<% boolean even=false; %>
			<c:forEach var="cust" items="${customers}">
			<tr <%= even?"class='even'":"" %> >
				<td>${cust.name }</td>
				<td>${cust.email }</td>
				<td>${cust.phone }</td>
				<td>${cust.username }</td>
				<td>${cust.privilage }</td>
				<td>${cust.enabled }</td>
				<td><button>${cust.enabled?"disable":"enable" }</button>
			</tr>
			<% even=!even; %>
			</c:forEach>
		</tbody>
	</table>
	<a href="./customer-management?pageSize=${pageSize }&pageNum=${pageNum-1 }">Prev</a>
	<a href="./customer-management?pageSize=${pageSize }&pageNum=${pageNum+1 }">Next</a>
</div>
</body>
</html>