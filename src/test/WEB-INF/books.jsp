<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<a href="/logout">Logout</a>
<a href="/add">+ Add to my shelf!</a>

<h1>Welcome <c:out value= "${user.userName }"></c:out></h1>    
<p> Books From everyone's shelves:</p>
<table class="table table-dark">
  <thead>
    <tr>
      <th scope="col">ID</th>
      <th scope="col">Title</th>
      <th scope="col">Author Name</th>
      <th scope="col">Posted By</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach var="x"  items="${books }">
    <tr>
      <td><c:out value= "${x.id }"/></td>
      <td><c:out value= "${x.title }"/></td>
      <td><c:out value= "${x.author }"/></td>
      <td><c:out value= "${x.user.userName }"/></td>
    </tr>
    </c:forEach>
    </tbody>
    </table>
</body>
</html>
