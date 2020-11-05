<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WELCOME</title>
<style>
body{
	background-color:blue;
	font-family:arial;
}
div.main{
	width:80%;
	margin:auto;
	padding: 2em;
	background-color:white;
	border-radius:2em;
}
label{
	margin:1em;
	display:inline-block;
	width:10%;
}
.textbox{
	width:30%;
	padding:0.5em;
	border-radius:.5em;
}

.btn:hover{
  background-color: #1e6e21;
}
.btn{
	padding:.5em 1em .5em;
	border-radius:.5em;
	font-weight:bold;
	color:white;
}
.btn.btn-primary{
	background-color:green;
	color:white;
	border-color:green;
}
fieldset{
	border:0;
}
.card{
	display:inline-block;
	border:  1px;
	border-color:black;
	border-radius:1em;
	border-style:solid;
	width:14%;
	height:10em;
	margin:0.55%;
	padding:.4%;
	transition: .5s;
}
.card:hover{
  background-color: #ddd;
  transition: .5s;
}
.item-title{
	margin:0 1em 0 1em;
	font-weight:bold;
}
.thumbnail{
	width:75%;
	height:75%;
	margin:0 1em 0 1em;
}
.thumbnail img{
	display: block;
	max-width:100%;
	max-height:100%;
	width: auto;
	height: auto;
}
</style>
</head>
<body>
<div class="main">
<div>
Welcome! Your login was successful...!
</div>
<a href="<c:url value="/logout" />">Logout</a><br/>

<div>
<h2>Some things to look at</h2>
<c:forEach begin="0" end="13" step="1">
<div class="card">
	<div class="item-title">Item name</div>
	<div class="thumbnail"><img src="f" /></div>
	<div class="item-body">Item description</div>
</div>
</c:forEach>

</div>
</div>
</body>
</html>