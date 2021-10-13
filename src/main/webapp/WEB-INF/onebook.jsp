<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<!-- for Bootstrap CSS -->
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	<!-- YOUR own local CSS -->
	<link rel="stylesheet" href="/css/main.css"/>
	<!-- For any Bootstrap that uses JS or jQuery-->
	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	<title>Insert title here</title>
</head>
<body>
 <h1> <c:out value="${book.title}"></c:out></h1>
<br>
<p><c:out value="${user.userName}"></c:out> read <c:out value= "${book.title}"></c:out> by  <c:out value="${book.author}."></c:out></p>

<p><c:out value = "Here are ${user.userName}'s thoughts:"></c:out></p>

<p><c:out value="${book.thoughts}"></c:out></p>

<c:choose>

                                <c:when test="${book.user.id == user.id}">
                                <a href="/editBook/${book.id}" class="mx-3">Edit</a> <a href="/destroy/${book.id}">Delete</a>
                                </c:when>

                                <c:otherwise>
                                <p style="color:red;"> Your Not The Master! </p>
                                </c:otherwise> 

                            </c:choose>
                            
                            <a href="/home">Home</a>
</html>
