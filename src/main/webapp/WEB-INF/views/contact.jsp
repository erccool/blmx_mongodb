<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>NoSQL - Mongo DB</title>
<link rel="stylesheet" href="css/styles.css">
</head>
<body>
	<h3><c:if test="${empty document}">New</c:if> Contact Form</h3>
	<form action="home" method="POST">
		<button type="submit">Save</button>
		<a href="home">Cancel</a>
		<hr>

		<c:if test="${!empty document}">
			<input name="id" type="hidden" value="${document.id}">
		</c:if>
		<ul class="fields">

			<li>Name: <input name="name" type="text"
				placeholder="Please enter your name." required
				<c:if test="${!empty document}">value="${document.name}"</c:if>>
			</li>
			<li>Email: <input name="email" type="email"
				placeholder="Please enter your email." required
				<c:if test="${!empty document}">value="${document.email}"</c:if>>
			</li>
			<li>Mobile No.: <input name="mobile" type="text"
				placeholder="Please enter your mobile no." required
				<c:if test="${!empty document}">value="${document.mobile}"</c:if>>
			</li>
		</ul>

	</form>
</body>
</html>