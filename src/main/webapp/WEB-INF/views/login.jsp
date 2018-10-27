<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<title>Login page</title>
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>

</head>
<body>
	<c:url var="loginUrl" value="/login" />
	<c:url var="registrationUrl" value="/newuser" />
	<div class="container">
		<h2>Login page</h2>

		<form action="${loginUrl}" method="post">

			<c:if test="${param.error != null}">
				<div class="alert alert-danger">
					<p>Invalid username and password.</p>
				</div>
			</c:if>

			<c:if test="${param.logout != null}">
				<div class="alert alert-success">
					<p>You have been logged out successfully.</p>
				</div>
			</c:if>

			<div class="form-group">
				<label for="username">Username</label> <input type="text"
					placeholder="Enter Username" id="username" name="username" required>
			</div>

			<div class="form-group">
				<label for="password">Password</label> <input type="password"
					placeholder="Enter Password" id="password" name="password" required>
			</div>

			<div>
				<label><input type="checkbox" id=rememberme
					name="remember-me"> Remember me</label>
			</div>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

			<button type="submit">Log in</button>

		</form>

		<a href="${registrationUrl}">Create an account</a>

	</div>
</body>
</html>