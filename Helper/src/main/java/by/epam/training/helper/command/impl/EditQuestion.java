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
import by.epam.training.helper.constant.ParameterName;
import by.epam.training.helper.constant.Url;
import by.epam.training.helper.service.QuestionService;
import by.epam.training.helper.service.exception.ServiceException;
import by.epam.training.helper.service.factory.ServiceFactory;
import by.epam.training.helper.tools.NullOrEmpty;
import by.epam.training.helper.tools.StringInNumber;

public class EditQuestion implements Command {
	private static final Logger logger = LogManager.getLogger(EditQuestion.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CommandException {
		String questionIdParamet = request.getParameter(ParameterName.QUESTION_ID);
		String questionTitleParamet = request.getParameter(ParameterName.QUESTION_TITLE);
		String questionContentParamet = request.getParameter(ParameterName.QUESTION_CONTENT);
		try{
			int questionId =  StringInNumber.parseString(questionIdParamet);
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute(ParameterName.USER);
			if(user != null){
				if(!NullOrEmpty.isNullOrEmpty(questionTitleParamet)&&!NullOrEmpty.isNullOrEmpty(questionContentParamet)){
					ServiceFactory serviceFactory = ServiceFactory.getInstance();
					QuestionService questionService = serviceFactory.getQuestionService();
					questionService.updateQuestionById(questionId, questionTitleParamet, questionContentParamet);
					response.sendRedirect("controller?command=show_question&question_id="+questionId);
				}else{
					request.getRequestDispatcher("controller?command=page_edit_question&question_id="+questionId).forward(request, response);
				}
			}else {
				response.sendRedirect(Url.SIGN_IN);
			}
		}catch (NumberFormatException e) {
			logger.error(ErrorMessage.INVALID_ID);
			e.printStackTrace();
			throw new CommandException(ErrorMessage.INVALID_ID);
		} catch (ServiceException e) {
			logger.error(e);
			e.printStackTrace();
			throw new CommandException();
		}
	}
}
