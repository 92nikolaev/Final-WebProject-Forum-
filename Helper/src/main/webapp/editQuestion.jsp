<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../elementpage/element_localization.jspf"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../elementpage/element_head.jspf"%>
<title>Edit Question</title>
</head>
<body>
	<%@ include file="../elementpage/element_header.jspf"%>
	<div class="container">
			<div class="big-heading">
				<h2>${question_edit_title}</h2>
			</div>
			<div class="form_item">
			<form  action="controller" method="post">
				<label>
					<span>${profile_question_title}</span>
					<input type="text" name="question_title" value="${question.title}">
				</label>
				<label>
					<span>${profile_question_description}</span>
					<textarea name="question_content" >${question.content}</textarea>
				</label>
				<input type="hidden" name="question_id" value="${question.id}">
				<button class="btn" type="submit" name="command" value="edit_question">${question_edit_btn}</button>
			</form>
			</div>
	</div>
	<%@include file="../elementpage/element_footer.jspf"%>
	<%@include file="../elementpage/element_popUp.jspf"%>
	<%@include file="../elementpage/element_modal_overlay.jspf"%>
	<script type="text/javascript" src="js/common.js"></script>
</body>
</html>