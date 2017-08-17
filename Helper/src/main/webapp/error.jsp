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
		<div class="big-heading">
			<h2>${profile_title}</h2>
		</div>
		<h3>
			<c:choose>
	    		<c:when test="${pageContext.errorData.statusCode == 404}">
	       			${error_404_message}
	    		</c:when>
	    		<c:when test="${pageContext.errorData.statusCode == 403}">
	       			${error_403_message}
	       		</c:when>
	    		<c:when test="${pageContext.errorData.statusCode == 414}">
	       			${error_414_message}
	       		</c:when>
	    		<c:when test="${pageContext.errorData.statusCode == 504}">
	       			${error_504_message}
	       		</c:when>
	    		<c:otherwise>
	    			Unknown error
	    		</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<%@include file="../elementpage/element_footer.jspf" %>
	<%@include file="../elementpage/element_popUp.jspf" %>
	<%@include file="../elementpage/element_modal_overlay.jspf" %>
	<script type="text/javascript" src="js/common.js"></script>
</body>
</html>