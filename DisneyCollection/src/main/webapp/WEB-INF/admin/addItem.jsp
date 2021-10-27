<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="container">
<h1>Create an Item</h1>
<hr>
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
				<form:label path="value">Value:</form:label>
				<form:errors class="errors"  path="value"/>
				<form:input type="number" path="value" />
			</div>
			<div class="form-control">
				<form:label path="base64Image">Image:</form:label>
				<form:errors class="errors"  path="base64Image"/>
				<form:input type="image" path="base64Image" />
			</div>
			<button>Create Item</button>
		</form:form>
	</div>
</div>

</body>
</html>