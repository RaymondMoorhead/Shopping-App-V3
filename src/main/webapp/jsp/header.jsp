<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.shoppingapp.entity.User" %>
<div class="header">
	<ul class="navbar">
		<li><h1 class="Title">Furniture Hub</h1></li>
		<li><a href="<c:url value="/welcome" />">Home</a></li>
		<li><a href="<c:url value="/product-list"/>">Products</a></li>
		<li><a href="<c:url value="/about"/>">About us</a></li>
		<li><a href="<c:url value="/contact"/>">Contact us</a></li>
	</ul>
	<% String username="user"; %>
	<ul class="navbar float-right">
		<li>Welcome: <%= username %></li>
		<li><a href="<c:url value="/logout"/>">Logout</a></li>
		<li><a href="<c:url value="/cart"/>">Cart</a></li>
		<c:if test="${user!=null && user.privilage == 'ADMIN' }">
		<li><a href="<c:url value="/admin/customer-management"/>">Admin</a></li>
		</c:if>
	</ul>
</div>