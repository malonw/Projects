<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome Page</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="/css/style.css">

</head>
<body class ="AdminHome">
<header>
	<form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout!" />
    </form>
    <a href="/admin/addItem">Add an Item</a>
    <a href="/admin/locations">Add Location</a>
    <a href="/dashboard">Dashboard</a>
    <a href ="/admin/users">List of Registered Users</a>
</header>
<div class ="container">
    <h1>Welcome, ${user.username}</h1>
    <hr>
    <table class ="table table-striped table-light table-bordered">
    	<thead>
    		<tr>
    			<th>Number of Likes</th>
   				<th class="th-sm">Item Name  <a class= "btn btn-light" href="/admin/itemUp" ><span>&#8607;</span></a><a class="btn btn-light" href="/admin/itemDown"> <span>&#8609;</span></a></th>
    			<th>Item Location  <a class= "btn btn-light" href="/admin/locationUp" ><span>&#8607;</span></a><a class="btn btn-light" href="/admin/locationDown"> <span>&#8609;</span></a></th>
    			<th>Action</th>
   	
    		</tr>
    	</thead>
    	<tbody>
    	<c:forEach items="${allItems}" var="item">
    		<tr>
    			<td>${item.likers.size()}</td>
    			<td><a href="/details/${item.id}">${item.name}</a></td>
    			<td>${item.roomItemIsIn.name}</td>
    			<td><a href="/admin/editItem/${item.id}">Edit</a> | <a href="/delete/${item.id}">Delete</a></td>
    						
    		</tr>	
    	</c:forEach>
    	</tbody>
    	
    
    </table>
    
  </div>
</body>
</html>