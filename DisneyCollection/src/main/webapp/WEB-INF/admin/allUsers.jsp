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
<body class ="users">
<header>
	<form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout!" />
    </form>
    <a href="/admin">Admin Dashboard</a>

</header>
<div class ="container">
    <h1>Welcome, Admin</h1>
    <hr>
    <table class ="table table-striped table-light table-bordered">
    	<thead>
    		<tr>

   				<th class="th-sm">Username  <a class= "btn btn-light" href="/admin/userNameUp" ><span>&#8607;</span></a><a class="btn btn-light" href="/admin/userNameDown"> <span>&#8609;</span></a></th>
    			<th>Created Date</th>
    			<th>Action</th>
   	
    		</tr>
    	</thead>
    	<tbody>
    	<c:forEach items="${allUsers}" var="user">
    		<tr>
    			<td>${user.username}</td>
    			<td>${user.createdAt}</td>
    			<td><a href="/deleteUser/${user.id}">Delete</a></td>
    						
    		</tr>	
    	</c:forEach>
    	</tbody>
    	
    
    </table>
    
  </div>
</body>
</html>