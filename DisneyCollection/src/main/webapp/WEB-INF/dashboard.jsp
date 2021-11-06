<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome Page</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/css/style.css">

</head>
<body class="home">
<header>
	<form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout!" />
    </form>
  <a href="/admin">Admin Only</a> 
</header>
<div class ="container">

    <h1>Welcome, ${user.username}</h1>
    <hr>
    <table class ="table table-striped table-light table-bordered table-sm">
    	<thead>
    		<tr>
    			<th>Number of Likes <!--  <a class= "btn btn-light" href="/sort/LikesUp" ><span>&#8607;</span></a><a class="btn btn-light" href="/sort/LikesDown"> <span>&#8609;</span></a> --></th>
   				<th >Item Name  <a class= "btn btn-light" href="/sort/itemUp" ><span>&#8607;</span></a><a class="btn btn-light" href="/sort/itemDown"> <span>&#8609;</span></a></th>
    			<th>Item Location  <a class= "btn btn-light" href="/sort/locationUp" ><span>&#8607;</span></a><a class="btn btn-light" href="/sort/locationDown"> <span>&#8609;</span></a></th>
    			<th>Action</th>
    		</tr>
    	</thead>
    	<tbody>
    	<c:forEach items="${allItems}" var="item">
    		<tr>
    			<td>${item.likers.size()} </td>
    			<td><a href="/details/${item.id}">${item.name}</a></td>
    			<td>${item.roomItemIsIn.name}</td>
    			<td>
    			<c:choose>
    				<c:when test="${item.likers.contains(user)}">
    				<a href="/unlike/${item.id}">Unlike</a>
    			</c:when>
    				<c:otherwise>
    				<a href="/like/${item.id}">Like</a>
    				</c:otherwise>
    			</c:choose>
    			</td>
    		</tr>	
    	</c:forEach>
    	</tbody>
    	
    
    </table>
    </div>

</body>
</html>