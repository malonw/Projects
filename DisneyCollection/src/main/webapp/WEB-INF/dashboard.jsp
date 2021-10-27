<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome Page</title>
</head>
<body>
<header>
	<form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout!" />
    </form>
    
</header>
<div class ="container">
    <h1>Welcome Page <c:out value="${user.username}"></c:out></h1>
    <hr>
    <table class ="table table-striped table-light">
    	<thead>
    		<tr>
    			<th>Number of Likes</th>
   				<th>Item Name</th>
    			<th>Item Location</th>
    			<th>Item Image</th>
    			<th>Action</th>
    		</tr>
    	</thead>
    	<tbody>
    	<c:forEach items="${allItems}" var="item">
    		<tr>
    			<td>${item.likers.size()}</td>
    			<td><a href="/details/${item.id}">${item.name}</a></td>
    			<td>${item.location}</td>
    			<td>${item.base64image}</td>
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