<%@ page language="java" isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
			<h2>Error page</h2>
		</div>
		<h3>
			We apologize, there were unforeseen difficulties when working with the system, try to execute the actions again....
		</h3>
	</div>
	<%@include file="../elementpage/element_footer.jspf" %>
	<%@include file="../elementpage/element_popUp.jspf" %>
	<%@include file="../elementpage/element_modal_overlay.jspf" %>
	<script type="text/javascript" src="js/common.js"></script>
</body>
</html>