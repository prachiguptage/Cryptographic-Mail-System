<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<title>Crytographic Email</title>

<spring:url value="/resources/core/css/main.css" var="mainCss" />
<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="${mainCss}">

</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
	<div class="navbar-header">
		<a style="text-color:grey;"  class="navbar-brand" href="#" onclick="location.href='home'">Columbus State University - Cryptographic Email</a>
	</div>
  </div>
</nav>
	<h2>heading</h2>
	<h4 >User Registration</h4>
	<form:form method="POST" action="register" modelAttribute="userRegistration" >
		<table class="center" >
			<tr>
				<td><form:label path="name">Name</form:label></td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td><form:label path="email">Email</form:label></td>
				<td><form:input path="email" /></td>
			</tr>
			<tr>
				<td><form:label path="passphrase">Passphrase</form:label></td>
				<td><form:input type="password" path="passphrase" /></td>
			</tr>
			<tr/>
			<tr>
				<td/>
				<td colspan="2"><input type="submit" value="Submit" /></td>
			</tr>
			<tr/>
			<tr>
				<td >	<input type="button"  onclick="location.href='login'" value="Go to Login" ></td>
				<td align="center">	<input type="button"  onclick="location.href='home'" value="Go to Home" ></td>
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