<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
		<!-- for Bootstrap CSS -->
	<link rel="stylesheet"
	href="/webjars/bootstrap/5.0.1/css/bootstrap.min.css" />

	<!-- YOUR own local CSS -->
	<link rel="stylesheet" href="/css/main.css"/>
	<!-- For any Bootstrap that uses JS or jQuery-->
	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/5.0.1/js/bootstrap.min.js"></script>
	<title>Insert title here</title>
</head>
<body>
<h1>Change your Entry</h1>
<br>
<a href="/home">back to the shelves</a>

<br>
<form:form action= "/editBook/${book.id}" method = "POST" modelAttribute="book">
<input type="hidden" name="_method" value="put">
 <form:hidden path = "user" value = "${user.id}" />
			<form:errors path="user" class="text-danger" />
<p>
        <form:label path="title">Title:</form:label>
        <form:errors path="title"/>
        <form:input path="title"/>
    </p>
    <p>
        <form:label path="author">Author:</form:label>
        <form:errors path="author"/>
        <form:input path="author"/>
    </p>

    <p>
        <form:label path="thoughts">My Thoughts:</form:label>
        <form:errors path="thoughts"/>
        <form:textarea path="thoughts"/>
    </p>
    <input type="submit" value="Submit"/>
</form:form>

</body>
</html>
