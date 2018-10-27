<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Form</title>
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>

</head>
<body>
	<div class="container">
		<h2>User Registration Form</h2>

		<form:form method="POST" modelAttribute="user">
			<form:input type="hidden" path="id" id="id" />

			<div class="form-group">
				<label for="firstName">First Name</label>
				<form:input type="text" path="firstName" id="firstName" />
				<div class="has-error">
					<form:errors path="firstName" />
				</div>
			</div>

			<div class="form-group">
				<label for="lastName">Last Name</label>
				<form:input type="text" path="lastName" id="lastName" />
				<div class="has-error">
					<form:errors path="lastName" />
				</div>
			</div>

			<div class="form-group">
				<label for="ssoId">Username</label>
				<form:input type="text" path="username" id="ssoId" />
				<div class="has-error">
					<form:errors path="username" />
				</div>
			</div>

			<div class="form-group">
				<label for=password>Password</label>
				<form:input type="password" path="password" id="password" />
				<div class="has-error">
					<form:errors path="password" />
				</div>
			</div>
			
			<div class="form-group">
				<label for=email>Email</label>
				<form:input type="text" path="email" id="email" />
				<div class="has-error">
					<form:errors path="email" />
				</div>
			</div>
			
			<button type="submit">Submit</button>
		</form:form>

	</div>
</body>
</html>