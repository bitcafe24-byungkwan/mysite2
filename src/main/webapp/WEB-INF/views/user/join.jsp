<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath }/assets/css/user.css"
	rel="stylesheet" type="text/css">
	<script src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
	
<script>
$(function(){
	$('#email').change(function(){
		$('#check-button').show();
		$('#check-image').hide();
	});
	$('#check-button').click(function(){
		var email = $('#email').val();
		if(email == '') return;
		
		// ajax comm 
		$.ajax({
			url: "${pageContext.servletContext.contextPath }/user/api/checkemail?email=" + email,
			type:"get",
			dataType:"json",
			data:"",
			success:function(response){
				if(response.result != "success"){
					console.error(response.message);
					console.log(response);
					return;
				}
				
				if(response.data == true){	
					alert('이미 존재하는 이메일입니다.\n다른 이메일 ㄱㄱ')
					$('#email').focus().val("");
					
					return;
				}
				$('#check-button').hide();
				$('#check-image').show();
			},
			error:function(hxr,msg){
				console.error("error:" + msg);
			}
		})
		console.log(email);
	})
});
	
</script>
</head>
<body>
	<div id="container">
		<c:import url='/WEB-INF/views/includes/header.jsp' />

		<div id="content">
			<div id="user">

				<form id="join-form" name="joinForm" method="post"
					action="${pageContext.servletContext.contextPath }/user/join">
					<input type="hidden" name="a" value="join"> <label
						class="block-label" for="name">이름</label> <input id="name"
						name="name" type="text" value=""> <label
						class="block-label" for="email">이메일</label> <input id="email"
						name="email" type="text" value=""> <input type="button" id = "check-button" 
						value="체크"> 
						
						<img style ="display:none" id = "check-image" src ="${pageContext.servletContext.contextPath }/assets/images/check.png">
						
						
						<label class="block-label">패스워드</label> <input
						name="password" type="password" value="">

					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female"
							checked="checked"> <label>남</label> <input type="radio"
							name="gender" value="male">
					</fieldset>

					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>

					<input type="submit" value="가입하기">

				</form>
			</div>
		</div>
		<c:import url='/WEB-INF/views/includes/navigation.jsp'>
			<c:param name="menu" value="main" />
		</c:import>

		<c:import url='/WEB-INF/views/includes/footer.jsp' />
	</div>
</body>
</html>