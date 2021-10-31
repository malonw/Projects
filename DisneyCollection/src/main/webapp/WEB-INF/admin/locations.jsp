<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Locations</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="/css/style.css">

</head>
<body>
<header><a href="/admin">Admin Home Page</a>
<a href="/dashboard">User Home Page</a>
</header>

<div class="container">
<h1>Create an Location</h1>
<hr>
	<div class="form-group">
		<form:form method ="POST" action="/admin/addLocation" modelAttribute="location">
			<div class="form-control">
				<form:label path="name">Item Name:</form:label>
				<form:errors class="errors"  path="name"/>
				<form:input type="text" path="name"/>
			</div>
			<button>Create Location</button>
		</form:form>
	</div>
	<hr>
	<h4>Locations</h4>
	<ul>
		<c:forEach items="${allLocations}" var="all">
			<li>${all.name}</li>
		</c:forEach>
	</ul>
</div>

</body>
</html>