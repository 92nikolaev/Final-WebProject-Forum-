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
		<div class="item-list">
		 	<div  class="hidden" id="locale">${sessionScope.locale}</div> 
			<form id="RegistrationForm" action="controller" method="post">
				<table>
					<tr>
						<td><label for="user_name">${sign_up_name}</label></td>
						<td><input id="user_name" name="user_name" type="text" required></td>
						<c:if test="${error_status == 'invalid_name'}">
							<td class="error_valid">${sign_up_invalid_name}</td>	
						</c:if>
					</tr>
					<tr>
						<td><label for="user_surname">${sign_up_surname}</label></td>
						<td><input id="user_surname" name="user_surname" type="text" required></td>
						<c:if test="${error_status == 'invalid_surname'}">
							<td class="error_valid">${sign_up_invalid_surname}</td>	
						</c:if>
					</tr>
	
					<tr>
						<td><label for="user_login">${sign_up_login}</label></td>
						<td><input id="user_login" name="user_login" type="text" required></td>
						<c:if test="${requestScope.error_status == 'invalid_login'}">
							<td class="error_valid">${sign_up_invalid_login}</td>	
						</c:if>
						<c:if test="${requestScope.error_status == 'login_exist'}">
							<td class="error_valid">${sign_up_login_exist}</td>	
						</c:if>
						<c:if test="${requestScope.error_status == 'login_email_exests'}">
							<td class="error_valid">${sign_up_login_email_exests}</td>	
						</c:if>
					</tr>
	
					<tr>
						<td><label for="user_password">${sign_up_password}</label></td>
						<td><input id="user_password" name="user_password" type="password" required></td>
						<c:if test="${requestScope.error_status == 'invalid_password'}">
							<td class="error_valid">${sign_up_invalid_password}</td>	
						</c:if>
					</tr>
	
					<tr>
						<td><label for="user_confirm_password">${sign_up_confirm_password}</label></td>
						<td><input id="user_confirm_password" name=verification_password type="password" required></td>
					</tr>
					<tr>
						<td>
							<label>${sign_up_email}</label>
						</td>
						<td>
						<div id="BoxesGroup">
								<input id="textbox" name="user_email" type="text" required>
						</div>
						</td>
						<c:if test="${requestScope.error_status == 'invalid_email'}">
							<td class="error_valid">${sign_up_invalid_email}</td>	
						</c:if>
						<c:if test="${requestScope.error_status == 'email_exests'}">
							<td class="error_valid">${sign_up_email_exests}</td>	
						</c:if>
						<c:if test="${requestScope.error_status == 'login_email_exests'}">
							<td class="error_valid">${sign_up_login_email_exests}</td>	
						</c:if>
					</tr>
				</table>
				<br>
				<button type="submit" onclick="validate(this.form)" name="command" value="sign_up" class="btn signUp_btn">${sign_up_btn}</button>
			</form>
			</div>
			<aside>
				<%@include file="../elementpage/element_news.jspf"%>
			</aside>
		</div>	
	<%@include file="../elementpage/element_footer.jspf" %>
	<%@include file="../elementpage/element_popUp.jspf" %>
	<%@include file="../elementpage/element_modal_overlay.jspf" %>
	<script type="text/javascript" src="js/validationn.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
</body>
</html>