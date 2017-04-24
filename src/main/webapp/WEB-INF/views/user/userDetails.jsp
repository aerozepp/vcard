<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
 /**
  * @Class Name : honme.jsp
  * @Description : vCard 메인화면
  * @Modification Information
  *
  * @author prettymuch(Hyun Jung)
  * @since 2017.04.19
  * @version 1.0
  *
  * Copyright (C) All right reserved.
  */
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>sjcard homepage</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>" />
<!-- 
<style>
.modal-header{
background-color : green;}
</style> -->
</head>
<body>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/css/tether-theme-arrows-dark.css"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
		crossorigin="anonymous"></script>


	<script>
		/* $(document).ready(function() {

			$('#btn-play').click(function() {
				var roomname = $("#roomname").val();

				if ($.trim(roomname).length <= 0) {
					alert("Fill in room name");
				} else {
					$.ajax({
						url : "php/openRoom.php",
						method : "POST",
						data : {
							roomname : roomname
						},
						cache : false,
						success : function(data) {

							$("body").load("index.php");

							alert(data);
						}
					});
				}

			});

		}); */
	</script>

	<section id="menuPanel"> <nav
		class="navbar navbar-inverse fluid navbar-static-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="/vCard">sjCard</a>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<c:if test="${not empty pageContext.request.userPrincipal}">
			
				<ul class="nav navbar-nav navbar-right">
					<li><a><span id="session_name"
							class="glyphicon glyphicon-user">
								${pageContext.request.userPrincipal.name} </span></a></li>
					<li><a href="<c:url value="/user/logout"/>"><span
							class="glyphicon glyphicon-log-out"></span>로그아웃</a></li>
				</ul>
			</c:if>

			<c:if test="${empty pageContext.request.userPrincipal}">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="<c:url value="/user/signup"/>"
						data-toggle="modal" data-target="#signup"><span
							class="glyphicon glyphicon-user"> </span> 회원가입</a></li>
					<li><a href="<c:url value="/user/login"/>"><span
							class="glyphicon glyphicon-log-in"></span> 로그인</a></li>
				</ul>
			</c:if>
		</div>
	</div>
	</nav> </section>
	
	<section>
	
	
	
	
	</section>

	


</body>
</html>