<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../elementpage/element_localization.jspf"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../elementpage/element_head.jspf"%>
<title>Helper</title>
</head>
<body>
	<%@ include file="../elementpage/element_header.jspf"%>
	<div class="container">
			<div class="big-heading">
				<h2>${answer_edit_title}</h2>
			</div>
			<div class="form_item">
			<form  action="controller" method="post">
				<label>
					<textarea name="answer_content" >${answer.content}</textarea>
				</label>
				<input type="hidden" name="question_id" value="${answer.questionId}">
				<input type="hidden" name="answer_id" value="${answer.id}">
				<button class="btn" type="submit" name="command" value="edit_answer">${answer_edit_btn}</button>
			</form>
			</div>
	</div>
	<%@include file="../elementpage/element_footer.jspf"%>
	<%@include file="../elementpage/element_popUp.jspf"%>
	<%@include file="../elementpage/element_modal_overlay.jspf"%>
	<script type="text/javascript" src="js/common.js"></script>
</body>
</html>