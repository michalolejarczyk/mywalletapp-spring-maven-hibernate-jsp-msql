<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>

<head>
<meta charset=UTF-8">
<title>Expenses</title>
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
	<div class="container">
		<%@include file="authheader.jsp"%>
		<h2>List of expenses</h2>

		<table class="table">
			<thead>
				<tr>
					<th>Date</th>
					<th>Description</th>
					<th>Expense</th>
					<th width="100"></th>
					<th width="100"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${userexpenses}" var="userexpense">
					<tr>

						<td>${userexpense.expenseDate}</td>
						<td>${userexpense.purpose}</td>
						<td>${userexpense.expense}</td>
						<td><a
							href="<c:url value='/edit-expense-${userexpense.id}' />"
							class="btn btn-success custom-width">edit</a></td>
						<td><a
							href="<c:url value='/delete-expense-${userexpense.id}' />"
							class="btn btn-danger custom-width">delete</a></td>

					</tr>
				</c:forEach>
			</tbody>
		</table>

		<div>
			<a href="<c:url value='/newexpense' />">Add New Expense</a>
		</div>

	</div>
</body>
</html>