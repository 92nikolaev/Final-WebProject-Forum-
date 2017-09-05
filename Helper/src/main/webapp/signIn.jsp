<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../elementpage/element_localization.jspf" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../elementpage/element_head.jspf" %>
<title>Helper</title>
</head>
<body>
<%@ include file="../elementpage/element_header.jspf" %>
	<div class="container">
		<div class="modal-content modal-content-show">
			<button class="modal-content-close" type="button" title="Закрыть">Close</button>
			<h2 class="modal-content-title">${popUp_signIn_title}</h2>
			<c:choose>
				<c:when test="${error_status == 'Failed sign in'}">
					<p class="error">${popUp_signIn_description_action_error}</p>
				</c:when>
				<c:when test="${error_status == 'User is locked'}">
					<p  class="error">${popUp_signIn_description_action_ban}</p>
				</c:when>
				<c:otherwise>
					<p>${popUp_signIn_description_action}</p>
				</c:otherwise>
			</c:choose>
			<form class="login-form" action="controller" method="post">
				<input class="icon-user" type="text" name="user_login" placeholder="${popUp_signIn_login}" required pattern="^[A-Za-z0-9_-]{5,16}$">
				<input class="icon-password" type="password" name="user_password" placeholder="${popUp_signIn_password}" required pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])\w{6,}$">
				<a class="restore-right" href="controller?command=sign_up_page">${popUp_signIn_signUp}</a>
				<button class="btn" type="submit" name="command" value="sign_in">${popUp_signIn_signIn}</button>
			</form>
		</div>
	</div>
	<%@include file="../elementpage/element_footer.jspf" %>
	<script type="text/javascript" src="js/common.js"></script>
	
</body>
</html>
