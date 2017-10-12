<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<title>Crytographic Email</title>

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
	<h4>User Home Page</h4>
	<div style="margin-left:10em">
		<input type="button"  onclick="location.href='logout'" value="Logout" >
	</div>
	<div align="right" style="margin-right:10em">
		<a href="#" onclick='sendMail()'>Compose Mail</a>
	</div>
	<div style="margin- left:5em">
		<c:if test="${not empty mailList.mails}">

			<ul>
				<c:forEach var="listValue" items="${mailList.mails}">
					<li><a href="#"
						onclick='showDetails("${listValue}","${mailList.userEmail}")'>${listValue}</a>
						<%-- <a href='viewDetails/${listValue}'>${listValue}</a> --%></li>
				</c:forEach>
			</ul>

		</c:if>
	</div>

	

	<script>
		function showDetails(mailId, userEmail) {
			

			var passphrase = prompt("Please enter your passphrase");
			window
					.open('viewDetails?mailId=' + mailId + '&passphrase='
							+ passphrase + '&userEmail=' + userEmail, null,
							'height=200,width=400,status=yes,toolbar=no,menubar=no,location=no');
		}
	</script>
	
	<script>
		function sendMail() {
		
			window
					.open('sendMail', null,
							'height=200,width=400,status=yes,toolbar=no,menubar=no,location=no');
		}
	</script>
	
	<spring:url value="/resources/core/js/home.js" var="coreJs" />
<spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs" />
 
<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
</body>
</html>