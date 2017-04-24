	$(document).ready(function(){

		var stoneColor = '';
		var put=0; /*1 : my turn / 0 : the other's turn */

		$('td').click(function(){

			if(put == 0){
				alert("wait");
			}else{
				if($(this).attr('class').length == 0){
				
					var roomid = $(".status-roomid").val();
				
					var posid = $(this).attr('id');
		
		
					$.ajax({
						url : "php/move.php",
						method : "POST",
						data : {roomid : roomid, posid : posid, color : stoneColor},
						cache : false,
						success : function(data){
						
							var json = $.parseJSON(data);
							var color = '';	
								for(var i = 0 ; i < json.length ; i+=2){
								
									if(json[i+1] == 0){color = 'black'}else{color='white'}
					
										$('#' + json[i]).addClass(color);
								}
							}
					
					});	
					
					$.ajax({
						url : "php/checkWinner.php",
						method : "POST",
						data : {roomid : roomid, posid, posid, color : stoneColor},
						cache : false,
						success : function(data){
							
							if(data.msg == "win"){
									
							}						
							
						}
						
					});
				
				}	
			}
				
		
		});
		
		
		$("#btn-goout").click(function(){
		
			var roomid = $(".status-roomid").val();
		
			$.ajax({
					url : "php/deleteRoom.php",
					method : "POST",
					data : {roomid : roomid},
					cache : false,
					success : function(data){
						$("body").load("index.php");
						$('#menuPanel').css('display', 'block');
						$('#playRoom').css('display', 'block');
						$('#playPanel').css('display', 'none');
					}	
			});		
		});


		$("#btn-resign").click(function(){
			
			var roomid = $(".status-roomid").val();
		
			$.ajax({
					url : "php/deleteRoom.php",
					method : "POST",
					data : {roomid : roomid},
					cache : false,
					success : function(data){
						$("body").load("index.php");
						$('#menuPanel').css('display', 'block');
						$('#playRoom').css('display', 'block');
						$('#playPanel').css('display', 'none');
					}	
			});		
		});

		

		$("#btn-login").click(function(){

			var username = $("#login-username").val();
			var password = $("#login-password").val();

			if($.trim(username).length <= 0 || $.trim(password).length <= 0){
					alert("Fill in all fields");
			}else{
					$.ajax({
							url : "php/login.php",
							method : "POST",
							data : {username : username, password : password},
							cache : false,
							success : function(data){
								$("body").load("index.php");
								alert(data);
							}	
					});
			}				

		});

		$("#btn-logout").click(function(){

			$.ajax({
				url : "php/logout.php",
		
				success : function(){
					$("body").load("index.php");
					alert("You are logged out");
				}
			});
		});

		$('#btn-play').click(function(){
			var roomname = $("#roomname").val();

			if($.trim(roomname).length <= 0){
					alert("Fill in room name");
			}else{
					$.ajax({
							url : "php/openRoom.php",
							method : "POST",
							data : {roomname : roomname},
							cache : false,
							success : function(data){

								$("body").load("index.php");

								alert(data);
							}	
					});
			}

		});

		$(".join").click(function(){
			var room = $(this).attr('name');
			$.ajax({
						url : "php/joinRoom.php",
						method : "POST",
						cache : false,
						data: {room: room},
						success : function(data){
							$("body").load("index.php");
							alert(data);	
						}	
					});
		});

		$(".enter").click(function(){
		
			var room = $(this).attr('name');
			var roomid='';
			$.ajax({
						url : "php/enter.php",
						method : "POST",
						cache : false,
						data: {room: room},
						success : function(data){

							if(data.msg == "Play"){
								$('#menuPanel').css('display', 'none');
								$('#playRoom').css('display', 'none');
								$('#playPanel').css('display', 'block');
								$('.status-roomid').html(data.roomid);
								$('.status-roomid').val(data.roomid);
								$('.status-roomname').html(data.roomname);
								$('.status-user1').html(data.user1);
								$('.status-user2').html(data.user2);
								
								roomid = data.roomid;
								if(data.session == data.user1){
									stoneColor = "black";
									put = 1;
								}else{
									stoneColor = "white";
									put = 0;
								}
								alert(data.msg);
							}else{
								alert(data.msg);		
							}

							
						}	
			});
			
			setInterval(function(){
				$.ajax({
					url : "php/refresh.php",
					method : "POST",
					data : {roomid : roomid},
					cache : false,
					success : function(data){
						
				
					var json = $.parseJSON(data);
					var color = '';	
						for(var i = 0 ; i < json.length ; i+=2){
				
							if(json[i+1] == 0){color = 'black'}else{color='white'}
				
							$('#' + json[i]).addClass(color);
							}
					
					if(stoneColor == color){
						put = 0;
					
					}	
					else{
						put = 1;
				
					}
						
						
					}
					
					
					
						
				});	
				
			}, 2000);
				
			
			
		});

		$('#btn-send').click(function(){
			var msg = $("#message").val();
			var roomid = $(".status-roomid").val();
		
			$.ajax({
					url : "php/message.php",
					method : "POST",
					data : {msg : msg, roomid : roomid},
					cache : false,
					success : function(data){

						$(".chatlog").html(data);
						$("#message").val('');
					}	
			});
		

		});
		
		$.ajaxSetup({cache:false});
		setInterval(function(){
			var roomid = $(".status-roomid").val();
			
			$('.chatlog').load('php/logs.php?roomid='+roomid);}, 2000);
			
			
	/*	$('.btn-play').click(function(){

			$('#playPanel').css('display', 'block');
		});*/
	});