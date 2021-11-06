<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="/css/style.css">

</head>
<body class="addItem">
	<header>
		<a href="/admin">Admin Home Page</a>
		<a href="/dashboard">User Home Page</a>
	</header>

<div class="container">
	<h1>Create an Item</h1>
	<hr>
	<div class="row">
		<div class="col-md-3">
			<div class="form-group">
				<form:form method ="POST" action="/admin/new" modelAttribute="item">
					<div class="form-control">
						<form:label path="name">Item Name:</form:label>
						<form:errors class="errors"  path="name"/>
						<form:input type="text" path="name"/>
					</div>
					<div class="form-control">
						<form:label path="description">Description:</form:label>
						<form:errors class="errors"  path="description"/>
						<form:textarea type="text" path="description" />
					</div>
					<div class="form-control">
						<form:label path="roomItemIsIn">Location</form:label>
						<form:select path="roomItemIsIn">
							<c:forEach items="${allLocations}" var="all">
								<option value="${all.id}">${all.name}</option>
							</c:forEach>
						</form:select>
					</div>
					
					<div class="form-control">
						<form:label path="value">Value:$</form:label>
						<form:errors class="errors"  path="value"/>
						<form:input type="number" path="value" />
					</div>
					<div class="form-control">
						<p>Upload Images on Edit Page</p>
					</div>
					<button class= "btn btn-success">Create Item</button>
				</form:form>
			</div>
		</div>
	</div>
</div>

</body>
</html>