<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Cryptographic mail</title>

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
<h4>Send Mail</h4>

	<form:form method="POST" action="sendMail" modelAttribute="mailDetails">
		<table class="center">
			<tr>
				<td><form:label path="to">To</form:label></td>
				<td><form:input path="to" /></td>
			</tr>
			<tr>
				<td><form:label path="from">From</form:label></td>
				<td><form:input path="from" /></td>
			</tr>
			<tr>
				<td><form:label path="subject">Subject</form:label></td>
				<td><form:input path="subject" /></td>
			</tr>
			<tr>
				<td><form:label path="content">Content</form:label></td>
				<td><form:textarea path="content" /></td>
			</tr>
			<tr>
				<td/>
				<td colspan="2"><input type="submit" value="Send" /></td>
			</tr>
		</table>
	</form:form>
	
	<spring:url value="/resources/core/js/home.js" var="coreJs" />
<spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs" />
 
<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
</body>
</html>