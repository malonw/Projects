<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Item Details Page</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="/css/style.css">

</head>
<body class="ItemDetails">
	<header>
		<a href="/dashboard">Dashboard</a>
		<form id="logoutForm" method="POST" action="/logout">
	        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	        <input type="submit" value="Logout!" />
	    </form>
	    <a href="/admin/">Admin Home Page</a>
	    
	</header>
<hr>
<div class="container">
	<div class="details">
		<img src="/images/DisneyCastleLogo.jpeg" height="10%" width="10%"/>
		
		<div>
			<h2>${item.name}</h2>
	 
			<p><label><b>Description:</b></label>
				${item.description}</p>
			<p><label><b>Value: </b></label>
				<fmt:formatNumber value="${item.value}" type="currency"/></p>
		</div>
	</div>
	<div class="row">		
		<div class="imagePlace">
			<h5>Images</h5>
			<c:forEach items="${allPics}" var="all">
				<img src="${all.image_url}" class="rounded mx-auto d-block" alt="image of ${item.name}" height="75%" width="75%"/>
			<hr>
			</c:forEach>
		</div>
			<hr>
			<h4>Liked By</h4>
		<ul>
			<c:forEach items="${item.likers}" var="user">
				<li>${user.username}</li>
			</c:forEach>
		</ul>
	</div>	
<hr>
	<div class="row">
		<div class="col-md-6">
				<h4>Add a Comment</h4>
				<form:form action="/addComment/${item.id}" method= "POST" modelAttribute="comment">
					<form:label path="comments" class="form-label">Comment Here:</form:label>
					<form:errors path="comments"/>
					<form:textarea path="comments" class="form-control"/>
					<button class="btn btn-success">Submit</button>
				</form:form>
		</div>
		<div class="col-md-6">
			<h4>Comments</h4>
			<div class="comments">
				<c:forEach items="${item.comments}" var="all">
					<p>${all.author.username} states:
						${all.comments}</p>
						<hr>
				</c:forEach>
			</div>
		</div>
	</div>
</div>
</body>
</html>