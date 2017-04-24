<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	/**
	 * @Class Name : loginPage.jsp
	 * @Description : vCard �α��� ȭ��
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
<title>sjCard login</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">



<style>
.modal-header {
	background-color: grey;
	color: white;
}
.login-status{
	color : red;
}
.signup-status{
	color : red;
}
</style>
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
		 
		$(document).ready(function(){
		
			$("#btn-login").click(function(){
			
				var username = $("#login-username").val();
				var password = $("#login-password").val();
					
				if($.trim(username).length <= 0){
					$(".login-status").text("����ڸ��� �Է����ּ���.");	
				}else if($.trim(password).length <= 0){
					$(".login-status").text("��й�ȣ�� �Է����ּ���.");
				}else{
					$(".login-status").text("����......");
					$.ajax({
						url : "/user/login",
						contentType : "application/json",
						dataType : 'json',
						method : "POST",
						data : {username : username, password : password},
						cache : false,
						success : function(){
							alert("hello");
						}	
				});
		}				
			});
			
		
					 
			 
			  $("#btn-signup").click(function(event){
		
				var username = $("#signup-username").val();
				var password = $("#signup-password").val();
				var pw_confirm = $("#signup-confirm").val();
				
				if($.trim(username).length <= 0){
					$(".signup-status").text("����ڸ��� �Է����ּ���.");	
				}else if($.trim(password).length <= 0 || $.trim(pw_confirm).length <= 0){
					$(".signup-status").text("��й�ȣ�� �Է����ּ���.");
				}else if(password != pw_confirm){
					$(".signup-status").text("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				}else{
					$(".signup-status").text("����.....");
					$("#insertUserFrom").submit();
						
						/* $.ajax({
								url : $("#insertUserForm").attr("action"),
								contentType : "application/json",
								dataType : 'json',
								method : "POST",
								data : {username:username, password:password},
								cache : false,
								success : function(){
									alert("hello");
								}	
						}); */
				}				
			}); 
		});
			
			
	</script>



	<div class="container">
		<div class="panel panel-primary">
			<div class="panel-heading">SJCARD �α���</div>

			<div class="panel-body">
				<form name="loginForm" action="<c:url value="/user/login"/>"
					method="POST">

					<div>

						<label>����ڸ� : &nbsp</label><input type="text" name="username" id="login-username"
							placeholder="username"><br/> <label>��й�ȣ
							: &nbsp</label><input type="password" name="password" id="login-password" placeholder="password"><br/><br/>
						<div class="login-status"></div>
					</div>
					<div class="panel-footer">
						<span class="btn-group pull-right"> <button type="button"
							id="btn-login">�α���</button> <button type="button"
							data-toggle="modal" data-target="#signup">ȸ������</button>
						</span>

					</div>
				</form>
			</div>
		</div>
	</div>

	<div class="modal fade" id="signup">

		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">ȸ������</div>
				<form id="insertUserFrom" name="signupForm" action="/user/insertUser" method="POST" enctype="multipart/form-data">
					<!-- ==============openRoom.php ========================== -->
					<div class="modal-body">

						<label>����ڸ� : &nbsp</label><input type="text" name="username"
							id="signup-username" placeholder="������̸�"> </br> 
						<label>��й�ȣ : &nbsp</label><input type="password" name="password"
							id="signup-password" placeholder="�н�����"> </br> 
						<label>���Ȯ�� : &nbsp</label><input type="password" name="password-confirm"
							id="signup-confirm" placeholder="�н�����"> </br><br/>
						<label>VCF����</label><input type="file" name="file"  value="VCF ���� ����"><br/>
						
						<div class="signup-status"></div>
					</div>
					
					
					<div class="modal-footer">
						<input type="button" value="����" id="btn-signup" class="btn btn-default">
						<button type="button" class="btn btn-default" data-dismiss="modal">�ݱ�</button>
					</div>
				</form>
			</div>

		</div>
	</div>

</body>
</html>