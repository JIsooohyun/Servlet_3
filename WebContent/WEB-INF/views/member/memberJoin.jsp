<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/bootstrap.jsp" />
</head>
<body>
	<c:import url="../temp/header.jsp" />
	<div class="container">
		<h1>Member Join</h1>
		<form action="" name="jj" id="frm" method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label for="id">Id:</label> 
				<input type="text" class="form-control" id="id" placeholder="Enter Id" name="id"> 
				<input type="hidden" id="idConfirm" value="0"> 
				<input type="button" value="중복확인" id="idcheck">
			</div>
			
			<div class="form-group">
				<label for="pwd">Password:</label> 
				<input type="password" class="form-control" id="pwd" placeholder="Enter password">
				<div id="valuecheck"></div>
			</div>

			<div class="form-group">
				<label for="pwd">Password:</label> 
				<input type="password" class="form-control" id="pwcheck" placeholder="Enter password" name="pw">
				<div id="valuepcheck"></div>
			</div>
			
			<div class="form-group">
				<label for="name">Name:</label> 
				<input type="text" class="form-control" id="name" placeholder="Enter name" name="name">
				<div id="valuecheck"></div>
			</div>

			<div class="form-group">
				<label for="email">Email:</label> 
				<input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
				<div id="emailcheck"></div>
			</div> 
			
			<div class="form-group">
				<label for="phone">Phone:</label> 
				<input type="tel" class="form-control" id="phone" placeholder="Enter Phone" name="phone">
			</div>
			
			<div class="form-group">
				<label for="age">Age:</label> 
				<input type="text" class="form-control" id="age" placeholder="Enter age" name="age">
				<div id="valuecheck"></div>
			</div>
			
			<div class="form-group">
				<label for="file">Image:</label> 
				<input type="file" class="form-control" id="file" name="file">
			</div>
			<input type="button" class="btn btn-default" value="Join" id="join">
		</form>
	</div>
	<script type="text/javascript">
	$(function() {
		//새창 여는 코드
		$('#idcheck').click(function() {
			var id = $('#id').val();
			open('./idcheck.jsp?id='+id,"_blank", "width=500, height=300");
		});
		
		//ID 10자 미만
		$('#id').blur(function() {
			if($('#id').val().length>9){
				alert("10자 미만 입력해주세요")
			}
		});
		
		//PW 6글자 이상 
		$('#pwd').keyup(function() {
			if($('#pwd').val().length<6){
				$('#valuecheck').html("6글자 이상 입력해주세요");
			}else{
				$('#valuecheck').html("OK");
			}
		})
		//PW같은지 확인 
		$('#pwcheck').keyup(function() {
			if($('#pwcheck').val()==$('#pwd').val()){
				$('#valuepcheck').html("ok");
			}else{
				$('#valuepcheck').html("비밀번호가 다릅니다.")
			}
		});
		
		
		$('#email').keyup(function() {
			var echeck = $('#email').val();
			var check = echeck.indexOf("@");
			if(check==-1){
				$('#emailcheck').html("이메일 형식으로 입력하세요");
			}else{
				$('#emailcheck').html("");
			}
			
		});
		
		
		$('#join').click(function() {
			var blank = true;
			$('.form-control').each(function() {
				if($(this).val()==""){  //꺼낸 것을 나타내줌
					alert($(this).attr('name')+"를 빈칸없이 입력하세요");
					blank = false;
					return false;
				}
			});
			var joincheck=true;
			if(blank){
				if($('#pwcheck').val()!=$('#pwd').val()){
					alert("비밀번호가 다릅니다.");
					joincheck=false;
				}else if($('#pwd').val().length<6){
					alert("비밀번호 6글자 이상 입력해주세요");
					joincheck=false;
				}
				
				if(joincheck){
					$('#frm').submit();
				} 
			}
		});
	});
</script>
</body>
</html>