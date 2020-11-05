<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="header">
	<ul class="navbar">
		<li><h1 class="Title">Furniture Hub</h1></li>
		<li><a href="<c:url value="/" />">Home</a></li>
		<li><a href="<c:url value="/prducts"/>">Products</a></li>
		<li><a href="<c:url value="/about"/>">About us</a></li>
		<li><a href="<c:url value="/contact"/>">Contact us</a></li>
	</ul>
	<% String username="user"; %>
	<ul class="navbar float-right">
		<li>Welcome: <%= username %></li>
		<li><a href="<c:url value="/logout"/>">Logout</a></li>
		<li><a href="<c:url value="/cart"/>">Cart</a></li>
		<li><a href="<c:url value="/admin"/>">Admin</a></li>
	</ul>
</div>