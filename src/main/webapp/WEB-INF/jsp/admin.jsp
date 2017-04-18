<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<title>Admin</title>
</head>
<body class="body">
<div>
<table class="table table-hover">
					<tr>
						<th>Id</th>
						<th>Description</th>
						<th>Options</th>
					<c:forEach items="${webpages}" var="web">
					
				<tbody>
							<td>${web.page_id}</td>
							
							<td>${web.description}</td>
					
							<td><a href="<c:url value='/delete-page-${web.page_id}' />"
							class="btn btn-danger custom-width">delete</a><a href="<c:url value='/edit-webpage-${web.page_id }' />"
							class="btn btn-info custom-width"> edit </a></td>
							
						</tr>
					</c:forEach>
					<td><a href="<c:url value='/new-webpage' />"
							class="btn btn-primary custom-width">new </a></td>
		    		</tbody>
				</table>
	</div>
</body>
</html>