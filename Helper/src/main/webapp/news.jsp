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
	<c:if test="${sessionScope.user.role == 1 }">
		<c:if test="${requestScope.message != null }">
			<script type="text/javascript">
				alert( "Новость добавлена" );
			</script>
		</c:if>
	</c:if>
	<div class="container">
		<%@include file="../elementpage/element_news_items.jspf"%>
	</div>
	<%@include file="../elementpage/element_footer.jspf"%>
	<%@include file="../elementpage/element_popUp.jspf"%>
	<%@include file="../elementpage/element_modal_overlay.jspf"%>
	<script type="text/javascript" src="js/common.js"></script>
	<%@include file="../elementpage/element_news_create.jspf"%>
</body>
</html>