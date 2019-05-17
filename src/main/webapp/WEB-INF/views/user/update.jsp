
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
<link href="${pageContext.servletContext.contextPath }/assets/css/user.css"
	rel="stylesheet" type="text/css">
	<script type="text/javascript">
		window.onload = function(){			
			document.getElementById("${userVo.gender}").checked = true;
		};
		
	</script>
	
	<c:if test='${requestScope.result == "success" }'>
	<script>
		alert( "정상적으로 수정 하였습니다." );
		window.location.href = '${pageContext.servletContext.contextPath }/';
	</script>
</c:if>
</head>
<body>
	<div id="container">
		<c:import url='/WEB-INF/views/includes/header.jsp' />
		<div id="content">
			<div id="user">
				
				<form id="join-form" name="joinForm" method="post"
					action="${pageContext.servletContext.contextPath }/user/update">
					<input
						type="hidden" name="no" value="${userVo.no }"> <label
						class="block-label" for="email">이메일</label>
					<h4>${userVo.email }</h4>

					<label class="block-label" for="name">이름</label> <input id="name"
						name="name" type="text" value="${userVo.name }"> <label
						class="block-label">패스워드</label> <input name="password"
						type="password" value="">

					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input id="female" type="radio" name="gender" value="female">
						<label>남</label> <input id="male" type="radio" name="gender" value="male">
						<!-- 
						<c:choose>
						 <c:when test='${userVo.gender == "female"}'>
					
						<label>여</label> <input id="radio_female" type="radio" name="gender" value="female"
							checked="checked">
							 <label>남</label> <input id="radio_male" type="radio"
							name="gender" value="male">

						</c:when>
						<c:otherwise>
						<label>여</label> <input id="radio_female" type="radio" name="gender" value="female">
						<label>남</label> <input id="radio_male" type="radio" name="gender" value="male"
							checked="checked">

						</c:otherwise>
						</c:choose>
						 -->
					</fieldset>


					<input type="submit" value="변경하기">
					<c:if test='${requestScope.result == "fail" }'>
					<p>
						아마 비밀번호나 이름이 너무 길어서 실패한거같아요 짧게 다시하세요
					</p>
					</c:if>
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