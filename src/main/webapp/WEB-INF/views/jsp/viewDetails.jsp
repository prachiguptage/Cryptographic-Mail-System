<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Crytographic Emaill</title>

<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />

<spring:url value="/resources/core/css/main.css" var="mainCss" />
<link rel="stylesheet" type="text/css" href="${mainCss}">
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
	<div class="navbar-header">
		<a class="navbar-brand" href="#" onclick="location.href='home'">Columbus State University - Cryptographic Email</a>
	</div>
  </div>
</nav>

<h2>heading</h2>



	<table class="center">
		<tr>
			<td><label >To</label></td>
			<td><c:out value='${mailDetails.to}' /></td>
		</tr>
		<tr>
			<td><label >From</label></td>
			<td><c:out value='${mailDetails.from}' /></td>
		</tr>
		<tr>
			<td><label >Subject</label></td>
			<td><c:out value='${mailDetails.subject}' /></td>
		</tr>
		<tr>
			<td><label>Content</label></td>
			<td><c:out value='${mailDetails.content}' /></td>
		</tr>
	</table>
	
	<spring:url value="/resources/core/js/home.js" var="coreJs" />
<spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs" />
 
<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	
</body>
</html>