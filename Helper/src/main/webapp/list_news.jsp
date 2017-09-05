<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../elementpage/element_localization.jspf"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../elementpage/element_head.jspf"%>
<title>News</title>
</head>
<body>
	<%@ include file="../elementpage/element_header.jspf"%>
	<div class="container">
		<div class="message">
			<c:if test="${message == 'failed add'}">
				<div>${news_failed_add}</div>
			</c:if>
			<c:if test="${message == 'user can_not create news'}">
				<div>${news_user_can_not_create}</div>
			</c:if>
			<c:if test="${message == 'news successfully add'}">
				<div>${news_successful_add}</div>
			</c:if>
			<c:if test="${message == 'No news was found'}">
				<div>${news_not_found}</div>
			</c:if>
			<c:if test="${message == 'news_empty'}">
				<div>${news_empty_not_add}</div>
			</c:if>
			
		</div>
		<%@include file="../elementpage/element_news_items.jspf"%>
	</div>
	<%@include file="../elementpage/element_footer.jspf"%>
	<%@include file="../elementpage/element_popUp.jspf"%>
	<%@include file="../elementpage/element_modal_overlay.jspf"%>
	<script type="text/javascript" src="js/common.js"></script>
	<%@include file="../elementpage/element_news_create.jspf"%>
</body>
</html>