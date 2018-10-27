<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>

<head>
<meta charset=UTF-8">
<title>New/Edit Expense</title>
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
	<c:url var="saveExpense" value="/newexpense" />
	<div class="container">
		<%@include file="authheader.jsp"%>
		<h2>New/Edit Expense</h2>

		<form:form action="${saveExpense}" method="POST" modelAttribute="userExpense">
			<form:input type="hidden" path="id" />
			
			<div class="form-group">
				<label for="firstName">Purpose</label>
				<form:input type="text" path="purpose" id="purpose" />
				<div class="has-error">
					<form:errors path="purpose" />
				</div>
			</div>

			<div class="form-group">
				<label for="expense">Expense</label>
				<form:input type="number" step="0.01" path="expense" id="expense" />
				<div class="has-error">
					<form:errors path="expense" />
				</div>
			</div>

			<div class="form-group">
				<label for="expenseDate">Date</label>
				<form:input type="datetime-local" min="0000-06-07T00:00" max="9999-06-07T00:00" path="expenseDate"
					id="expenseDate" />
				<div class="has-error">
					<form:errors path="expenseDate" />
				</div>
			</div>

			<button class="submit" type="submit">Save</button>
		</form:form>
	</div>
</body>
</html>