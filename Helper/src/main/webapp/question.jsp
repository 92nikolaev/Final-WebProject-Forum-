<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		<div class = title_question>
			<h1>${requestScope.question.title}</h1>
		</div>	
		<div class="post">
			<table class="table_question">
				<tbody>
					<tr class="post_heder">
						<td class="left-column">
						<fmt:formatDate value="${requestScope.question.dateCreated}" pattern="dd MMMM"/>
						</td>
						<td class="right-column">${question_colm}</td>
					</tr>
					<tr class="post_content_tr">
						<td class="user_info left-column">
							<div class="login_user">	
								<h3>${requestScope.question.userLogin}</h3>
							</div>
							<span class="post_user_avatar"><img alt="${question_avatar}" src="img/avatar_question.gif" width="100px" height="100px"></span>
							<div class="average_mark">
								${question_answers} ${requestScope.question.answerCount}
							</div>
							<div>
								<c:if test="${sessionScope.user.login == question.userLogin}">
									<form action="controller" method="post">
											<input type="hidden" name="question_id" value="${requestScope.question.id}">
											<button class="btn edit_question_btn" type="submit" name="command" value="page_edit_question">${question_edit_btn}</button>
									</form>
								</c:if>
							</div>
						</td>
						<td>
							<div class= post_content_td  class="right-column">
								<div class="word-wrap post-content-body"><p>${requestScope.question.content}</p></div>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<c:forEach items="${requestScope.answers}" var = "answer">
		<div class="answers">
				<table class="table_question">
				<tbody>
					<tr class="post_heder">
						<td class="left-column">
						<fmt:formatDate value="${answer.dateCreated}" pattern="dd MMMM"/>
						</td>
						<td class="right-column">${question_answer}</td>
					</tr>
					<tr class="post_content_tr">
						<td class="user_info left-column">
							<div class="login_user">	
								<h3>${answer.userLogin}</h3>
							</div>
							<span class="post_user_avatar"><img alt="${question_avatar}" src="img/avatar_answer.jpg" width="100px" height="100px"></span>
							<div class="average_mark">
								${question_average_rating}: ${answer.averageMark}
							</div>
							<c:if test="${sessionScope.logged == true}">
								<c:choose>
									<c:when test="${sessionScope.user.login == answer.userLogin}">
										<form action="controller" method="post">
											<input type="hidden" name="question_id" value="${requestScope.question.id}">
											<input type="hidden" name="answer_id" value="${answer.id}">
											<button class="btn edit_answer_btn" type="submit" name="command" value="page_edit_answer">${question_edit_answer}</button>
										</form>
									</c:when>
									<c:otherwise>
									<form action="controller" method="post">
										<div id="rate" class="rating">
										  <!-- Пятая звезда  -->
										  <input type="radio" id="star_5" name="mark_value" value="5" />
										  <label for="star_5" title="Incredible"><i class="fa fa-star" aria-hidden="true"></i></label>
										  <!-- Четвертая звезда -->
										  <input type="radio" id="star_4" name="mark_value" value="4" />
										  <label for="star_4" title="Well"><i class="fa fa-star" aria-hidden="true"></i></label>
										  <!-- Третия звезда  -->
										  <input type="radio" id="star_3" name="mark_value" value="3" />
										  <label for="star_3" title="Normally"><i class="fa fa-star" aria-hidden="true"></i></label>
										  <!-- Вторая звезда  -->
										  <input type="radio" id="star_2" name="mark_value" value="2" />
										  <label for="star_2" title="Bad"><i class="fa fa-star" aria-hidden="true"></i></label>
										  <!-- Первая звезда -->
										  <input type="radio" id="star_1" name="mark_value" value="1" />
										  <label for="star_1" title="Awful"><i class="fa fa-star" aria-hidden="true"></i></label>
										</div>
										<input type="hidden" name="answer_id" value="${answer.id}">
										<input type="hidden" name="question_id" value="${requestScope.question.id}">
										<button class="btn" type="submit" name="command" value="evaluate_answer">${question_rate_answer}</button>
										</form>
										 
									</c:otherwise>
								</c:choose>
							</c:if>
						</td>
						<td>
							<div class= post_content_td  class="right-column">
								<div class="word-wrap post-content-body"><p>${answer.content}</p></div>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		</c:forEach>
		<c:choose>
    		<c:when test="${sessionScope.logged == true}">
       			<button class="btn create_answer" type="submit" name="create_answer" value="evaluate_user">${question_create_answer}</button>
   			</c:when>
    		<c:otherwise>
       			${question_signIn_response} 
    		</c:otherwise>
		</c:choose>
	</div>
	<%@include file="../elementpage/element_footer.jspf" %>
	<%@include file="../elementpage/element_popUp.jspf" %>
	<%@include file="../elementpage/element_modal_overlay.jspf" %>
	<div class="modal-content-edit modal_create_qestion">
		<button class="modal-content-close" type="button" title="Close">Close</button>
		<h2 class="modal-content-title">${question_create_answer_action_description}</h2>
		<div class="form_item">
			<form  action="controller" method="post">
					<textarea required="required" name="answer_content"></textarea>
					<input type="hidden" name="question_id" value="${requestScope.question.id}">
					<button class="btn" type="submit" name="command" value="new_answer">${question_create_answer_btn}</button>
			</form>
		</div>
	</div>
	<script type="text/javascript" src="js/questionPage.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
</body>
</html>