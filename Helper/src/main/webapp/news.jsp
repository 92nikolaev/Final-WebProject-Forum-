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
		<c:if test="${sessionScope.user.role == 1||sessionScope.user.role == 2}">
		<button class="btn editNews" type="button" name="command" value="create_news">${administrator_news_edit_btn}</button>
		</c:if>
		<div class="big-heading">
			<h2>${news.title}</h2>
		</div>
		<div class="content_news">
			${news.content}
		</div>
	</div>
	<%@include file="../elementpage/element_footer.jspf"%>
	<%@include file="../elementpage/element_popUp.jspf"%>
	<%@include file="../elementpage/element_modal_overlay.jspf"%>
	<script type="text/javascript" src="js/common.js"></script>
	<%@include file="../elementpage/element_news_create.jspf"%>
	<c:if test="${sessionScope.user.role == 1||sessionScope.user.role == 2}">
	<div class="modal-content-edit modal_edit_news">
		<button class="modal-content-close" type="button" title="Close">Close</button>
		<h2 class="modal-content-title">${administrator_title_edit_news}</h2>
		<div class="form_item">
			<form  action="controller" method="post">
				<label>
					<span>${administrator_title_news}</span>
					<input type="text" name="news_title" value="${news.title}">
				</label>
				<label>
					<span>${administrator_content_news}</span>
					<textarea name="news_content">${news.content}</textarea>
				</label>
				<input type="hidden" name="news_id" value="${news.id}" />
				<button class="btn" type="submit" name="command" value="edit_news">${administrator_news_edit_btn}</button>
			</form>
		</div>
	</div>	
	<script type="text/javascript">
			var editNews = document.querySelector(".editNews");
			var popupEditNews = document.querySelector(".modal_edit_news");
			var popOff = popupEditNews.querySelector(".modal-content-close");
			var overlay = document.querySelector(".modal-overlay");
		
			editNews.addEventListener("click", function(event) {
			event.preventDefault();
			popupEditNews.classList.add("modal-content-show");
			overlay.classList.add("modal-content-show");
		});
		
		popOff.addEventListener("click", function(event) {
			event.preventDefault();
			popupEditNews.classList.remove("modal-content-show");
			popupEditNews.classList.remove("modal-error");
			overlay.classList.remove("modal-content-show");
			
		});
		
		window.addEventListener("keydown", function(event){
			if(event.keyCode === 27){
				if(popup.classList.contains("modal-content-show")) {
					popupEditNews.classList.remove("modal-content-show");
					popupEditNews.classList.remove("modal-error");
					overlay.classList.remove("modal-content-show");
				}
			}
		});
	</script>					
	</c:if>
</body>
</html>