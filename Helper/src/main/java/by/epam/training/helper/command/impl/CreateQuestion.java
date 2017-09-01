package by.epam.training.helper.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.training.helper.bean.User;
import by.epam.training.helper.command.Command;
import by.epam.training.helper.command.exception.CommandException;
import by.epam.training.helper.constant.ErrorMessage;
import by.epam.training.helper.constant.ErrorMessageLocation;
import by.epam.training.helper.constant.ParameterName;
import by.epam.training.helper.service.QuestionService;
import by.epam.training.helper.service.exception.ServiceException;
import by.epam.training.helper.service.factory.ServiceFactory;
import by.epam.training.helper.utils.StringUtils;

public class CreateQuestion implements Command {
	private static final Logger logger = LogManager.getLogger(CreateQuestion.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CommandException {
		String questionTitle = request.getParameter(ParameterName.QUESTION_TITLE);
		String questionContent = request.getParameter(ParameterName.QUESTION_CONTENT);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(ParameterName.USER);
		if(!StringUtils.isNullOrEmpty(questionTitle)
				&&!StringUtils.isNullOrEmpty(questionContent)
				&&user!=null){
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			QuestionService questionService = serviceFactory.getQuestionService();
			try {
				questionService.addNewQuestion(user.getId(), questionTitle, questionContent);
				response.sendRedirect("controller?command=user_profile");
			} catch (ServiceException e) {
				e.printStackTrace();
				logger.error(ErrorMessage.ERROR_CREATE_QUESTION);
				request.setAttribute("message", ErrorMessageLocation.ERROR_CREATE_QUESTION);
				request.getRequestDispatcher("controller?command=user_profile").forward(request, response);
			}
		}else{
			logger.error(ErrorMessage.ERROR_CREATE_QUESTION);
			request.setAttribute("message", ErrorMessageLocation.ERROR_CREATE_QUESTION);
			request.getRequestDispatcher("controller?command=user_profile").forward(request, response);
		}
	}
}
