<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../elementpage/element_localization.jspf" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../elementpage/element_head.jspf" %>
<title>Helper User Profile</title>
</head>
<body>
<%@ include file="../elementpage/element_header.jspf" %>
	<div class="container">
		<c:if test="${message == 'changed successfully'}">
			<div><p class="successful">${profile_data_changed_successfully}</p></div>
		</c:if>
		<c:if test="${message == 'faild change'}">
			<div><p class="error">${profile_data_changed_faild}</p></div>
		</c:if>
		<c:if test="${message == 'Successfully changed pasword'}">
			<div><p class="successful">Successfully changed pasword</p></div>
		</c:if>
		<c:if test="${message == 'Failed to change password'}">
			<div><p class="error">Failed to change password</p></div>
		</c:if>
		<div class="inner-content">
			<div class="big-heading">
				<h2>${profile_title}</h2>
			</div>
			<div class="inner-columns clearfix">
				<div class="cabinet-inner-column-left">
				 <button class="btn ask_question" type="submit" name="command" value="ask_qestion">${profile_ask_question}</button><br>
				<h2>${profile_personal_info}</h2>
					<ul class="custom-list-1">					
						<li>${profile_user_name} : <span class="cabinet-user-content">${user.name}</span> </li>
						<li>${profile_user_surname} : <span class="cabinet-user-content2">${user.surname}</span></li>
						<li>${profile_user_login} : <span class="cabinet-user-content">${user.email}</span></li>
						<li>${profile_user_email} : <span class="cabinet-user-content">${user.login}</span></li>			
					</ul>
					<h2>${profile_user_settings}</h2>   
                            <button class="btn edit" type="submit" name="editProfile" value="edit_profile">${profile_edit_user_data}</button><br> 
                            <button class="btn edit_pasword" type="submit" name="editProfile" value="edit_profile">${profile_change_password}</button><br>     
				</div>
				<div class="cabinet-inner-column-right">
					<table id="topics-table" class="table table-with-titles">
						<tr>
						<td colspan="3" class="table-inner">
							<h2 class="h-nostyle h-header">
								${profile_my_discussion}
							</h2>
						</td>
						</tr>
					<c:forEach items="${requestScope.questions}" var="question">
						<tr id="question-my" class="question">
						<td class="col1">
								<h3 class="h-nostyle">
									<a class="branch-title" href="#" data-original-title="">${question.title}</a>
								</h3>
						</td>
						<td class="col2">
							<p>${question.dateCreated}</p>
						</td>
						<td class="col3">
						<form  action="controller" method="post">
							<input type="hidden" name="question_id" value="${question.id}">
							<button class="btn" type="submit" name="command" value="edit_question">${profile_edit_question}</button> 
						</form>
						</td>
						</tr>
					</c:forEach>
				</table>
				</div>
			</div>
		</div>
	</div>
	<%@include file="../elementpage/element_footer.jspf" %>
	<!-- POP UP Edit profile -->
	<div class="modal-content-edit">
			<button class="modal-content-close" type="button" title="Close">Close</button>
			<h2 class="modal-content-title">${profile_edit_question}</h2>
			<p>${profile_edit_profile_description_action}</p>
			<form class="login-form1-cabinet" action="controller" method="post">
			
			<table>
				<tr>
					<td>${profile_edit_user_name}</td>
					<td><input class="icon-user1" type="text" name="user_name" placeholder="${profile_edit_user_name}" value="${user.name}" required pattern="^[a-zA-Zа-яА-Я]{4,}$"></td>
				</tr>
				<tr>
					<td>${profile_edit_user_surname}</td>
					<td><input class="icon-user1" type="text" name="user_surname" placeholder="${profile_edit_user_surname}" value="${user.surname}" required pattern="^[a-zA-Zа-яА-Я]{4,}$"></td>
				</tr>
				<tr>
					<td>${profile_edit_user_email}</td>
					<td><input class="icon-user1" type="text" name="user_email" placeholder="${profile_edit_user_email}" value="${user.email}" required pattern="/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/"></td>
				</tr>	
			</table>
				<button class="btn" type="submit" name="command" value="edit_profile">${profile_edit_profile_btn}</button>
			</form>
		</div>
		
		<div class="modal-content-edit modal_edit_password">
			<button class="modal-content-close" type="button" title="Close">Close</button>
			<h2 class="modal-content-title">${profile_title_edit_password}</h2>
			<p>Например Nikolaev123</p>
			<form class="login-form1-cabinet" action="controller" method="post">
			<table>
				<tr>
					<td>${profile_password_old}</td>
					<td><input id="old_password" class="icon-user1" type="password" name="old_password" placeholder="${profile_password_old}" required pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])\w{6,}$"></td>
				</tr>
				<tr>
					<td>${profile_password_new}</td>
					<td><input id="new_password" class="icon-user1" type="password" name="new_password" placeholder="${profile_password_new}" required pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])\w{6,}$"></td>
				</tr>
				<tr>
					<td>${profile_password_verefication}</td>
					<td><input id="new_verefication_password" class="icon-user1" type="password" name="new_verefication_password" placeholder="${profile_password_verefication}"  required pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])\w{6,}$"></td>
				</tr>	
			</table>
				<button id="сhange_password" class="btn" type="submit" name="command" value="сhange_password">${profile_password_change_btn}</button>
			</form>
		</div>
		<div class="modal-content-edit modal_create_qestion">
			<button class="modal-content-close" type="button" title="Close">Close</button>
			<h2 class="modal-content-title">${profile_title_create_question}</h2>
			<div class="form_item">
			<form  action="controller" method="post">
				<label>
					<span>${profile_question_title}</span>
					<input type="text" name="question_title">
				</label>
				<label>
					<textarea name="question_content"></textarea>
				</label>
				<button class="btn" type="submit" name="command" value="new_question">${profile_title_create_question_btn}</button>
			</form>
			</div>
		</div>
	<%@include file="../elementpage/element_modal_overlay.jspf" %>
	<%@include file="../elementpage/element_news_create.jspf"%>
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/profileScript.js"></script>
</body>
</html>